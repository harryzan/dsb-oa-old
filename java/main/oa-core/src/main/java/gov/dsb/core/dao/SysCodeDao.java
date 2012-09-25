package gov.dsb.core.dao;

import gov.dsb.core.dao.base.EntityService;
import gov.dsb.core.domain.SysCode;
import gov.dsb.core.domain.SysCodeList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: harryzan
 * Date: 9/16/12
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class SysCodeDao extends EntityService<SysCode, Long> {

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, SysCode.class);
    }

    public Collection<SysCodeList> findCodeList (Object parameter){
        String  hql = "from SysCode s where s.code = ?";
        SysCode syscode = findUnique(hql, parameter);
        if(syscode == null || syscode.getSyscodelists() == null || syscode.getSyscodelists().size() <= 0){
            return new ArrayList<SysCodeList>();
        }
        return findUnique(hql, parameter).getSyscodelists();
    }
}