package gov.dsb.web.action.system.sysprivilege;

import gov.dsb.core.dao.SysCodeDao;
import gov.dsb.core.dao.SysCodeListDao;
import gov.dsb.core.dao.SysPrivilegeDao;
import gov.dsb.core.domain.SysCodeList;
import gov.dsb.core.domain.SysPrivilege;
import gov.dsb.core.struts2.CRUDActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-5
 * Time: 12:15:18
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "sys-privilege", type = "chain"),
     @Result(name = CRUDActionSupport.DELETE, location = "/common/blank", type = "redirect")})
public class SysPrivilegeAction extends CRUDActionSupport<SysPrivilege> {

    @Autowired
    private SysPrivilegeDao service;

    @Autowired
    private SysCodeDao sysCodeEntityService;

    @Autowired
    private SysCodeListDao sysCodeListEntityService;

    protected Long id;

    private Long parentid;

    private Long privilegetypeid;

    private Collection<SysCodeList> colprivilegetype;

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

    public Long getPrivilegetypeid() {
        return privilegetypeid;
    }

    public void setPrivilegetypeid(Long privilegetypeid) {
        this.privilegetypeid = privilegetypeid;
    }

    public Collection<SysCodeList> getColprivilegetype() {
        return colprivilegetype;
    }

    public void setColprivilegetype(Collection<SysCodeList> colprivilegetype) {
        this.colprivilegetype = colprivilegetype;
    }

    public String save() throws Exception {
        service.save(entity);

        return VIEW;
    }

    public String delete() throws Exception {
        service.delete(id);
        return DELETE;
    }


    public String view() throws Exception {
        return VIEW;
    }

    protected void prepareModel() throws Exception {
        if (entity == null) {
            if (id != null) {
                entity = service.get(id);
            }
            else {
                entity = new SysPrivilege();
            }
        }
        if (parentid != null) {
            SysPrivilege parent = service.get(parentid);
            entity.setParent(parent);
        }

        if (privilegetypeid != null) {
            SysCodeList privilegetype = sysCodeListEntityService.get(privilegetypeid);
            entity.setPrivilegetype(privilegetype);
        }


        colprivilegetype = sysCodeEntityService.findCodeList("privilegetype");
    }

    public SysPrivilege getModel() {
        return entity;
    }

    public String crudinput() throws Exception {
        return "crudinput";
    }

    public String crudsave() throws Exception {

        SysPrivilege privilegeD = new SysPrivilege();
        privilegeD.setParent(entity.getParent());
        privilegeD.setCode(entity.getCode() + "_D");
        privilegeD.setName(entity.getName() + "删除");
        privilegeD.setTag(entity.getTag());
        privilegeD.setDescription(entity.getDescription());
        privilegeD.setPrivilegetype(getPrivilegeType("D"));
        service.save(privilegeD);

        SysPrivilege privilegeR = new SysPrivilege();
        privilegeR.setParent(entity.getParent());
        privilegeR.setCode(entity.getCode() + "_R");
        privilegeR.setName(entity.getName() + "查看");
        privilegeR.setTag(entity.getTag() + 1);
        privilegeR.setDescription(entity.getDescription());
        privilegeR.setPrivilegetype(getPrivilegeType("R"));
        service.save(privilegeR);

        SysPrivilege privilegeU = new SysPrivilege();
        privilegeU.setParent(entity.getParent());
        privilegeU.setCode(entity.getCode() + "_U");
        privilegeU.setName(entity.getName() + "更新");
        privilegeU.setTag(entity.getTag() + 2);
        privilegeU.setDescription(entity.getDescription());
        privilegeU.setPrivilegetype(getPrivilegeType("U"));
        service.save(privilegeU);

        SysPrivilege privilegeC = new SysPrivilege();
        privilegeC.setParent(entity.getParent());
        privilegeC.setCode(entity.getCode() + "_C");
        privilegeC.setName(entity.getName() + "创建");
        privilegeC.setTag(entity.getTag() + 3);
        privilegeC.setDescription(entity.getDescription());
        privilegeC.setPrivilegetype(getPrivilegeType("C"));
        service.save(privilegeC);

        entity = privilegeD;


        return VIEW;
    }

    private SysCodeList getPrivilegeType(String type){
        for(SysCodeList list : colprivilegetype){
            if(list.getListcode().equals(type)){
                return list;
            }
        }
        return null;
    }
}
