package gov.dsb.web.security;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import gov.dsb.core.utils.Nulls;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cxs
 * Date: 2009-12-3
 * Time: 12:47:03
 * To change this template use File | Settings | File Templates.
 */
//@Service
public class UserSessionListener implements HttpSessionListener {

//    @Autowired
//    private SysLogEntityService sysLogEntityService;

//    @Autowired
//    private static SysUserEntityService  sysUserEntityService;

    private static Logger logger = LoggerFactory.getLogger(UserSessionListener.class.getName());
    private static HashSet sessionList = new HashSet();

    public void sessionCreated(HttpSessionEvent event)
    {
//        System.out.println("==create=="+event.getSession()+"==");
        logger.debug("sessionCreated");
        sessionList.add(event.getSession());

    }

    public void sessionDestroyed(HttpSessionEvent event)
    {
//        System.out.println("==destroy==");
        logger.debug("sessionDestroyed");
        HttpSession session = event.getSession() ;
//        UserSession userSession = (UserSession) session.getAttribute("SESSION_USERSESSION");
//        if(userSession != null){
//            SysLog sysLog = new SysLog();
//            sysLog.setIpaddress((String)userSession.get("UserIp"));
//            sysLog.setContent("SESSION失效 离线!");
//            sysLog.setSysuser(userSession.getUser());
//            sysLog.setLogtime(new Timestamp(System.currentTimeMillis()));
//
//            sysLogEntityService.save(sysLog);
//        }
        sessionList.remove(event.getSession());
    }

    public static int getActiveSessionCount()
    {
        return sessionList.size();
    }
    public static int getLoginUserCount()
    {
        int count = 0;
        for (Object oSession : sessionList) {
//            System.out.println("************************ oSession = " + oSession);
            HttpSession session = (HttpSession) oSession;
            UserSession userSession = (UserSession) session.getAttribute(UserSession.SESSION_USERSESSION);
            if (userSession != null) {
                if (!Nulls.isNull(userSession.getLoginDate())) {
                    count++;
//                    System.out.println("************************** userSession.getDisplayName() = " + userSession.getDisplayName());
//                    System.out.println("====loginusercount++");
                }
            }
        }
        return count;
    }
    public static int getOnlineUserCount()
    {
        int count = 0;

        for (Iterator it = sessionList.iterator(); it.hasNext();)
        {
            HttpSession session = (HttpSession) it.next();
            UserSession userSession = (UserSession) session.getAttribute("SESSION_USERSESSION");
            if (userSession != null)
            {
                    count++;
//                System.out.println("====onlineusercount++");
            }
        }

        return count;
    }

    /**
     *
     * @return arraylist of SessionInfo
     */
    public static ArrayList<UserSession> getOnlineUserList() throws Exception
    {
        return getOnlineUserList(false);
    }

    /**
     *
     * @param loginTag .
     * @return arraylist of SessionInfo
     */
    public static ArrayList<UserSession> getOnlineUserList(boolean loginTag) throws Exception
    {
        ArrayList<UserSession> list = new ArrayList<UserSession>();
        try{

            for (Object aSessionList : sessionList) {
                HttpSession session = (HttpSession) aSessionList;
                UserSession userSession = (UserSession) session.getAttribute(UserSession.SESSION_USERSESSION);
                if (loginTag) {
                    if (userSession != null) {
                        if (!Nulls.isNull(userSession.getLoginDate()) && !Nulls.isNull(userSession.getUserId())) {
                            list.add(userSession);
//                        SessionInfo sessionInfo = new SessionInfo();
//                        sessionInfo.setActName(""+userSession.get("LastActName"));
//                        if(userSession.get("LastActName") == null){
//                            sessionInfo.setActName("");
//                        }
//                        sessionInfo.setUser(sysUserEntityService.get(userSession.getUserId()));
//                        sessionInfo.setLastVisitDate(userSession.getLastVisitDate());
//                        sessionInfo.setLoginDate(userSession.getLoginDate());
//                        sessionInfo.setSessionid(session.getId());
//                        sessionInfo.setUserIp(""+userSession.get("UserIp"));
//                        list.add(sessionInfo);
                        }
                    }
                }
                else {
                    //if (userSession != null )
                    if (userSession != null && userSession.get("UserIp") != null)
                    {
                        userSession.setLoginDate(new Timestamp(session.getCreationTime()));
                        userSession.setLastVisitDate(new Timestamp(session.getLastAccessedTime()));
                        list.add(userSession);
                        //no longdate is execute call.jsp
//                        if (Nulls.isNull(userSession.getLoginDate())) {
//                            SessionInfo sessionInfo = new SessionInfo();
//                            sessionInfo.setActName((String) userSession.get("LastActName"));
//                            sessionInfo.setUser(null);
//                            sessionInfo.setLastVisitDate(new Timestamp(session.getLastAccessedTime()));
//                            sessionInfo.setLoginDate(new Timestamp(session.getCreationTime()));
//                            sessionInfo.setSessionid(session.getId());
//                            if (userSession.get("UserIp") != null) {
//                                sessionInfo.setUserIp("" + userSession.get("UserIp"));
//                            }
//                            else {
//                                sessionInfo.setUserIp(Nulls.getNullString());
//                            }
//                            list.add(sessionInfo);
//                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Collections.sort(list, new LoginDateComparator());
        return list;
    }

    public static class LoginDateComparator
        implements Comparator
    {
        public int compare(Object element1, Object element2)
        {
            UserSession item1 = (UserSession) element1;
            UserSession item2 = (UserSession) element2;
            if (item1.getLoginDate() == null) return 1;
            if (item2.getLoginDate() == null) return 0;
            return item1.getLoginDate().compareTo(item2.getLoginDate());
        }
    }
}
