package gov.dsb.web.action.oa.demand;

import gov.dsb.core.dao.DemandDao;
import gov.dsb.core.dao.DemandTypeDao;
import gov.dsb.core.domain.Demand;
import gov.dsb.core.domain.DemandType;
import gov.dsb.core.struts2.CRUDActionSupport;
import gov.dsb.web.security.UserSession;
import gov.dsb.web.security.UserSessionService;
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
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "demand-use-grid", type = "chain")})
public class DemandAction extends CRUDActionSupport<Demand>{

    @Autowired
    private DemandDao service;

    @Autowired
    private UserSessionService userSessionService;

    @Autowired
    private DemandTypeDao demandTypeDao;

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
    
    private Long typeid;

    public Long getTypeid() {
        return typeid;
    }

    public void setTypeid(Long typeid) {
        this.typeid = typeid;
    }

    private DemandType type;

    public DemandType getType() {
        return type;
    }

    public void setType(DemandType type) {
        this.type = type;
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
                entity = new Demand();
            }
        }
    }

    public Demand getModel() {
        return entity;
    }

    public String main(){
        if (typeid != null) {
            UserSession userSession = userSessionService.getUserSession();
            userSession.set("typeid", typeid);
            userSessionService.putUserSession(userSession);
            type = demandTypeDao.get(typeid);
        }
        return "main";
    }

    public String tab(){
        UserSession userSession = userSessionService.getUserSession();
        typeid = (Long) userSession.get("typeid");
        type = demandTypeDao.get(typeid);

        return "tab";
    }
}
