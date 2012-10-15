package gov.dsb.web.action.document.docclientcategory;

import gov.dsb.core.dao.DocCategoryDao;
import gov.dsb.core.dao.DocClientCategoryDao;
import gov.dsb.core.dao.DocRelateCategoryDao;
import gov.dsb.core.domain.DocCategory;
import gov.dsb.core.domain.DocClientCategory;
import gov.dsb.core.domain.DocRelateCategory;
import gov.dsb.core.struts2.CRUDActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-21
 * Time: 20:00:09
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "doc-relate-category", type = "chain"),
        @Result(name = CRUDActionSupport.DELETE, location = "/common/blank", type = "redirect")})
public class DocRelateCategoryAction extends CRUDActionSupport<DocRelateCategory> {

    @Autowired
    private DocRelateCategoryDao service;

    @Autowired
    private DocClientCategoryDao docClientCategoryDao;

    @Autowired
    private DocCategoryDao docCategoryDao;

    protected Long id;

    private Long parentid;

    private Long docclientcategoryid;

    private Long doccategoryid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Long getDocclientcategoryid() {
        return docclientcategoryid;
    }

    public void setDocclientcategoryid(Long docclientcategoryid) {
        this.docclientcategoryid = docclientcategoryid;
    }

    public Long getDoccategoryid() {
        return doccategoryid;
    }

    public void setDoccategoryid(Long doccategoryid) {
        this.doccategoryid = doccategoryid;
    }

    public String save() throws Exception {
        if (docclientcategoryid != null) {
            if (doccategoryid != null) {
                DocClientCategory docclient = docClientCategoryDao.get(docclientcategoryid);
                DocCategory doccategory = docCategoryDao.get(doccategoryid);
                entity.setDoccategory(doccategory);
                entity.setDocclientcategory(docclient);
                entity.setName(doccategory.getName());

                service.save(entity);
            }
        } else if (parentid != null) {
            if (doccategoryid != null) {
                DocRelateCategory parent = service.get(parentid);
                DocCategory doccategory = docCategoryDao.get(doccategoryid);
                entity.setDoccategory(doccategory);
                entity.setParent(parent);
                entity.setName(doccategory.getName());
                entity.setDocclientcategory(parent.getDocclientcategory());

                service.save(entity);
            }
        } else if (id != null) {
            service.save(entity);

        }
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
                entity = new DocRelateCategory();
            }
        }
    }

    public DocRelateCategory getModel() {
        return entity;
    }

    private Boolean upoperation;

    public Boolean getUpoperation() {
        return upoperation;
    }

    public void setUpoperation(Boolean upoperation) {
        this.upoperation = upoperation;
    }

    /**
     * 上移 / 下移
     *
     * @return SUCCESS
     * @throws Exception .
     */
    public String operation() throws Exception {

        if (entity != null) {
            DocRelateCategory temp = null;
            long orderno = -1;
            Collection<DocRelateCategory> col = null;

            if (entity.getParent() == null) {
                col = service.findByQuery("from DocRelateCategory where parent is null and docclientcategory.id=" + entity.getDocclientcategory().getId());
            } else {
                col = entity.getParent().getChildren();
            }


            for (DocRelateCategory d : col) {
                if (upoperation) {
                    if (d.getOrderno() < entity.getOrderno() && d.getOrderno() > orderno) {
                        temp = d;
                        orderno = d.getOrderno();
                    }
                } else {
                    if (d.getOrderno() > entity.getOrderno()) {
                        if (orderno == -1) {
                            temp = d;
                            orderno = d.getOrderno() - entity.getOrderno();
                        } else if (d.getOrderno() - entity.getOrderno() < orderno) {
                            temp = d;
                            orderno = d.getOrderno() - entity.getOrderno();
                        }
                    }
                }
            }
            if (temp != null) {
                long no1 = temp.getOrderno();
                long no2 = entity.getOrderno();
                entity.setOrderno(no1);
                temp.setOrderno(no2);
                service.save(entity);
                service.save(temp);
            }
        }

        return SUCCESS;
    }
}
