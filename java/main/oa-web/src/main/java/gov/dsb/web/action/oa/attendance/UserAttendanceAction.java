package gov.dsb.web.action.oa.attendance;

import com.justone.core.struts2.CRUDActionSupport;
import gov.dsb.core.dao.SysDeptDao;
import gov.dsb.core.dao.SysRoleDao;
import gov.dsb.core.dao.SysUserDao;
import gov.dsb.core.dao.UserAttendanceDao;
import gov.dsb.core.domain.UserAttendance;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: cxs
 * Date: 2009-7-23
 * Time: 9:47:37
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "user-attendance-main", type = "redirect")})
public class UserAttendanceAction extends CRUDActionSupport<UserAttendance> {

    @Autowired
    private UserAttendanceDao service;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysDeptDao sysDeptDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    protected Long id;

    private String day;

    private String beforeday;

    private String afterday;

    public String getBeforeday() {
        return beforeday;
    }

    public String getAfterday() {
        return afterday;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Timestamp today;

    private List<Map<String, Object>> result;

    public Timestamp getToday() {
        return today;
    }

    public void setToday(Timestamp today) {
        this.today = today;
    }

    public List<Map<String, Object>> getResult() {
        return result;
    }

    public void setResult(List<Map<String, Object>> result) {
        this.result = result;
    }

    private String begintime;

    private String endtime;

    public String save() throws Exception {
        service.save(entity);
        return RELOAD;
    }

    public String delete() throws Exception {
        service.delete(id);
        return RELOAD;
    }

    protected void prepareModel() throws Exception {
        if (entity == null) {
            if (id != null) {
                entity = service.get(id);
            }
            else {
                entity = new UserAttendance();
            }
        }
    }

    public String day() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (day == null || "".equals(day)) {
            Date d = new Date();
            day = sdf.format(d);
            beforeday = sdf.format(new Date(d.getTime() - 24 * 3600 * 1000L));
            afterday = sdf.format(new Date(d.getTime() + 24 * 3600 * 1000L));
        }
        else {
            Date d = sdf.parse(day);
            day = sdf.format(d);
            beforeday = sdf.format(new Date(d.getTime() - 24 * 3600 * 1000L));
            afterday = sdf.format(new Date(d.getTime() + 24 * 3600 * 1000L));
        }

        return "day";
    }

    public String month() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        today = new Timestamp(System.currentTimeMillis());

        result = new ArrayList<Map<String, Object>>();
        try {
         }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "month";
    }

    public String monthopen() {
        return "monthopen";
    }

    public UserAttendance getModel() {
        return entity;
    }

    public String list() throws Exception {
        return "list";
    }

    public String search() throws Exception {
        return "search";
    }
}
