package gov.dsb.web.action.system.sysuser;

import gov.dsb.core.dao.*;
import gov.dsb.core.domain.*;
import gov.dsb.core.struts2.CRUDActionSupport;
import gov.dsb.core.utils.CryptUtil;
import gov.dsb.web.security.UserSessionService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-3
 * Time: 14:12:01
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "sys-user", type = "chain"),
 @Result(name = CRUDActionSupport.DELETE, location = "/common/blank", type = "redirect")})
public class SysUserAction extends CRUDActionSupport<SysUser> {

    @Autowired
    private SysUserDao service;

    @Autowired
    private SysDeptDao sysDeptEntityService;

    @Autowired
    private SysRoleDao sysRoleEntityService;

    @Autowired
    private SysPrivilegeDao sysPrivilegeEntityService;

    @Autowired
    private UserSessionService userSessionService;

    @Autowired
    private SysUserPrivilegeDao sysUserPrivilegeEntityService;

    @Autowired
    private SysLogDao sysLogEntityService;

    protected Long id;

    private Long sysdeptid;

    private Integer savetype;

    private Collection<Map<String, Object>> colsysrole;

    private Long[] sysroleids;

    private Long privilegeid;

    private String type;

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSysdeptid() {
        return sysdeptid;
    }

    public void setSysdeptid(Long sysdeptid) {
        this.sysdeptid = sysdeptid;
    }

    public Integer getSavetype() {
        return savetype;
    }

    public void setSavetype(Integer savetype) {
        this.savetype = savetype;
    }

    public Collection<Map<String, Object>> getColsysrole() {
        return colsysrole;
    }

    public Long[] getSysroleids() {
        return sysroleids;
    }

    public void setSysroleids(Long[] sysroleids) {
        this.sysroleids = sysroleids;
    }

    public Long getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(Long privilegeid) {
        this.privilegeid = privilegeid;
    }

    public String save() throws Exception {
        if(sysdeptid != null){
            SysDept sysdept = sysDeptEntityService.get(sysdeptid);
            entity.setSysdept(sysdept);
            if(entity.getSysroleusers() != null && entity.getSysroleusers().size() > 0){
                //先将以前该用户拥有的所有角色删除 然后再添加
                entity.getSysroleusers().clear();
            }
            else if (entity.getSysroleusers() == null){
                entity.setSysroleusers(new ArrayList<SysRole>());
            }
            if(sysroleids != null && sysroleids.length > 0) {
                for(Long sysroleid : sysroleids){
                    SysRole sysrole = sysRoleEntityService.get(sysroleid);
                    if(sysrole != null){
                        entity.getSysroleusers().add(sysrole);
                    }
                }
            }

            entity.setPassword(CryptUtil.cl_encrypt(entity.getPassword()));
            service.save(entity);
        }


        return VIEW;
    }

    public String changestatus() throws Exception {

        service.save(entity);

        return VIEW;
    }

    public String delete() throws Exception {
        Collection<SysLog> logs = entity.getSyslogs();

        if(logs != null && logs.size() > 0){
            throw new RuntimeException("该用户不能被删除！");
        }

//        Collection<Bulletin> bulletins = entity.getBulletinusers();
//        if(bulletins != null && bulletins.size()> 0) {
//            throw new RuntimeException("该用户不能被删除！");
//        }

        entity.getSysroleusers().clear();
        Collection<SysUserPrivilege> userPrivileges = entity.getSysuserprivileges();
        for(SysUserPrivilege userPrivilege : userPrivileges){
            sysUserPrivilegeEntityService.delete(userPrivilege);
        }
//        entity.getBulletinusers().clear();

        service.save(entity);

        service.delete(id);
        return DELETE;
    }

    public String changeDept() throws Exception {

        if(sysdeptid != null){
            SysDept dept = sysDeptEntityService.get(sysdeptid);
            entity.setSysdept(dept);
            service.save(entity);
        }
        else {
            throw new RuntimeException();
        }


        return RELOAD;
    }

    protected void prepareModel() throws Exception {
        if (entity == null) {
            if (id != null) {
                entity = service.get(id);
            }
            else {
                entity = new SysUser();
            }
        }

        //系统角色的初始化
        Collection<SysRole> colrole = sysRoleEntityService.findAll();
        colsysrole = new ArrayList<Map<String, Object>>();
        //Map<Long, UserRoleUtil> map = new HashMap<Long, UserRoleUtil>();
        for(SysRole sysrole : colrole){
            //系统中定义的角色
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("sysrole", sysrole);
            map.put("isnull", false);
            map.put("checked", false);
            if(entity.getSysroleusers() != null && entity.getSysroleusers().contains(sysrole)){
                map.put("checked", true);
            }

            colsysrole.add(map);
        }
        int nullvalue = 0;
        if(colsysrole.size() % 4 != 0){
            nullvalue = 4 - colsysrole.size() % 4;
        }
        if(nullvalue != 0){
            for(int i = 0; i < nullvalue; i++){
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("sysrole", null);
                map.put("isnull", true);
                map.put("checked", false);

                colsysrole.add(map);
            }
        }
    }

    public SysUser getModel() {
        return entity;
    }

    private String updatepwd;

    public String getUpdatepwd() {
        return updatepwd;
    }

    public void setUpdatepwd(String updatepwd) {
        this.updatepwd = updatepwd;
    }

    public String saveupdate() throws Exception {
        if(sysdeptid != null){
            SysDept sysdept = sysDeptEntityService.get(sysdeptid);
            entity.setSysdept(sysdept);
            if(entity.getSysroleusers() != null && entity.getSysroleusers().size() > 0){
                //先将以前该用户拥有的所有角色删除 然后再添加
                entity.getSysroleusers().clear();
            }
            else if (entity.getSysroleusers() == null){
                entity.setSysroleusers(new ArrayList<SysRole>());
            }
            if(sysroleids != null && sysroleids.length > 0) {
                for(Long sysroleid : sysroleids){
                    SysRole sysrole = sysRoleEntityService.get(sysroleid);
                    if(sysrole != null){
                        entity.getSysroleusers().add(sysrole);
                    }
                }
            }
            if(updatepwd != null && updatepwd.length() > 0){
                entity.setPassword(CryptUtil.cl_encrypt(updatepwd));
            }
            service.save(entity);
        }

        return VIEW;
    }

    public String update(){
        return "update";
    }

    public String clearpassword() throws Exception {
        
        if(entity != null){
            entity.setPassword(null);
            service.save(entity);
        }

        return RELOAD;
    }

    public String blank() throws Exception {
        return "blank";
    }

    public String saveprivilege() throws Exception {
        Collection<SysPrivilege> sysprivileges = service.getUserprivileges(id);
        if(savetype == 0){
            SysPrivilege sysprivilege = sysPrivilegeEntityService.get(privilegeid);

            if(!containPrivilege(sysprivileges, sysprivilege)){
                SysUserPrivilege sysUserPrivilege = new SysUserPrivilege();
                sysUserPrivilege.setSysprivilege(sysprivilege);
                sysUserPrivilege.setIsdeny(true);
                sysUserPrivilege.setSysuser(entity);
                sysUserPrivilegeEntityService.save(sysUserPrivilege);
            }
            else {
                SysUserPrivilege userPrivilege = sysUserPrivilegeEntityService.findUnique("from SysUserPrivilege where sysuser.id=? and sysprivilege.id=?", id, privilegeid);
                if(userPrivilege != null){
                    userPrivilege.setIsdeny(true);
                    sysUserPrivilegeEntityService.save(userPrivilege);
                }
            }
        }
        else if(savetype == 1){
            if(type == null){
                initChildren(privilegeid);
            }
            else {
                initTypeChildren(privilegeid, type);
            }

            for(SysPrivilege sp : colchildren){
                if(!containPrivilege(sysprivileges, sp)){
                    SysUserPrivilege sysUserPrivilege = new SysUserPrivilege();
                    sysUserPrivilege.setSysprivilege(sp);
                    sysUserPrivilege.setIsdeny(true);
                    sysUserPrivilege.setSysuser(entity);
                    sysUserPrivilegeEntityService.save(sysUserPrivilege);
                }
                else {
                    SysUserPrivilege userPrivilege = sysUserPrivilegeEntityService.findUnique(
                            "from SysUserPrivilege where sysuser.id=? and sysprivilege.id=?", id, privilegeid);
                    if (userPrivilege != null) {
                        userPrivilege.setIsdeny(true);
                        sysUserPrivilegeEntityService.save(userPrivilege);
                    }
                }
            }
        }
        else if(savetype == 2){
            remove(id, privilegeid);
        }
        else if(savetype == 3){
            if(type == null){
                initChildren(privilegeid);
            }
            else {
                initTypeChildren(privilegeid, type);
            }
            for(SysPrivilege sp : colchildren){
                remove(id, sp.getId());
            }
        }
        else if(savetype == 4){
            forbid(id, privilegeid);
        }
        else if (savetype == 5){
            if(type == null){
                initChildren(privilegeid);
            }
            else {
                initTypeChildren(privilegeid, type);
            }
            for(SysPrivilege sp : colchildren){
                forbid(id, sp.getId());
            }
        }

        return "stage";
    }

    public String view() throws Exception {
        return VIEW;
    }


    //===========重设密码=========//
    public String newpwd;

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String savepwd(){

        SysUser user = userSessionService.getCurrentSysUser();
        user.setPassword(CryptUtil.cl_encrypt(newpwd));

        service.save(user);

        SysLog sysLog = new SysLog();

        sysLog.setSysuser(user);
        sysLog.setContent("修改密码");
        sysLog.setLogtime(new Timestamp(System.currentTimeMillis()));
        String ip = ServletActionContext.getRequest().getRemoteAddr();
        sysLog.setIpaddress(ip);

        sysLogEntityService.save(sysLog);

        return "updateresult";
    }

    public String updatepwd() throws Exception{
        return "updatepwd";
    }

    private Collection<SysPrivilege> colchildren;

    private void initChildren(Long parentid){
        if(colchildren == null){
            colchildren =  new ArrayList<SysPrivilege>();
        }
        SysPrivilege sysprivilege = null;
        if(parentid != null){
            sysprivilege = sysPrivilegeEntityService.get(parentid);
        }
        if(sysprivilege != null){
            colchildren.add(sysprivilege);
            for(SysPrivilege s : sysprivilege.getChildren()){
                initChildren(s.getId());
            }
        }

    }
    private void initTypeChildren(Long parentid, String type){
        if(colchildren == null){
            colchildren =  new ArrayList<SysPrivilege>();
        }
        SysPrivilege sysprivilege = null;
        if(parentid != null){
            sysprivilege = sysPrivilegeEntityService.get(parentid);
        }
        if(sysprivilege != null){
            if(sysprivilege.getPrivilegetype() != null && type.equals(sysprivilege.getPrivilegetype().getListcode())){
                colchildren.add(sysprivilege);
            }
            for(SysPrivilege s : sysprivilege.getChildren()){
                initTypeChildren(s.getId(), type);
            }
        }

    }

    private boolean containPrivilege(Collection<SysPrivilege> privileges, SysPrivilege privilege){
       return containPrivilege(privileges, privilege.getId());
    }

    private boolean containPrivilege(Collection<SysPrivilege> privileges, Long privilegeid){
       for(SysPrivilege p : privileges){
           if(p.getId().equals(privilegeid)){
               return true;
           }
       }


        return false;
    }

    private void forbid(Long userId, Long privilegeId){
        Collection<SysUserPrivilege> entities = sysUserPrivilegeEntityService.findByQuery("from SysUserPrivilege where sysuser.id=? and sysprivilege.id=?", userId, privilegeId);
//        System.out.println(entities);
        for(SysUserPrivilege e : entities){
            e.setIsdeny(false);
            sysUserPrivilegeEntityService.save(e);
//            System.out.println("contain one");
        }
        if(entities.size() <= 0){
//            System.out.println("no contain!");
            SysUserPrivilege entity = new SysUserPrivilege();

            entity.setSysprivilege(sysPrivilegeEntityService.get(privilegeId));
            entity.setIsdeny(false);
            entity.setSysuser(service.get(userId));

            sysUserPrivilegeEntityService.save(entity);

        }
    }

    private void remove(Long userId, Long privilegeId){
        Collection<SysUserPrivilege> entities = sysUserPrivilegeEntityService.findByQuery("from SysUserPrivilege where sysuser.id=? and sysprivilege.id=?", userId, privilegeId);
        for(SysUserPrivilege entity : entities){
            sysUserPrivilegeEntityService.delete(entity);
        }
    }
}
