package gov.dsb.web.security;

import gov.dsb.core.domain.SysUser;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: cxs
 * Date: 2009-12-3
 * Time: 13:46:50
 * To change this template use File | Settings | File Templates.
 */
public class SessionInfo {

    private SysUser user;

    private String userIp;

    private Date loginDate;

    private Date lastVisitDate;

    private String actName;

    private String sessionid;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getLoginDateStr() {
        return loginDate.toString();
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getLastVisitDate() {
        return lastVisitDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public String getLastVisitDateStr() {
        return lastVisitDate.toString();
    }

    public void setLastVisitDate(Date lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public int getOnlineTime() {
        return (int) (((new Date()).getTime() - this.loginDate.getTime()) / 60000);
    }
}
