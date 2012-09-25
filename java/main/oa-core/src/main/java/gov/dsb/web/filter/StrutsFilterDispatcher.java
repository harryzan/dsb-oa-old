package gov.dsb.web.filter;

import gov.dsb.web.security.IUserSession;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-7-30
 * Time: 14:20:56
 * To change this template use File | Settings | File Templates.
 */
public class StrutsFilterDispatcher extends org.apache.struts2.dispatcher.FilterDispatcher {

    public static String SESSION_USERSESSION = "SESSION_USERSESSION";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String url = request.getRequestURL().toString();
//        System.out.println("url = " + url);
        //忽略webservice
//        if(url.indexOf("interfaces") >= 0){
//            System.out.println(url);
//        }
        if (url.indexOf("/webservice") >= 0) {
            chain.doFilter(req, res);
        }
        else if (url.indexOf("interfaces/data-explorer!loginForData") >=0 ) {
            super.doFilter(req, res, chain);
        }
        else {
            varifySession(req, res);
            super.doFilter(req, res,
                    chain);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }

    public boolean varifySession(ServletRequest servletRequest, ServletResponse servletResponse)
            throws UnsupportedEncodingException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            IUserSession userSession =
                    (IUserSession) ((HttpServletRequest) servletRequest).getSession(true).getAttribute(
                            SESSION_USERSESSION);
            if (userSession == null) {
                StringBuffer url = request.getRequestURL();
                if ("/".equals(url.substring(url.length() - 1)) || url.indexOf("/logon") >= 0 ||
                        url.indexOf("/index") >= 0 || url.indexOf("themes") >= 0 ||
                        url.indexOf("scripts") >= 0 || url.indexOf("common") >= 0) {
                    return false;
                }
                else {
                    throw new RuntimeException("注意：请先登录系统！");
                }
            }
            else {
                return true;
            }
//            HttpSession session = ((HttpServletRequest) servletRequest).getSession();
//            boolean hasSession = false;
//            if (session != null) {
//                UserSession userSession = (UserSession) session.getAttribute(UserSession.SESSION_USERSESSION);
//                hasSession = userSession != null;
//            }
//            if (!hasSession) {
////                String requestURL = request.getRequestURL().toString();
//                if (request.getRequestURL().indexOf("manage/") >= 0 && request.getRequestURL().indexOf("login") == -1) {
////                    System.err.println("注意：请先登录系统！ ");
//                    throw new RuntimeException("注意：请先登录系统！");
//                }
//            }
        }
        return false;
    }
}
