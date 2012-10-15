package gov.dsb.web.action.document.doccategory;

import gov.dsb.core.dao.DocCategoryDao;
import gov.dsb.core.dao.SysUserDao;
import gov.dsb.core.domain.DocCategory;
import gov.dsb.core.domain.SysUser;
import gov.dsb.core.struts2.CRUDActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-17
 * Time: 10:04:25
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "/common/blank", type = "redirect")})
public class SysUserAction extends CRUDActionSupport<SysUser> {

    @Autowired
    private SysUserDao service;

    @Autowired
    private DocCategoryDao docCategoryDao;

    protected Long id;

    private Long permittype;

    private Long doccategoryid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPermittype() {
        return permittype;
    }

    public void setPermittype(Long permittype) {
        this.permittype = permittype;
    }

    public Long getDoccategoryid() {
        return doccategoryid;
    }

    public void setDoccategoryid(Long doccategoryid) {
        this.doccategoryid = doccategoryid;
    }

    public String save() throws Exception {
        service.save(entity);
        return RELOAD;
    }

    public String savepermit() throws Exception {
        if (id != null) {
            if (permittype == 1) {
                //给用户部门赋予文档查看权限
                DocCategory doccategory = docCategoryDao.get(doccategoryid);
                removepermit(doccategoryid);
                saveviewpermit(doccategory.getId());
            } else if (permittype == 2) {
                //给用户赋予一系列文档查看权限
                Collection<DocCategory> doccategorys = new ArrayList<DocCategory>();
                getDoccategorys(doccategorys, doccategoryid);
                for (DocCategory d : doccategorys) {
                    removepermit(d.getId());
                    saveviewpermit(d.getId());
                }
            } else if (permittype == 3) {
                //给用户赋予文档修改权限
                DocCategory doccategory = docCategoryDao.get(doccategoryid);
                removepermit(doccategory.getId());
                saveeditpermit(doccategory.getId());
            } else if (permittype == 4) {
                //给用户赋予一系列文档修改权限
                Collection<DocCategory> doccategorys = new ArrayList<DocCategory>();
                getDoccategorys(doccategorys, doccategoryid);
                for (DocCategory d : doccategorys) {
                    removepermit(d.getId());
                    saveeditpermit(d.getId());
                }
            } else if (permittype == 5) {
                //撤销用户的文档权限
                for (DocCategory d : entity.getEditDocPermitted()) {
                    if (d.getId().equals(doccategoryid)) {
                        removepermit(d.getId());
                        break;
                    }
                }
                for (DocCategory d : entity.getViewDocPermitted()) {
                    if (d.getId().equals(doccategoryid)) {
                        removepermit(d.getId());
                        break;
                    }
                }
            } else if (permittype == 6) {
                //撤销用户的一系列权限
                Collection<DocCategory> doccategorys = new ArrayList<DocCategory>();
                getDoccategorys(doccategorys, doccategoryid);
                Map<Long, DocCategory> map = new HashMap<Long, DocCategory>();
                for (DocCategory d : doccategorys) {
                    map.put(d.getId(), d);
                }

                for (DocCategory d : entity.getViewDocPermitted()) {
                    if (map.containsKey(d.getId())) {
                        removepermit(d.getId());
                    }
                }
            }

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
                entity = new SysUser();
            }
        }
    }

    public SysUser getModel() {
        return entity;
    }

    private void getDoccategorys(Collection<DocCategory> cols, Long id) {
        DocCategory d = docCategoryDao.get(id);
        cols.add(d);
        if (d.getChildren() != null && d.getChildren().size() > 0) {
            for (DocCategory doc : d.getChildren()) {
                getDoccategorys(cols, doc.getId());
            }
        }
    }

    private void saveviewpermit(Long doccategoryid) {

        DocCategory doccategory = docCategoryDao.get(doccategoryid);
        doccategory.getViewPermittedUser().add(entity);
        docCategoryDao.save(doccategory);
    }

    private void saveeditpermit(Long doccategoryid) {

        DocCategory doccategory = docCategoryDao.get(doccategoryid);
        doccategory.getEditPermittedUser().add(entity);
        docCategoryDao.save(doccategory);

    }

    private void removepermit(Long doccategoryid) {
        DocCategory doccategory = docCategoryDao.get(doccategoryid);
        doccategory.getEditPermittedUser().remove(entity);
        doccategory.getViewPermittedUser().remove(entity);
        docCategoryDao.save(doccategory);

    }
}
