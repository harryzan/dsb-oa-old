package gov.dsb.web.action.system.syscode;

import gov.dsb.core.dao.SysCodeDao;
import gov.dsb.core.dao.SysCodeListDao;
import gov.dsb.core.domain.SysCode;
import gov.dsb.core.domain.SysCodeList;
import gov.dsb.core.struts2.CRUDActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-3
 * Time: 17:13:35
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "sys-code-list-view", type = "chain"),
        @Result(name = CRUDActionSupport.DELETE, location = "/common/blank", type = "redirect")})
public class SysCodeListAction extends CRUDActionSupport<SysCodeList> {

    @Autowired
    private SysCodeListDao service;

    @Autowired
    private SysCodeDao sysCodeEntityService;

    protected Long id;

    private Long syscodeid;

    private Long parentid;

    private String syscodecode;

    public String getSyscodecode() {
        return syscodecode;
    }

    public void setSyscodecode(String syscodecode) {
        this.syscodecode = syscodecode;
    }

    public Long getSyscodeid() {
        return syscodeid;
    }

    public void setSyscodeid(Long syscodeid) {
        this.syscodeid = syscodeid;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String save() throws Exception {
        if(syscodeid != null){
            SysCode code = sysCodeEntityService.get(syscodeid);
            entity.setSyscode(code);
        }
        if(parentid != null){
            SysCodeList list = service.get(parentid);
            entity.setParent(list);
            entity.setSyscode(list.getSyscode());
        }

        service.save(entity);
        return RELOAD;
    }

    public String delete() throws Exception {
        service.delete(id);
        return DELETE;
    }

    protected void prepareModel() throws Exception {
        if (entity == null) {
            if (id != null) {
                entity = service.get(id);
                syscodecode = entity.getSyscode().getCode();
            }
            else {
                entity = new SysCodeList();
            }
            if(syscodeid != null){
                syscodecode = sysCodeEntityService.get(syscodeid).getCode();
            }
            if(parentid != null){
                syscodecode = service.get(parentid).getSyscode().getCode();
            }
        }
    }

    public SysCodeList getModel() {
        return entity;
    }
}
