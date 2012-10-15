package gov.dsb.web.action.d.d01;

import gov.dsb.core.dao.DocCategoryDao;
import gov.dsb.core.dao.SysPrivilegeDao;
import gov.dsb.core.domain.DocCategory;
import gov.dsb.core.domain.SysPrivilege;
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
 * Date: 2009-7-16
 * Time: 21:08:36
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "/common/blank", type = "redirect")})
public class SysPrivilegeAction extends CRUDActionSupport<SysPrivilege> {

    @Autowired
    private SysPrivilegeDao service;

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
                //给系统权限赋予文档查看权限
                DocCategory doccategory = docCategoryDao.get(doccategoryid);
                removepermit(doccategory.getId());
                saveviewpermit(doccategory.getId());
            } else if (permittype == 2) {
                //给系统权限赋予一系列文档查看权限
                Collection<DocCategory> doccategorys = new ArrayList<DocCategory>();
                getDoccategorys(doccategorys, doccategoryid);
                for (DocCategory d : doccategorys) {
                    removepermit(d.getId());
                    saveviewpermit(d.getId());
                }
            } else if (permittype == 3) {
                //给系统权限赋予文档修改权限
                DocCategory doccategory = docCategoryDao.get(doccategoryid);
//                entity.getEditDocPermitted().add(doccategory);
                removepermit(doccategory.getId());
                saveeditpermit(doccategory.getId());
            } else if (permittype == 4) {
                //给系统权限赋予一系列文档修改权限
                Collection<DocCategory> doccategorys = new ArrayList<DocCategory>();
                getDoccategorys(doccategorys, doccategoryid);
                for (DocCategory d : doccategorys) {
//                    entity.getEditDocPermitted().add(d);
                    removepermit(d.getId());
                    saveeditpermit(d.getId());
                }
            } else if (permittype == 5) {
                for (DocCategory d : entity.getViewDocPermitted()) {
                    if (d.getId().equals(doccategoryid)) {
//                        entity.getViewDocPermitted().remove(d);
                        removepermit(d.getId());
                        break;
                    }
                }
            } else if (permittype == 6) {
                //撤销系统权限的一系列权限
                Collection<DocCategory> doccategorys = new ArrayList<DocCategory>();
                getDoccategorys(doccategorys, doccategoryid);
                Map<Long, DocCategory> map = new HashMap<Long, DocCategory>();
                for (DocCategory d : doccategorys) {
                    map.put(d.getId(), d);
                }


                for (DocCategory d : entity.getViewDocPermitted()) {
                    if (map.containsKey(d.getId())) {
                        entity.getViewDocPermitted().remove(d);
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
                entity = new SysPrivilege();
            }
        }
    }

    public SysPrivilege getModel() {
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
        doccategory.getViewPermittedPrivilege().add(entity);
        docCategoryDao.save(doccategory);

    }

    private void saveeditpermit(Long doccategoryid) {
        DocCategory doccategory = docCategoryDao.get(doccategoryid);
        doccategory.getEditPermittedPrivilege().add(entity);
        docCategoryDao.save(doccategory);

    }

    private void removepermit(Long doccategoryid) {
        DocCategory doccategory = docCategoryDao.get(doccategoryid);
        doccategory.getEditPermittedPrivilege().remove(entity);
        doccategory.getViewPermittedPrivilege().remove(entity);
        docCategoryDao.save(doccategory);

    }
}
