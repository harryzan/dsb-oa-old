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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-5-15
 * Time: 10:57:24
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "demand-use-grid", type = "chain")})
public class DemandCheckAction extends CRUDActionSupport<Demand>{

    @Autowired
    private DemandDao service;


    @Autowired
    private DemandTypeDao demandTypeDao;

    @Autowired
    private UserSessionService userSessionService;

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

    private DemandType type;

    public DemandType getType() {
        return type;
    }

    public void setType(DemandType type) {
        this.type = type;
    }

    public String save() throws Exception {
//        System.out.println("********************** carid = " + carid);

//        UserSession userSession = userSessionService.getUserSession();
//        Long typeid= typeid = (Long) userSession.get("typeid");
//        if (typeid != null){
//            type = demandTypeDao.get(typeid);
//            entity.setType(type);
//        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String day = sdf.format(d);
        entity.setCheckdate(day);
        entity.setChecker(userSessionService.getCurrentSysUser());
        entity.setStatus(true);
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
                entity.setStatus(false);
            }
        }
    }

    public Demand getModel() {
        return entity;
    }

}
