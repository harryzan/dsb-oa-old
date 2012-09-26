package gov.dsb.core.dao;

import gov.dsb.core.dao.base.EntityService;
import gov.dsb.core.domain.SysUserPrivilege;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: harryzan
 * Date: 9/16/12
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class SysUserPrivilegeDao extends EntityService<SysUserPrivilege, Long> {

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, SysUserPrivilege.class);
    }

    /**
     * 判断指定用户的某项权限是否被禁止
     * @param userId                用户ID
     * @param privilegeId           权限ID
     * @return                      boolean
     */
    public boolean isForbid(Long userId, Long privilegeId){
        Collection<SysUserPrivilege> entities = findByQuery("from SysUserPrivilege where sysuser.id=? and sysprivilege.id=?", userId, privilegeId);
        for(SysUserPrivilege entity : entities){
            if(!entity.getIsdeny()) {
                return true;
            }
        }

        return false;
    }
}