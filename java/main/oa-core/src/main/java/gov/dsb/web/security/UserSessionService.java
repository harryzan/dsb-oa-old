package gov.dsb.web.security;

import gov.dsb.core.dao.SysUserDao;
import gov.dsb.core.domain.SysUser;
import gov.dsb.core.utils.CryptUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * UserSession: Administrator
 * Date: 2007-10-18
 * Time: 10:15:58
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserSessionService {

    @Autowired
    private SysUserDao sysUserDao;

    public static final String SESSION_USERSESSION = "SESSION_USERSESSION";


    /**
     * find user by login name
     *
     * @param loginname
     * @return
     */
    public SysUser getUserByLoginname(String loginname) {
        if (loginname == null) {
            return null;
        }
        List<SysUser> list =
                sysUserDao.findByQuery("from SysUser fetch all properties where loginname = ? and status = ?",
                        loginname, true);
        for (SysUser sysUser : list) {
            if (loginname.equals(sysUser.getLoginname())) {
                return sysUser;
            }
        }
        return null;
    }

    /**
     * 权限判断
     *
     * @param privilegeCode
     * @return
     */
    public boolean hasPrivilege(String privilegeCode) {
//        if(privilegeCode == null) return true;
//        if(user == null) return false;
//        Set<SysPrivilege> privileges = user.getSysPrivileges();
//        for (SysPrivilege privilege : privileges) {
//            if(privilegeCode .equals(privilege.getPrivilegecode())){
//                SysUserPrivilege sysUserPrivilege = getService().get(SysUserPrivilege.class, new SysUserPrivilegePK(user, privilege));
//                if(sysUserPrivilege != null){
//                    return !"1".equals(sysUserPrivilege.getIsdeny());
//                }
//            }
//        }
//
//        Set<SysRole> roles = user.getSysRoles();
//        for (SysRole role : roles) {
//            for (SysPrivilege rolePrivilege : role.getSysPrivileges()) {
//                if(privilegeCode.equals(rolePrivilege.getPrivilegecode())) return true;
//            }
//        }
        return false;
    }

    /**
     * 登陆校验
     *
     * @param loginname 用户名
     * @param password  密码
     * @return
     */
    public boolean verify(String loginname, String password) {
        SysUser user = getUserByLoginname(loginname);
        if (user == null) {
            return false;
        }
        if (password == null) {
            password = "";
        }
        String checkPassword = user.getPassword();
        if (checkPassword == null) {
            checkPassword = "";
        }
        if (!"".equals(checkPassword)) {
            checkPassword = CryptUtil.cl_decrypt(checkPassword);
        }
        return checkPassword.equals(password);
    }


    /**
     * 登陆系统
     *
     * @param loginname
     * @return
     */
    public UserSession login(String loginname) {
        UserSession userSession = new UserSession();
        userSession.setLoginname(loginname);
        SysUser user = getUserByLoginname(loginname);
        userSession.setUserId(user.getId());
        userSession.setDisplayName(user.getDisplayname());
        userSession.setDept(user.getSysdept().getName());
        userSession.setLoginDate(new Timestamp(System.currentTimeMillis()));
        userSession.setLastVisitDate(new Timestamp(System.currentTimeMillis()));
        return userSession;
    }

    /**
     * 获取当前登录的用户
     * xj
     * @return      SysUser
     */
    public SysUser getCurrentSysUser(){
        HttpServletRequest request = ServletActionContext.getRequest();

        HttpSession session = request.getSession(true);
        UserSession userSession = (UserSession) session.getAttribute(UserSession.SESSION_USERSESSION);

        if (userSession == null)
            return null;
        return sysUserDao.get(userSession.getUserId());
    }

}