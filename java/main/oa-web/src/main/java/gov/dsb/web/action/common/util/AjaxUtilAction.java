package gov.dsb.web.action.common.util;

import com.justone.core.struts2.SimpleActionSupport;
import com.justone.core.utils.CryptUtil;
import gov.dsb.core.dao.SysLogDao;
import gov.dsb.core.dao.SysPrivilegeDao;
import gov.dsb.core.dao.SysUserDao;
import gov.dsb.core.domain.SysLog;
import gov.dsb.core.domain.SysPrivilege;
import gov.dsb.core.domain.SysRole;
import gov.dsb.core.domain.SysUser;
import gov.dsb.web.security.UserSession;
import gov.dsb.web.security.UserSessionService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: xiejiao
 * Date: 2009-11-17
 * Time: 16:19:26
 */
@ParentPackage("default")
@Results({@Result(name = SimpleActionSupport.SUCCESS, location = "/WEB-INF/pages/common/ajaxutilData.jsp")})
public class AjaxUtilAction extends SimpleActionSupport {

    @Autowired
    private SysUserDao sysUserEntityService;

    @Autowired
    private UserSessionService userSessionService;

    @Autowired
    private SysLogDao sysLogEntityService;

    @Autowired
    private SysPrivilegeDao sysPrivilegeEntityService;


    private String result;

    private Long entityId;

    public String getResult() {
        return result;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    //===============common attribute setting up=================//


    public String execute() throws Exception {

        return SUCCESS;
    }

    //==========unique user loginname===========//
    private String loginname;

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    /**
     * 用来验证登录用户名是否已被注册，结合entityId来进行验证
     * author :  xj
     *
     * @return SUCCESS
     */
    public String uniqueloginname() {
        // 根据用户名来查询系统用户，若找不到结果则用户名可用，
        // 若找到结果，但其id不是当前用户（entityId）,则用户名不可用，
        // 若是当前用户，则用户名也是表示为可用

        Collection<SysUser> users = sysUserEntityService.findByProperty("loginname", loginname);
        System.out.println("************************* users.size() = " + users.size());
        if (users != null && users.size() == 1) {
            SysUser user = users.iterator().next();
            if (user.getId().equals(entityId)) {
                result = "true";
            } else if (user.getStatus() == null || !user.getStatus()) {
                result = "true|<id>" + user.getId() + "</id><status>" + user.getStatus() + "</status><displayname>" +
                        user.getDisplayname() + "</displayname><roleids>";
                Collection<SysRole> roles = user.getSysroleusers();
                for (SysRole role : roles) {
                    result += role.getId() + ",";
                }
                if (result.endsWith(",")) {
                    result = result.substring(0, result.length() - 1);
                }
                result += "</roleids>";
            } else {
                result = "false";
            }
        } else {
            result = "true";
        }

        return SUCCESS;
    }


    //===================right user old password==================//

    public String userpwd;

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    /**
     * 验证当前登录用户的原密码是否正确
     *
     * @return result
     * @throws Exception 。
     */
    public String checkuserpwd() throws Exception {
        SysUser entity = userSessionService.getCurrentSysUser();
        if (entity != null) {
            boolean flag = CryptUtil.cl_decrypt(entity.getPassword()).equals(userpwd);
            result = flag + "";
        } else {
            result = "false";
        }

        return SUCCESS;
    }


    //============================logger==========================//
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 撰写日志
     *
     * @return 。
     * @throws Exception 。
     */
    public String logger() throws Exception {

        SysUser user = userSessionService.getCurrentSysUser();

        SysLog sysLog = new SysLog();
        sysLog.setSysuser(user);
        sysLog.setLogtime(new Timestamp(System.currentTimeMillis()));

        if (entityId != null) {
            SysPrivilege sysPrivilege = sysPrivilegeEntityService.get(entityId);
            sysLog.setSysprivilege(sysPrivilege);
            sysLog.setContent(sysPrivilege.getName());

            //////////////////
            UserSession userSession = (UserSession) ServletActionContext.getRequest().getSession().getAttribute(
                    UserSession.SESSION_USERSESSION);
            userSession.setLastVisitDate(new Timestamp(System.currentTimeMillis()));
            userSession.set("LastActName", sysPrivilege.getName());

        }

        HttpServletRequest request = ServletActionContext.getRequest();
        sysLog.setIpaddress(request.getRemoteAddr());

        sysLogEntityService.save(sysLog);

        return SUCCESS;
    }


    //=================has privilege=================//

    private String privilegecode;

    public void setPrivilegecode(String privilegecode) {
        this.privilegecode = privilegecode;
    }

    /**
     * 判断当前登录用户是否拥有对应权限代码的操作权限 没有返回false 有返回权限描述
     *
     * @return privilege definition
     * @throws Exception .
     */
    public String hasprivilege() throws Exception {
        result = "false";

        if (privilegecode != null) {
            SysUser user = userSessionService.getCurrentSysUser();
            SysPrivilege privilege = sysPrivilegeEntityService.findUniqueByProperty("code", privilegecode);
            if (privilege != null) {
                sysUserEntityService.containPrivilege(user.getId(), privilege.getId());
                Collection<SysPrivilege> privileges = sysUserEntityService.getUsePrivileges(user.getId());
                if (privileges.contains(privilege)) {
                    result = privilege.getDefinition() + "," + privilege.getId();
                }
            }
        }


        return SUCCESS;
    }

    public String hasPrivileges() throws Exception {
        result = "{'Results':{";
        // privilege code : is forbid
        if (privilegecode != null) {
            String[] pCodes = privilegecode.split(",");
            SysUser user = userSessionService.getCurrentSysUser();
            Collection<SysPrivilege> privileges = sysUserEntityService.getUsePrivileges(user.getId());

            for (String code : pCodes) {

                SysPrivilege privilege = sysPrivilegeEntityService.findUniqueByProperty("code", code);
                if (privilege != null) {
                    String temp = "'" + code + "':true,";
                    for (SysPrivilege p : privileges) {
                        if (p.getCode().equals(code)) {
                            temp = "'" + code + "':false,";
                            break;
                        }
                    }
                    result += temp;
                } else {
                    // default is not forbid, if not define the privilege, system will allow to access
                    result += "'" + code + "':false,";
                }
            }
        }
        if (result.endsWith(",")) {
            result = result.substring(0, result.length() - 1);
        }
        result += "}}";
        return SUCCESS;
    }


    /**
     * 主页面获取实时滚动信息
     *
     * @return
     * @throws Exception 。
     */
//    public String mainMsg() throws Exception {
//        result = "";
//        try {
//            Collection<MonitorProj> projs = monitorProjEntityService.findByQuery("from MonitorProj where acquiremode.listcode=?", SysCodeList.ACQUIREMODE_MANUAL);
//            MonitorProj monitorProj = null;
//
//            for (MonitorProj proj : projs) {
//                long period = TimeHelper.dtString2DtLong(proj.getCheckperiod());
//
//                Collection<ManualReport> reports = manualReportEntityService.findByQuery("from ManualReport where monitorproj.id = ? order by checktime desc", proj.getId());
//
//                if (reports == null || reports.size() <= 0) {
//                    result += "人工检测项目-" + proj.getName() + "&nbsp;尚未填写过人工检测报告！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
//                } else {
//                    ManualReport report = reports.iterator().next();
//                    Timestamp nexttime = new Timestamp(report.getChecktime().getTime() + period);
//
//                    if (nexttime.before(new Timestamp(System.currentTimeMillis()))) {
//                        result += "人工检测项目-" + proj.getName() + "&nbsp;应该在" + nexttime + "时填写人工检测报告！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
//                    } else if (nexttime.before(new Timestamp(System.currentTimeMillis() + 10 * 24 * 3600000))) {   // 10天内的进行提醒
//                        result += "人工检测项目-" + proj.getName() + "&nbsp;需要在" + nexttime + "时填写人工检测报告！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
//                    }
//                }
//            }
//
//
//            Collection<StructureAlarmType> types = structureAlarmTypeEntityService.findAll();
//
//            for (StructureAlarmType type : types) {
//                Collection<StructureAlarmTypeResult> results = structureAlarmTypeResultEntityService.findByQuery("from StructureAlarmTypeResult where structurealarmtype.id=? order by starttime", type.getId());
//
//                if (results != null && results.size() > 0) {
//                    StructureAlarmTypeResult result = results.iterator().next();
//                    if (result.getStarttime().after(new Timestamp(System.currentTimeMillis() - 2 * 24 * 3600000))) {    // 显示两天内的结构预警结果
//                        this.result += "结构预警类型-" + type.getName() + "在" + result.getStarttime() + "&nbsp;发生报警&nbsp;" + result.getName() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
//                    }
//                }
//            }
//
//        } catch (Exception ignore) {
//            ignore.printStackTrace();
//        }
//        return SUCCESS;
//    }


    private Timestamp startTime;

    private Timestamp endTime;

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getDurantDay() throws Exception {
        if (startTime != null && endTime != null) {
            long durant = endTime.getTime() - startTime.getTime();
            long days = durant / (24 * 3600000);
            if (durant % (24 * 3600000) > 0) {
                days += 1;
            }
            result = days + "";
        }
        return SUCCESS;
    }

    public static void main(String[] args) {
        String result = "{\"Wind\":{\"speed\":\"12\",\"scale\":\"四\",\"wizard\":\"SE\"}}";
        System.out.println(result);

        result = "[0]sssss";
        String s = result.substring(result.indexOf("[") + 1, result.indexOf("]"));
        System.out.println("s = " + s);

        System.out.println("result = " + result.substring(result.indexOf("]") + 1));
    }


}
