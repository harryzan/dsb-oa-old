package gov.dsb.web.action.common.document;

import gov.dsb.core.dao.DocClientCategoryDao;
import gov.dsb.core.domain.DocCategory;
import gov.dsb.core.domain.DocClientCategory;
import gov.dsb.core.domain.DocRelateCategory;
import gov.dsb.core.struts2.CRUDActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-27
 * Time: 10:04:53
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "doc-client-category", type = "redirect")})
public class DocClientCategoryAction extends CRUDActionSupport<DocClientCategory> {

    @Autowired
    private DocClientCategoryDao service;

    protected Long id;

    private Long documentid;

    private String modelname;

    private Collection<DocCategory> doccategories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumentid() {
        return documentid;
    }

    public void setDocumentid(Long documentid) {
        this.documentid = documentid;
    }

    public Collection<DocCategory> getDoccategories() {
        return doccategories;
    }

    public void setDoccategories(Collection<DocCategory> doccategories) {
        this.doccategories = doccategories;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String save() throws Exception {
        service.save(entity);
        return RELOAD;
    }

    public String delete() throws Exception {
        service.delete(id);
        return RELOAD;
    }

    protected void prepareModel() throws Exception {
        if (entity == null) {
            if (id != null) {
                entity = service.get(id);
            } else {
                entity = new DocClientCategory();
            }
        }

        //根据功能模块的名称来获取对应的文档业务目录 再获取该文档业务目录下所有的文档目录
        if (modelname != null) {
            DocClientCategory docclient = service.findUnique("from DocClientCategory d where d.purpose=? order by d.orderno", modelname.toLowerCase());
            if (docclient != null) {
                Collection<DocRelateCategory> relates = docclient.getDocrelatecategories();
                if (relates != null && relates.size() > 0) {
                    doccategories = new ArrayList<DocCategory>();
                    for (DocRelateCategory relate : relates) {
                        doccategories.add(relate.getDoccategory());
                    }
                }
            }
        }
        if (doccategories == null || doccategories.size() <= 0) {
            doccategories = null;
        }
    }

    public String main() throws Exception {
        return "main";
    }

    public String choice() throws Exception {
        if (documentid == null) {
            return "choice";
        } else {
            return "stage";
        }

    }

    public DocClientCategory getModel() {
        return entity;
    }
}
