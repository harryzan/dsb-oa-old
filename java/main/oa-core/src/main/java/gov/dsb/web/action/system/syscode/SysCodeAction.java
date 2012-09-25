package gov.dsb.web.action.system.syscode;

import gov.dsb.core.dao.SysCodeDao;
import gov.dsb.core.domain.SysCode;
import gov.dsb.core.struts2.CRUDActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-3
 * Time: 13:47:22
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "sys-code-view", type = "chain"),
        @Result(name = CRUDActionSupport.DELETE, location = "/common/blank", type = "redirect")})
public class SysCodeAction extends CRUDActionSupport<SysCode> {

    @Autowired
    private SysCodeDao service;

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String save() throws Exception {
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
            }
            else {
                entity = new SysCode();
            }
        }
    }

    public SysCode getModel() {
        return entity;
    }
}
