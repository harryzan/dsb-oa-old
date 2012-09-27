package gov.dsb.core.dao;

import gov.dsb.core.dao.base.EntityService;
import gov.dsb.core.domain.SysDept;
import gov.dsb.core.domain.SysUser;
import gov.dsb.core.domain.UserAttendance;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: harryzan
 * Date: 9/16/12
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserAttendanceDao extends EntityService<UserAttendance, Long> {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, UserAttendance.class);
    }

    public List<UserAttendance> getDayAttendance(Date date) {
        List<UserAttendance> attendances = findByProperty("checkdate", date);
        return attendances;
    }

    public List<UserAttendance> getDayAttendance(Date date, SysDept sysDept) {
        List<UserAttendance> attendances = findByQuery("from UserAttendance where checkdate=? and user.sysdept.id=?", date, sysDept.getId());
        System.out.println("********* attendances.size() = " + attendances.size());
        return attendances;
    }

    public List<UserAttendance> createDayAttendance(Date date) {
        List<UserAttendance> attendances = getDayAttendance(date);

        // todo 用户变化需要控制
        List<SysUser> users = sysUserDao.findAll();

        if (attendances.size() > 0)
        for (UserAttendance attendance : attendances) {
            if (users.contains(attendance.getUser())) {
                users.remove(attendance.getUser());
            }
        }

        for (SysUser user : users) {
            UserAttendance userAttendance = new UserAttendance();
            userAttendance.setCheckdate(date);
            userAttendance.setUser(user);
            save(userAttendance);
            attendances.add(userAttendance);
        }
        return attendances;
    }

    public List<UserAttendance> createDayAttendance(Date date, SysDept sysDept) {
        List<UserAttendance> attendances = getDayAttendance(date, sysDept);

        // todo 用户变化需要控制
        Collection<SysUser> users = sysDept.getSysusers();

        if (attendances.size() > 0)
            for (UserAttendance attendance : attendances) {
                if (users.contains(attendance.getUser())) {
                    users.remove(attendance.getUser());
                }
            }

        for (SysUser user : users) {
            UserAttendance userAttendance = new UserAttendance();
            userAttendance.setCheckdate(date);
            userAttendance.setUser(user);
            save(userAttendance);
            attendances.add(userAttendance);
            System.out.println("***************** new attendances = " + attendances.size());
        }
        return attendances;
    }
}