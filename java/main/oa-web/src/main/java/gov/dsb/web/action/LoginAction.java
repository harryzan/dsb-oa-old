package gov.dsb.web.action;

import gov.dsb.core.dao.SysLogDao;
import gov.dsb.core.domain.SysLog;
import gov.dsb.core.domain.SysUser;
import gov.dsb.core.struts2.SimpleActionSupport;
import gov.dsb.web.security.UserSession;
import gov.dsb.web.security.UserSessionService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-10-16
 * Time: 16:12:12
 * To change this template use File | Settings | File Templates.
 */
@ParentPackage("default")
@Results({@Result(name = SimpleActionSupport.SUCCESS, location = "/default", type = "redirect"),
        @Result(name = "failed", location = "/", type = "redirect")})
public class LoginAction extends SimpleActionSupport {

    @Autowired
    private UserSessionService userSessionService;

    @Autowired
    private SysLogDao sysLogEntityService;

    private String loginname;

    private String loginpass;

//    private String url;


    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass;
    }

//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getUrl(){
//        return url;
//    }

    //    private Collection<Map<String, Object>> loginmenus;
//
//    public Collection<Map<String, Object>> getLoginmenus() {
//        return loginmenus;
//    }

    public String execute() throws Exception {

//        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        HttpSession session = request.getSession(true);

//        SysUser user = service.login(loginname, loginpass);
        System.out.println("loginname = " + loginname);
        if (userSessionService.verify(loginname, loginpass)) {
            UserSession userSession = userSessionService.login(loginname);
            System.out.println("********** userSession.getUserId() = " + userSession.getUserId());

            if (request.getHeader("x-forwarded-for") == null) {
                userSession.set("UserIp",request.getRemoteAddr());
            }else{
                userSession.set("UserIp",request.getHeader("x-forwarded-for"));
            }
            session.setAttribute(UserSession.SESSION_USERSESSION, userSession);

            userSessionService.getCurrentSysUser();
            logger("登录系统", userSessionService.getCurrentSysUser());
            // for opma method 2011.2.28 xj
//            if(url != null && !url.trim().equals("")) {
//                return "/default";
//            }
            return SUCCESS;
        }
        else {
            throw new RuntimeException("用户名或密码错误！");
//            return "failed";
        }

    }

    public String logout() {
        HttpServletRequest request = ServletActionContext.getRequest();

        HttpSession session = request.getSession(true);

        session.setAttribute(UserSession.SESSION_USERSESSION, null);
        logger("退出系统", userSessionService.getCurrentSysUser());

        return "failed";        
    }

    private void logger(String content, SysUser sysuser){
        SysLog sysLog = new SysLog();

        sysLog.setIpaddress(ServletActionContext.getRequest().getRemoteAddr());
        sysLog.setContent(content);
        sysLog.setLogtime(new Timestamp(System.currentTimeMillis()));
        sysLog.setSysuser(sysuser);

        sysLogEntityService.save(sysLog);
    }
}
