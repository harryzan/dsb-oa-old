package gov.dsb.web.action.document.docclientcategory;

import gov.dsb.core.dao.DocClientCategoryDao;
import gov.dsb.core.domain.DocClientCategory;
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
 * Time: 19:59:27
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "doc-client-category", type = "chain")})
public class DocClientCategoryAction extends CRUDActionSupport<DocClientCategory> {

    @Autowired
    private DocClientCategoryDao service;

    protected Long id;

    private Long parentid;


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

    public String save() throws Exception {
        if (parentid != null) {
            DocClientCategory parent = service.get(parentid);
            entity.setParent(parent);
        }
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
    }

    public DocClientCategory getModel() {
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
            DocClientCategory temp = null;
            long orderno = -1;

            Collection<DocClientCategory> col = null;
            if (entity.getParent() == null) {
                col = service.findByQuery("from DocClientCategory where parent is null");
            } else {
                col = entity.getParent().getChildren();
            }

            for (DocClientCategory d : col) {
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

    /**
     * 清空子目录
     *
     * @return SUCCESS
     * @throws Exception .
     */
    public String clear() throws Exception {


        return SUCCESS;
    }
}
