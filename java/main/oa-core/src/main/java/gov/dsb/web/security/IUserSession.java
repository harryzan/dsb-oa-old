package gov.dsb.web.security;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-1-14
 * Time: 11:03:30
 * To change this template use File | Settings | File Templates.
 */
public interface IUserSession {

//    public Object get(String key);

//    public void set(String key, Object value);

//    public Object getUser();

    public Object getDept();

    public boolean logout();

    public boolean isTimeout();

    public Long getUserId();

//    public void remove(String guid);
}
