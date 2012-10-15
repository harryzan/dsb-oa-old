package gov.dsb.web.action.document.doccategory;

import gov.dsb.core.dao.DocCategoryDao;
import gov.dsb.core.dao.SysDeptDao;
import gov.dsb.core.dao.SysPrivilegeDao;
import gov.dsb.core.dao.SysUserDao;
import gov.dsb.core.domain.DocCategory;
import gov.dsb.core.domain.SysDept;
import gov.dsb.core.domain.SysPrivilege;
import gov.dsb.core.domain.SysUser;
import gov.dsb.core.struts2.CRUDActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-13
 * Time: 16:30:01
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "doc-category", type = "chain")})
public class DocCategoryAction extends CRUDActionSupport<DocCategory> {

    @Autowired
    private DocCategoryDao service;

    @Autowired
    private SysDeptDao sysDeptDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysPrivilegeDao sysPrivilegeDao;

    protected Long id;

    private Long parentid;

    private Long viewdeptid;

    private Long viewuserid;

    private Long viewprivilegeid;

    private Long editdeptid;

    private Long edituserid;

    private Long editprivilegeid;

    private Collection<Map<String, Object>> viewunit;

    private Integer viewrow = 1;

    private Collection<Map<String, Object>> editunit;

    private Integer editrow = 1;

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

    public Long getViewdeptid() {
        return viewdeptid;
    }

    public void setViewdeptid(Long viewdeptid) {
        this.viewdeptid = viewdeptid;
    }

    public Long getViewuserid() {
        return viewuserid;
    }

    public void setViewuserid(Long viewuserid) {
        this.viewuserid = viewuserid;
    }

    public Long getViewprivilegeid() {
        return viewprivilegeid;
    }

    public void setViewprivilegeid(Long viewprivilegeid) {
        this.viewprivilegeid = viewprivilegeid;
    }

    public Long getEditdeptid() {
        return editdeptid;
    }

    public void setEditdeptid(Long editdeptid) {
        this.editdeptid = editdeptid;
    }

    public Long getEdituserid() {
        return edituserid;
    }

    public void setEdituserid(Long edituserid) {
        this.edituserid = edituserid;
    }

    public Long getEditprivilegeid() {
        return editprivilegeid;
    }

    public void setEditprivilegeid(Long editprivilegeid) {
        this.editprivilegeid = editprivilegeid;
    }

    public Collection<Map<String, Object>> getViewunit() {
        return viewunit;
    }


    public Integer getViewrow() {
        return viewrow;
    }

    public void setViewrow(Integer viewrow) {
        this.viewrow = viewrow;
    }

    public Collection<Map<String, Object>> getEditunit() {
        return editunit;
    }

    public Integer getEditrow() {
        return editrow;
    }

    public void setEditrow(Integer editrow) {
        this.editrow = editrow;
    }

    /////////////////////
    public String save() throws Exception {
        if (parentid != null) {
            DocCategory parent = service.get(parentid);
            if (parent != null) {
                entity.setParent(parent);
                entity.setPath(parent.getPath() + File.separator + entity.getName());
            }
        } else {
            entity.setPath(entity.getName());
        }


        //添加部门查看权限
        boolean flag = true;
        if (viewdeptid != null) {
            for (SysDept dept : entity.getViewPermittedDept()) {
                if (dept.getId().equals(viewdeptid)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                SysDept dept = sysDeptDao.get(viewdeptid);
                if (dept != null) {
                    entity.getViewPermittedDept().add(dept);
                }
            }
        }

        //添加用户查看权限
        flag = true;
        if (viewuserid != null) {
            SysUser adduser = sysUserDao.get(viewuserid);
            if (entity.getViewPermittedUser() != null) {
                for (SysUser user : entity.getViewPermittedUser()) {
                    if (user.getId().equals(viewuserid)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    if (adduser != null) {
                        entity.getViewPermittedUser().add(adduser);
                    }
                }
            }
            else {
                Set<SysUser> viewPermittedUsers = new HashSet<SysUser>();
                viewPermittedUsers.add(adduser);
                entity.setViewPermittedUser(viewPermittedUsers);
            }
        }

        //添加系统查看权限
        flag = true;
        if (viewprivilegeid != null) {
            for (SysPrivilege privilege : entity.getViewPermittedPrivilege()) {
                if (privilege.getId().equals(viewprivilegeid)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                SysPrivilege privilege = sysPrivilegeDao.get(viewprivilegeid);
                if (privilege != null) {
                    entity.getViewPermittedPrivilege().add(privilege);
                }
            }
        }


        //添加部门修改权限
        flag = true;
        if (editdeptid != null) {
            for (SysDept dept : entity.getEditPermittedDept()) {
                if (dept.getId().equals(editdeptid)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                SysDept dept = sysDeptDao.get(editdeptid);
                if (dept != null) {
                    entity.getEditPermittedDept().add(dept);
                }
            }
        }

        //添加用户修改权限
        flag = true;
        if (edituserid != null) {
            SysUser adduser = sysUserDao.get(edituserid);
            if (entity.getEditPermittedUser() != null) {
                for (SysUser user : entity.getEditPermittedUser()) {
                    if (user.getId().equals(edituserid)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    entity.getEditPermittedUser().add(adduser);
                }
            }
            else {
                Set<SysUser> editPermittedUsers = new HashSet<SysUser>();
                editPermittedUsers.add(adduser);
                entity.setEditPermittedUser(editPermittedUsers);
            }
        }

        //添加系统修改权限
        flag = true;
        if (editprivilegeid != null) {
            for (SysPrivilege privilege : entity.getEditPermittedPrivilege()) {
                if (privilege.getId().equals(editprivilegeid)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                SysPrivilege privilege = sysPrivilegeDao.get(editprivilegeid);
                if (privilege != null) {
                    entity.getEditPermittedPrivilege().add(privilege);
                }
            }
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

                //以下部分是获取该文档多有的权限信息
                try {
                    int viewmax = 0;

                    //下面三个if判断是为了获取将要显示在页面上的查看权限的行数
                    if (entity.getViewPermittedDept() != null && entity.getViewPermittedDept().size() > 0) {
                        viewmax = entity.getViewPermittedDept().size();
                    }
                    if (entity.getViewPermittedUser() != null && entity.getViewPermittedUser().size() > 0) {
                        if (viewmax < entity.getViewPermittedUser().size()) {
                            viewmax = entity.getViewPermittedUser().size();
                        }
                    }
                    if (entity.getViewPermittedPrivilege() != null && entity.getViewPermittedPrivilege().size() > 0) {
                        if (viewmax < entity.getViewPermittedPrivilege().size()) {
                            viewmax = entity.getViewPermittedPrivilege().size();
                        }
                    }

                    //获取该文档中的所有查看权限 将他们放到显示容器中
                    viewunit = new ArrayList<Map<String, Object>>();
                    for (int i = 0; i < viewmax; i++) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        if (entity.getViewPermittedDept() != null && i < entity.getViewPermittedDept().size()) {
                            map.put("sysdept", entity.getViewPermittedDept().toArray()[i]);
                        } else {
                            map.put("sysdept", new SysDept());
                        }

                        if (entity.getViewPermittedUser() != null && i < entity.getViewPermittedUser().size()) {
                            map.put("sysuser", entity.getViewPermittedUser().toArray()[i]);
                        } else {
                            map.put("sysuser", new SysUser());
                        }

                        if (entity.getViewPermittedPrivilege() != null &&
                                i < entity.getViewPermittedPrivilege().size()) {
                            map.put("sysprivilege", entity.getViewPermittedPrivilege().toArray()[i]);
                        } else {
                            map.put("sysprivilege", new SysPrivilege());
                        }
                        viewunit.add(map);
                        viewrow = viewmax + 1;
                    }

                    //以下是获取修改权限
                    editunit = new ArrayList<Map<String, Object>>();
                    int editmax = 0;
                    if (entity.getEditPermittedDept() != null && entity.getEditPermittedDept().size() > 0) {
                        editmax = entity.getEditPermittedDept().size();
                    }
                    if (entity.getEditPermittedUser() != null && entity.getEditPermittedUser().size() > 0) {
                        if (editmax < entity.getEditPermittedUser().size()) {
                            editmax = entity.getEditPermittedUser().size();
                        }
                    }
                    if (entity.getEditPermittedPrivilege() != null && entity.getEditPermittedPrivilege().size() > 0) {
                        if (editmax < entity.getEditPermittedPrivilege().size()) {
                            editmax = entity.getEditPermittedPrivilege().size();
                        }
                    }

                    for (int i = 0; i < editmax; i++) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        if (entity.getEditPermittedDept() != null && i < entity.getEditPermittedDept().size()) {
                            map.put("sysdept", entity.getEditPermittedDept().toArray()[i]);
                        } else {
                            map.put("sysdept", new SysDept());
                        }

                        if (entity.getEditPermittedUser() != null && i < entity.getEditPermittedUser().size()) {
                            map.put("sysuser", entity.getEditPermittedUser().toArray()[i]);
                        } else {
                            map.put("sysuser", new SysUser());
                        }

                        if (entity.getEditPermittedPrivilege() != null &&
                                i < entity.getEditPermittedPrivilege().size()) {
                            map.put("sysprivilege", entity.getEditPermittedPrivilege().toArray()[i]);
                        } else {
                            map.put("sysprivilege", new SysPrivilege());
                        }
                        editunit.add(map);
                        editrow = editmax + 1;
                    }
                } catch (Exception ignore) {
                }
            } else {
                entity = new DocCategory();
            }
        }
    }

    public DocCategory getModel() {
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
            DocCategory temp = null;
            Collection<DocCategory> col = null;

            long orderno = -1;
            if (entity.getParent() == null) {
                col = service.findByQuery("from DocCategory where parent is null");
            } else {
                col = entity.getParent().getChildren();
            }

            for (DocCategory d : col) {
                if (upoperation) {
                    if (entity.getOrderno() > d.getOrderno() && d.getOrderno() > orderno) {
                        temp = d;
                        orderno = d.getOrderno();
                    }
                } else {
                    if (entity.getOrderno() < d.getOrderno()) {
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
                long no1 = entity.getOrderno();
                long no2 = temp.getOrderno();
                entity.setOrderno(no2);
                temp.setOrderno(no1);
                service.save(entity);
                service.save(temp);
            }

        }
        return SUCCESS;
    }
}
