package gov.dsb.core.dao;

import gov.dsb.core.dao.base.EntityService;
import gov.dsb.core.domain.SysPrivilege;
import gov.dsb.core.domain.SysRole;
import gov.dsb.core.domain.SysUser;
import gov.dsb.core.domain.SysUserPrivilege;
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
public class SysUserDao extends EntityService<SysUser, Long> {

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, SysUser.class);
    }


    @Autowired
    private SysUserPrivilegeDao sysUserPrivilegeDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    /**
     * user login
     * @param loginname    username
     * @param password     password
     * @return             .
     */
    public SysUser login(String loginname, String password){
        SysUser user = null;
        String hql = "from SysUser where loginname=?";

        try{
            user = findUnique(hql, loginname);
            if(!password.equals(user.getPassword())){
                user = null;
            }
        }
        catch(Exception ignore){
            user = null;
        }

        return user;
    }

    /**
     * 获取指定用户的所有权限
     * @param entityId      用户Id
     * @return        用户具有的权限集合
     */
    public Collection<SysPrivilege> getPrivileges(Long entityId){
        Collection<SysPrivilege> result = new ArrayList<SysPrivilege>();

        SysUser entity = get(entityId);
        Collection<SysUserPrivilege> sysuserprivileges = entity.getSysuserprivileges();

        for(SysUserPrivilege sysuserprivilege : sysuserprivileges){
            result.add(sysuserprivilege.getSysprivilege());
        }

        Collection<SysRole> sysroles = entity.getSysroleusers();
        for(SysRole role : sysroles){
            Collection<SysPrivilege> privileges = role.getSysprivilegeroles();
            for(SysPrivilege p : privileges){
                if(!result.contains(p)){
                    result.add(p);
                }
            }
        }

        return result;
    }

    /**
     * 获取指定用户的所有可行使权限
     * @param entityId      用户Id
     * @return        用户行使的权限集合
     */
    public Collection<SysPrivilege> getUsePrivileges(Long entityId){
        Collection<SysPrivilege> result = new ArrayList<SysPrivilege>();

        SysUser entity = get(entityId);
        Collection<SysUserPrivilege> sysuserprivileges = entity.getSysuserprivileges();

        for(SysUserPrivilege sysuserprivilege : sysuserprivileges){
            if(sysuserprivilege.getIsdeny()){
                result.add(sysuserprivilege.getSysprivilege());
            }
        }

        Collection<SysRole> sysroles = entity.getSysroleusers();
        for(SysRole role : sysroles){
            Collection<SysPrivilege> privileges = role.getSysprivilegeroles();
            for(SysPrivilege p : privileges){
                if(!result.contains(p) && !sysUserPrivilegeDao.isForbid(entityId, p.getId())){
                    result.add(p);
                }
            }
        }

        return result;
    }

    /**
     * 获取用户的所有角色权限
     * @param entityId     用户ID
     * @return             用户角色权限集
     */
    public Collection<SysPrivilege> getRolePrivileges(Long entityId){
        Collection<SysPrivilege> result = new ArrayList<SysPrivilege>();

        SysUser entity = get(entityId);

        Collection<SysRole> sysroles = entity.getSysroleusers();
        for(SysRole role : sysroles){
            Collection<SysPrivilege> privileges = role.getSysprivilegeroles();
            for(SysPrivilege p : privileges){
                if(!result.contains(p)){
                    result.add(p);
                }
            }
        }

        return result;
    }

    /**
     * 获取用户直接获得的权限
     * @param entityId     用户ID
     * @return             权限集合
     */
    public Collection<SysPrivilege> getUserprivileges(Long entityId){
        Collection<SysPrivilege> result = new ArrayList<SysPrivilege>();

        SysUser entity = get(entityId);
        Collection<SysUserPrivilege> sysuserprivileges = entity.getSysuserprivileges();

        for(SysUserPrivilege sysuserprivilege : sysuserprivileges){
            if(!containPrivilege(result, sysuserprivilege.getSysprivilege().getId())){
                result.add(sysuserprivilege.getSysprivilege());
            }
        }



        return result;
    }

    /**
     * 判断用户是否拥有指定的权限
     * @param entityId       用户ID
     * @param privilegeId    权限ID
     * @return               执行结果
     */
    public Boolean containPrivilege(Long entityId, Long privilegeId){
        SysUser entity = get(entityId);
        Collection<SysUserPrivilege> sysuserprivileges = entity.getSysuserprivileges();

        for(SysUserPrivilege sysuserprivilege : sysuserprivileges){
            if(sysuserprivilege.getSysprivilege().getId().equals(privilegeId)){
                return true;
            }
        }

        return false;
    }

    /**
     * 获取指定用户的所有被禁这权限
     * @param id          用户ID
     * @return            禁止权限集合
     */
    public Collection<SysPrivilege> getForbidprivilege(Long id){
        Collection<SysPrivilege> result = new ArrayList<SysPrivilege>();

        Collection<SysUserPrivilege> userPrivileges = get(id).getSysuserprivileges();
        for(SysUserPrivilege userprivilege : userPrivileges){
            if(!userprivilege.getIsdeny()){
                result.add(userprivilege.getSysprivilege());
            }
        }
        return result;
    }

    private boolean containPrivilege(Collection<SysPrivilege> privileges, Long privilegeid){
        for(SysPrivilege p : privileges){
            if(p.getId().equals(privilegeid)){
                return true;
            }
        }


        return false;
    }

    public boolean containRole(Long userId, String rolename) {
        SysRole role = sysRoleDao.findUniqueByProperty("name", rolename);
        if (role != null) {
            Collection<SysUser> users = role.getSysuserroles();
            for (SysUser user : users) {
                if (user.getId().equals(userId))
                    return true;
            }
        }
        return false;
    }
}