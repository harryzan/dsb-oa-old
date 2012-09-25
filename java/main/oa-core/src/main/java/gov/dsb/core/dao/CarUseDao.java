package gov.dsb.core.dao;

import gov.dsb.core.dao.base.EntityService;
import gov.dsb.core.domain.CarUse;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: harryzan
 * Date: 9/16/12
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CarUseDao extends EntityService<CarUse, Long> {

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, CarUse.class);
    }
}