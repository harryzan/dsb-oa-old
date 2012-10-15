package gov.dsb.web.action.message.workarrange;

import gov.dsb.core.dao.SysUserDao;
import gov.dsb.core.dao.WorkArrangeDao;
import gov.dsb.core.domain.SysUser;
import gov.dsb.core.domain.WorkArrange;
import gov.dsb.core.struts2.CRUDActionSupport;
import gov.dsb.core.utils.StringHelp;
import gov.dsb.core.utils.TimeHelper;
import gov.dsb.web.security.UserSessionService;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cxs
 * Date: 2009-7-23
 * Time: 9:47:37
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "user-attendance-main", type = "redirect")})
public class WorkArrangeAction extends CRUDActionSupport<WorkArrange> {

    @Autowired
    private WorkArrangeDao service;

    @Autowired
    private UserSessionService userSessionService;

    @Autowired
    private SysUserDao sysUserDao;

    protected Long id;

    private String day;

    private String beforeday;

    private String afterday;

    private List<WorkArrange> arranges;

    private Integer week;

    private Integer beforeweek;

    private Integer afterweek;

    private Integer beforeyear;

    private Integer afteryear;

    private Integer year;

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

    public int getNweek() {
        return week;
    }

    public void setNweek(int week) {
        this.week = week;
    }

    public int getBeforeweek() {
        return beforeweek;
    }

    public void setBeforeweek(int beforeweek) {
        this.beforeweek = beforeweek;
    }

    public int getAfterweek() {
        return afterweek;
    }

    public void setAfterweek(int afterweek) {
        this.afterweek = afterweek;
    }

    public int getBeforeyear() {
        return beforeyear;
    }

    public void setBeforeyear(int beforeyear) {
        this.beforeyear = beforeyear;
    }

    public int getAfteryear() {
        return afteryear;
    }

    public void setAfteryear(int afteryear) {
        this.afteryear = afteryear;
    }

    public int getNyear() {
        return year;
    }

    public void setNyear(int year) {
        this.year = year;
    }

    private Timestamp today;

    private List<Map<String, Object>> result;

    public Timestamp getToweek() {
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

    public List<WorkArrange> getArranges() {
        return arranges;
    }

    public void setArranges(List<WorkArrange> arranges) {
        this.arranges = arranges;
    }

    private String begintime;

    private String endtime;

    private boolean avaiable;

    public boolean getAvaiable() {
        return avaiable;
    }

    public void setAvaiable(boolean avaiable) {
        this.avaiable = avaiable;
    }

    private String arrid;

    public String getArrid() {
        return arrid;
    }

    public void setArrid(String arrid) {
        this.arrid = arrid;
    }

    public String save() throws Exception {
        SysUser currentUser = userSessionService.getCurrentSysUser();

        entity.setSysuser(currentUser);

        Timestamp start = Timestamp.valueOf(entity.getStarttime());
        Timestamp end = Timestamp.valueOf(entity.getEndtime());
        entity.setPeriod(TimeHelper.dtLong2DtString(end.getTime() - start.getTime()));
        entity.setWorktime(end.getTime() - start.getTime());

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(start.getTime());
        entity.setDow(""+c.get(Calendar.DAY_OF_WEEK));

        service.save(entity);

        return week();
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
                entity = new WorkArrange();

                if (year != null && week != null) {
                    entity.setYear(year.toString());
                    entity.setWeek(week.toString());
                }

//                entity.setWorkdate(day);
//                entity.setStarttime(day);
//                entity.setEndtime(day);
            }
        }
    }

    public String week() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        if (week != null && year != null) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.WEEK_OF_YEAR, week);
            Date d = calendar.getTime();
            day = sdf.format(d);
        }
        else if (StringHelp.isNotEmpty(day)) {
            Date d = sdf.parse(day);
            calendar.setTime(d);
            setNweek(calendar.get(Calendar.WEEK_OF_YEAR));
            setNyear(calendar.get(Calendar.YEAR));
        }
        else {
            Date d = new Date();
            calendar.setTime(d);
            day = sdf.format(d);
            setNweek(calendar.get(Calendar.WEEK_OF_YEAR));
            setNyear(calendar.get(Calendar.YEAR));
        }


        setBeforeweek(week - 1);
        setBeforeyear(year);
        setAfterweek(week + 1);
        setAfteryear(year);
//        setWeek("" + _week);
        if (week == 1) {
            setBeforeweek(52);
            setBeforeyear(year - 1);
        }
        else if (week == 52) {
            setAfterweek(1);
            setAfteryear(year + 1);
        }

        SysUser currentUser = userSessionService.getCurrentSysUser();

//        Date now = new Date();
//        if (!d.after(now)) {
//            setAvaiable(true);
//        }
//        else {
//            setAvaiable(false);
//        }

        arranges = service.findByQuery("from WorkArrange where week=? and year=? order by starttime", week.toString(), year.toString());

        return "week";
    }

    public String record() throws Exception {
        week();
        return "record";
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

    public WorkArrange getModel() {
        return entity;
    }

    public String list() throws Exception {
        return "list";
    }

    public String search() throws Exception {
        return "search";
    }
}
