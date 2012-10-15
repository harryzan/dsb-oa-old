package gov.dsb.web.action.document.docdocument;

import gov.dsb.core.dao.DocCategoryDao;
import gov.dsb.core.dao.DocDocumentDao;
import gov.dsb.core.dao.base.Page;
import gov.dsb.core.domain.DocCategory;
import gov.dsb.core.domain.DocDocument;
import gov.dsb.core.domain.DocDocumentAttach;
import gov.dsb.core.struts2.PageActionSupport;
import gov.dsb.core.utils.Nulls;
import gov.dsb.core.utils.StringHelp;
import gov.dsb.web.ui.grid.Grid;
import gov.dsb.web.ui.grid.QueryTranslateWeight;
import gov.dsb.web.ui.grid.Row;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-14
 * Time: 16:04:04
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = PageActionSupport.GRIDDATA, location = "/WEB-INF/pages/common/gridData.jsp"),
        @Result(name = "data", location = "/WEB-INF/pages/common/ajaxutilData.jsp")})
public class DocDocumentGridAction extends PageActionSupport<DocDocument> {

    @Autowired
    private DocDocumentDao service;

    @Autowired
    private DocCategoryDao docCategoryDao;

    private String columns;

    private Integer start;

    private Integer limit;

    private String conditions;

    private List<Row> rows;

    private String gridParams;
    //////

    private Long doccategoryid;

    private String queryCondition;

    public String getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public List<Row> getRows() {
        return rows;
    }

    public String getGridParams() {
        return gridParams;
    }

    public void setGridParams(String gridParams) {
        this.gridParams = gridParams;
    }

    public Long getDoccategoryid() {
        return doccategoryid;
    }

    public void setDoccategoryid(Long doccategoryid) {
        this.doccategoryid = doccategoryid;
    }

    public void prepare() throws Exception {
        if (gridParams == null) {
            gridParams = "";
        }
    }

    public String list() throws Exception {
        return SUCCESS;
    }

    public String griddata() throws Exception {

        if (!Nulls.isNull(limit) && limit > 0) {
            page = new Page<DocDocument>(limit, true);
        } else {
            page = new Page<DocDocument>(10, true);
        }

        if (!Nulls.isNull(start) && !Nulls.isNull(limit)) {
            int pageNo = start / limit + 1;
            page.setPageNo(pageNo);
        }
        if (null != doccategoryid) {
            DocCategory docCategory = docCategoryDao.get(doccategoryid);
            queryCondition = "文件夹为 \"" + docCategory.getName() + "\"";
        }
        String hql = "from DocDocument where doccategory.id=?";
        if (!StringHelp.isEmpty(conditions)) {
            QueryTranslateWeight queryTranslate = new QueryTranslateWeight(hql, conditions);
            hql = queryTranslate.toString();
        }

        // System.out.println("queryCondition: "+ queryCondition);
        hql += " order by createtime desc";


        page = service.findPageByQuery(page, hql, doccategoryid);
        if (page.getResult() != null) {
            Collection<DocDocumentAttach> attachs = new ArrayList<DocDocumentAttach>();
            for (DocDocument doc : page.getResult()) {
                attachs = doc.getDocdocumentattaches();
                if (attachs != null && attachs.size() > 0) {
                    String desc = "";
                    desc += "?";
                    //doc.setDescription() + "?");
                    for (DocDocumentAttach attach : attachs) {
                        //doc.setDescription(doc.getDescription() + attach.getId() + ":" + attach.getFilename() +",");
                        desc += attach.getId() + ":" + attach.getFilename() + ",";
                    }
                    if (desc.endsWith(",")) {
                        desc = desc.substring(0, desc.length() - 1);
                    }
                    doc.setDescription(doc.getDescription() + desc);
                }


            }
        }

        rows = Grid.gridValue2Rows(page.getResult(), columns);

        return GRIDDATA;
    }

    private String result;

    public String getResult() {
        return result;
    }

    public String resolveCondition() throws Exception {
        try {
            if (null != doccategoryid) {
                DocCategory docCategory = docCategoryDao.get(doccategoryid);
                queryCondition = "文档目录为 \"" + docCategory.getName() + "\"";
            }
            String hql = "from DocDocument where doccategory.id=?";
            if (!StringHelp.isEmpty(conditions)) {
                QueryTranslateWeight queryTranslate = new QueryTranslateWeight(hql, conditions);
                hql = queryTranslate.toString();
                String[] fields = hql.substring(hql.indexOf("?"), hql.lastIndexOf(")")).split("and");
                for (String field : fields) {
                    field = field.trim();

                    String operator = "";
                    if (field.indexOf("createtime") > -1) {
                        queryCondition += " 创建时间";
                        if (field.indexOf("between") > -1) {
                            queryCondition +=
                                    " 在 " + field.substring(field.indexOf("between") + 17, field.indexOf(",") - 1) + " 和 " +
                                            field.substring(field.indexOf("AND") + 13,
                                                    field.indexOf(",", field.indexOf("AND")) - 1) + "之间";
                        } else {


                            if (field.indexOf(">=") > -1) {
                                operator = " 大于等于 ";
                            } else if (field.indexOf("<=") > -1) {
                                operator = " 小于等于 ";
                            } else if (field.indexOf("=") > -1) {
                                operator = " 等于 ";
                            } else if (field.indexOf("<") > -1) {
                                operator = " 小于 ";
                            } else if (field.indexOf(">") > -1) {
                                operator = " 大于 ";
                            }
                            //String value = field.substring(field.indexOf("(") + 2, field.indexOf(",") - 1);
                            queryCondition += operator + field.substring(field.indexOf("to_date") + 9, field.indexOf(",") - 1);
                        }

                    } else {
                        String name = "";
                        String value = "";
                        if (field.indexOf("sysdept.name") > -1) {
                            name = " 单位名称";
                        } else if (field.indexOf("name") > -1) {
                            name = " 文档名称";
                        } else if (field.indexOf("path") > -1) {
                            name = " 文档目录";
                        }

                        if (field.indexOf("=") > -1) {
                            operator = " 等于 ";
                            value = field.substring(field.indexOf("'") + 1, field.length() - 1);
                        } else if (field.indexOf("<>") > -1) {
                            operator = " 不等于 ";
                            value = field.substring(field.indexOf("'") + 1, field.length() - 1);
                        } else if (field.indexOf("not like") > -1) {
                            operator = " 不包含 ";
                            value = field.substring(field.indexOf("'%") + 2, field.indexOf("%'"));
                        } else if (field.indexOf("like") > -1) {

                            if (field.indexOf("'%") > -1) {
                                if (field.indexOf("%'") > -1) {
                                    operator = " 包含 ";
                                    value = field.substring(field.indexOf("'%") + 2, field.indexOf("%'"));
                                } else {
                                    operator = " 以 ";
                                    value = field.substring(field.indexOf("'%") + 2, field.length() - 1) + " 结尾";
                                }
                            } else if (field.indexOf("%'") > -1) {
                                operator = " 以 ";
                                value = field.substring(field.indexOf("'") + 1, field.indexOf("%'")) + " 开头";
                            }
                        } else if (field.indexOf("is null") > -1) {
                            operator = " 为空 ";
                        } else if (field.indexOf("is not null") > -1) {
                            operator = " 不为空 ";
                        }

                        queryCondition += name + operator + value + ",";

                    }

                }
                //page = service.findPageByQuery(page, queryTranslate.toString() + " order by createtime desc", doccategoryid);
                if (queryCondition.endsWith(",")) {
                    queryCondition = queryCondition.substring(0, queryCondition.length() - 1);
                }
            }
        } catch (Exception ignore) {

        }
        //System.out.println("queryCondition: "+ queryCondition);
        result = queryCondition;

        return "data";
    }
}