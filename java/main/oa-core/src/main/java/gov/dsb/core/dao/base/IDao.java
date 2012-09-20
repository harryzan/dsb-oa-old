package gov.dsb.core.dao.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-8-10
 * Time: 16:24:00
 * To change this template use File | Settings | File Templates.
 */
public interface IDao {

    public Session getSession();

    public SessionFactory getSessionFactory();
}
