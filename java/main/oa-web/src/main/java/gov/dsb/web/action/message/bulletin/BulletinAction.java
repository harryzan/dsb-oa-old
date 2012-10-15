package gov.dsb.web.action.message.bulletin;

import gov.dsb.core.dao.BulletinDao;
import gov.dsb.core.dao.SysUserDao;
import gov.dsb.core.domain.Bulletin;
import gov.dsb.core.domain.SysUser;
import gov.dsb.core.struts2.CRUDActionSupport;
import gov.dsb.core.struts2.SimpleActionSupport;
import gov.dsb.web.security.UserSessionService;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: cxs
 * Date: 2010-4-1
 * Time: 14:11:53
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "bulletin-grid", type = "chain"),
        @Result(name = SimpleActionSupport.SUCCESS, location = "/WEB-INF/pages/common/ajaxutilData.jsp")})
public class BulletinAction extends CRUDActionSupport<Bulletin> {

    @Autowired
    private BulletinDao service;

    @Autowired
    private SysUserDao sysUserEntityService;

    @Autowired
    private UserSessionService userSessionService;

    protected Long id;

    private String viewuserids;

    private HashMap map;

    private String result;

    private String path;

    private Boolean bulletinstatus;

    private String gridParam;

    public void setPath(String path) {
        this.path = path;
    }

    public String getResult() {
        return result;
    }

    public HashMap getMap() {
        return map;
    }

    public void setViewuserids(String viewuserids) {
        this.viewuserids = viewuserids;
    }

    public Boolean getBulletinstatus() {
        return bulletinstatus;
    }

    public void setBulletinstatus(Boolean bulletinstatus) {
        this.bulletinstatus = bulletinstatus;
    }

    public String getGridParam() {
        return gridParam;
    }

    public void setGridParam(String gridParam) {
        this.gridParam = gridParam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String save() throws Exception {
        SysUser user = userSessionService.getCurrentSysUser();
        System.out.println("************ user.getDisplayname() = " + user.getDisplayname());
        entity.setAdduser(user);
//        entity.setStarttime(new Timestamp(System.currentTimeMillis()));
//        if (entity.getSysuserbulletins() != null) {
//            entity.getSysuserbulletins().clear();
//        } else {
//            entity.setSysuserbulletins(new ArrayList<SysUser>());
//        }

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
            } else {
                entity = new Bulletin();
            }
        }

    }

    public String view() throws Exception {
        return VIEW;
    }


    public String mainMsg() throws Exception {
        result = "";
        try {
            SysUser user = userSessionService.getCurrentSysUser();
            user = sysUserEntityService.get(user.getId());

            result += "<a style=\"cursor:pointer;\" onclick='goModule(\"结构预警结果\",\"f04\")'>更多结构预警信息>></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return SUCCESS;
    }

    public Bulletin getModel() {
        return entity;
    }

    public String changeStatus() throws Exception {
        if (id != null) {
            entity.setStatus(true);
            service.save(entity);
            System.out.println("enter change status!");
        }

        return null;
    }
}
