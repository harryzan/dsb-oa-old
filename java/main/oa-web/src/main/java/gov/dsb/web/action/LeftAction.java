package gov.dsb.web.action;

import gov.dsb.core.dao.DemandTypeDao;
import gov.dsb.core.dao.SysPrivilegeDao;
import gov.dsb.core.dao.SysUserDao;
import gov.dsb.core.dao.base.Page;
import gov.dsb.core.domain.DemandType;
import gov.dsb.core.domain.SysCodeList;
import gov.dsb.core.domain.SysPrivilege;
import gov.dsb.core.domain.SysUser;
import gov.dsb.core.struts2.PageActionSupport;
import gov.dsb.core.utils.Nulls;
import gov.dsb.core.utils.StringHelp;
import gov.dsb.web.security.UserSessionService;
import gov.dsb.web.ui.grid.Grid;
import gov.dsb.web.ui.grid.QueryTranslate;
import gov.dsb.web.ui.grid.Row;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-5
 * Time: 10:17:16
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = PageActionSupport.GRIDDATA, location = "/WEB-INF/pages/common/gridData.jsp")})
public class LeftAction extends PageActionSupport<DemandType> {

    @Autowired
    private DemandTypeDao service;

    @Autowired
    private UserSessionService userSessionService;

    @Autowired
    private SysPrivilegeDao sysPrivilegeEntityService;

    @Autowired
    private SysUserDao sysUserEntityService;

    private List<Map<String, Object>> loginmenus;

    private SysUser loginUser;

    public Collection<Map<String, Object>> getLoginmenus() {
        return loginmenus;
    }

    public SysUser getLoginUser() {
        return loginUser;
    }

    private String systime;

    public String getSystime() {
        return systime;
    }
    private List<DemandType> types;

    public List<DemandType> getTypes() {
        return types;
    }

    public void setTypes(List<DemandType> types) {
        this.types = types;
    }

    public void prepare() throws Exception {

    }

    public String list() throws Exception {
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        types = service.findAll();

        loginUser = userSessionService.getCurrentSysUser();

        Collection<SysPrivilege> privileges = sysUserEntityService.getUsePrivileges(loginUser.getId());
//        Collection<SysRole> userRole = user.getSysroleusers();
//        for (SysRole role : userRole) {
//            privileges.addAll(role.getSysprivilegeroles());
//        }

        loginmenus = new ArrayList<Map<String, Object>>();
        Collection<SysPrivilege> sysprivileges =
                sysPrivilegeEntityService.findByQuery("from SysPrivilege where privilegetype.listcode=? order by tag", SysCodeList.PRIVILEGETYPE_MENU);

        for (SysPrivilege privilege : sysprivileges) {
            if (privilege.getParent() == null && containPrivilege(privileges, privilege)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("mainmenu", privilege);
                List<SysPrivilege> submenus = new ArrayList<SysPrivilege>();
                for (SysPrivilege p : privilege.getChildren()) {
                    if (containPrivilege(privileges, p)) {
                        submenus.add(p);
                    }
                }
                Collections.sort(submenus, new Comparator<SysPrivilege>() {
                    public int compare(SysPrivilege p1, SysPrivilege p2) {
                        return p1.getOrderno().compareTo(p2.getOrderno());
                    }
                });
                map.put("submenus", submenus);
                loginmenus.add(map);
            }
        }
        Collections.sort(loginmenus, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> m1, Map<String, Object> m2) {
                SysPrivilege p1 = (SysPrivilege) m1.get("mainmenu");
                SysPrivilege p2 = (SysPrivilege) m2.get("mainmenu");
                return p1.getOrderno().compareTo(p2.getOrderno());
            }
        });

        return super.execute();    //To change body of overridden methods use File | Settings | File Templates.
    }

    private boolean containPrivilege(Collection<SysPrivilege> privileges, SysPrivilege privilege){
        for(SysPrivilege p : privileges){
            if(p.getId().equals(privilege.getId())){
                return true;
            }
        }


        return false;
    }
}