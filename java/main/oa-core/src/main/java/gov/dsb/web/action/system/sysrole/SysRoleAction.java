package gov.dsb.web.action.system.sysrole;

import gov.dsb.core.dao.SysPrivilegeDao;
import gov.dsb.core.dao.SysRoleDao;
import gov.dsb.core.domain.SysPrivilege;
import gov.dsb.core.domain.SysRole;
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
 * Date: 2009-7-5
 * Time: 15:53:58
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "sys-role", type = "chain"),
     @Result(name = CRUDActionSupport.DELETE, location = "/common/blank", type = "redirect")})
public class SysRoleAction extends CRUDActionSupport<SysRole> {

    @Autowired
    private SysRoleDao service;

    @Autowired
    private SysPrivilegeDao sysPrivilegeEntityService;

    private Long sysprivilegeid;

    private Integer savetype;

    protected Long id;

    private String type;

    private Collection<SysUser> sysRoleUsers;

    public Collection<SysUser> getSysRoleUsers() {
        return sysRoleUsers;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSysprivilegeid() {
        return sysprivilegeid;
    }

    public void setSysprivilegeid(Long sysprivilegeid) {
        this.sysprivilegeid = sysprivilegeid;
    }

    public Integer getSavetype() {
        return savetype;
    }

    public void setSavetype(Integer savetype) {
        this.savetype = savetype;
    }

    public String save() throws Exception {
        service.save(entity);
        return VIEW;
    }

    public String delete() throws Exception {
        service.delete(id);
        return DELETE;
    }

    protected void prepareModel() throws Exception {
        if (entity == null) {
            if (id != null) {
                entity = service.get(id);
                sysRoleUsers = entity.getSysuserroles();
            }
            else {
                entity = new SysRole();
            }
        }
    }

    public SysRole getModel() {
        return entity;
    }

    public String view() throws Exception {
        return VIEW;
    }

    public String saveprivilege() throws Exception {

        if(savetype == null){
            return VIEW;
        }
        if(savetype == 0){
            SysPrivilege sysprivilege = sysPrivilegeEntityService.get(sysprivilegeid);
            entity.getSysprivilegeroles().add(sysprivilege);
            service.save(entity);
        }
        else if(savetype == 1){
            Map<Long, SysPrivilege> map = new HashMap<Long, SysPrivilege>();
            for(SysPrivilege s : entity.getSysprivilegeroles()){
                map.put(s.getId(), s);
            }
            colchildren = new ArrayList<SysPrivilege>();
            if(type != null){
                initTypeChildren(sysprivilegeid, type);
            }
            else {
                initChildren(sysprivilegeid);
            }
            for(SysPrivilege s : colchildren){
                if(!map.containsKey(s.getId())){
                    entity.getSysprivilegeroles().add(s);
                }
            }

            service.save(entity);
        }
        else if(savetype == 2){
            for(SysPrivilege sysprivilege : entity.getSysprivilegeroles()){
                if(sysprivilegeid.equals(sysprivilege.getId())){
                    entity.getSysprivilegeroles().remove(sysprivilege);
                    service.save(entity);
                    break;
                }
            }
        }
        else if(savetype == 3){
            Map<Long, SysPrivilege> map = new HashMap<Long, SysPrivilege>();
            for(SysPrivilege s : entity.getSysprivilegeroles()){
                map.put(s.getId(), s);
            }
            colchildren = new ArrayList<SysPrivilege>();
            if(type != null){
                initTypeChildren(sysprivilegeid, type);
            }
            else {
                initChildren(sysprivilegeid);
            }
            for(SysPrivilege s : colchildren){
                if(map.containsKey(s.getId())){
                    entity.getSysprivilegeroles().remove(map.get(s.getId()));
                }
            }

            service.save(entity);
        }

        return "stage";
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
}
