package gov.dsb.web.action.system.syslog;

import gov.dsb.core.dao.SysLogDao;
import gov.dsb.core.domain.SysLog;
import gov.dsb.core.struts2.CRUDActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-3
 * Time: 14:29:54
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "", type = "redirect")})
public class SysLogAction extends CRUDActionSupport<SysLog> {

    @Autowired
    private SysLogDao service;

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
        return RELOAD;
    }

    protected void prepareModel() throws Exception {
        if (entity == null) {
            if (id != null) {
                entity = service.get(id);
            }
            else {
                entity = new SysLog();
            }
        }
    }

    public SysLog getModel() {
        return entity;
    }
}
