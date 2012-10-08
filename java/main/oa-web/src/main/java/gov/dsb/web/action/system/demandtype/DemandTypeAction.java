package gov.dsb.web.action.system.demandtype;

import gov.dsb.core.dao.DemandTypeDao;
import gov.dsb.core.dao.SysUserDao;
import gov.dsb.core.domain.DemandType;
import gov.dsb.core.struts2.CRUDActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-5-15
 * Time: 10:57:24
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "demand-type-grid", type = "chain")})
public class DemandTypeAction extends CRUDActionSupport<DemandType>{

    @Autowired
    private DemandTypeDao service;

    @Autowired
    private SysUserDao sysUserDao;

    private String gridParam;

    public void setGridParam(String gridParam) {
        this.gridParam = gridParam;
    }

    protected Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private Long userid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String save() throws Exception {
        if (userid != null) {
            entity.setUser(sysUserDao.get(userid));
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
            }
            else {
                entity = new DemandType();
            }
        }
    }

    public DemandType getModel() {
        return entity;
    }

}
