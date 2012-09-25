package gov.dsb.web.security;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * UserSession: Administrator
 * Date: 2007-10-18
 * Time: 10:15:58
 * To change this template use File | Settings | File Templates.
 */
public class UserSession implements IUserSession, Serializable{

    private static final long serialVersionUID = -6849794470754667711L;

    private Timestamp loginDate;

    private Timestamp lastVisitDate;

    private HashMap map = new HashMap();

    public static final String SESSION_USERSESSION = "SESSION_USERSESSION";

    public static final String SESSION_SHAREDSESSION = "SESSION_SHAREDSESSION";

    private String loginname=null;

    private String displayName=null;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Timestamp getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Timestamp loginDate) {
        this.loginDate = loginDate;
    }

    public Timestamp getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(Timestamp lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    /**
     * 退出系统****
     *
     * @return
     */
    public boolean logout() {
        loginDate = null;
        userId = null;
        return true;
    }

    public Object get(String key) {
        return map.get(key);
    }

    public void set(String key, Object value) {
        map.put(key, value);
    }

    public void remove(String key) {
        map.remove(key);
    }

    private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

//    public String getUserName() {
//        return getUser().getDisplayname();
//    }

//    public Long getDeptId(){
//        return getUser().getDept().getId();
//    }

//    public SysDept getDept() {
//        return getUser().getDept();
//    }

    private String dept;

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public boolean isTimeout() {
        return false;
//        return getUser() == null;
    }

}
