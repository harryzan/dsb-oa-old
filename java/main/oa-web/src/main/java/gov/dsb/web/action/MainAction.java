package gov.dsb.web.action;

import gov.dsb.core.dao.DemandTypeDao;
import gov.dsb.core.dao.WorkArrangeDao;
import gov.dsb.core.domain.DemandType;
import gov.dsb.core.domain.SysUser;
import gov.dsb.core.domain.WorkArrange;
import gov.dsb.core.struts2.PageActionSupport;
import gov.dsb.web.security.UserSessionService;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-5
 * Time: 10:17:16
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = PageActionSupport.GRIDDATA, location = "/WEB-INF/pages/common/gridData.jsp")})
public class MainAction extends PageActionSupport<DemandType> {

    @Autowired
    private WorkArrangeDao workArrangeDao;

    @Autowired
    private UserSessionService userSessionService;

    private SysUser user;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public void prepare() throws Exception {

    }

    private List<WorkArrange> monarranges;
    private List<WorkArrange> tusarranges;
    private List<WorkArrange> wedarranges;
    private List<WorkArrange> thearranges;
    private List<WorkArrange> friarranges;
    private List<WorkArrange> satarranges;
    private List<WorkArrange> sunarranges;

    public List<WorkArrange> getMonarranges() {
        return monarranges;
    }

    public void setMonarranges(List<WorkArrange> monarranges) {
        this.monarranges = monarranges;
    }

    public List<WorkArrange> getTusarranges() {
        return tusarranges;
    }

    public void setTusarranges(List<WorkArrange> tusarranges) {
        this.tusarranges = tusarranges;
    }

    public List<WorkArrange> getWedarranges() {
        return wedarranges;
    }

    public void setWedarranges(List<WorkArrange> wedarranges) {
        this.wedarranges = wedarranges;
    }

    public List<WorkArrange> getThearranges() {
        return thearranges;
    }

    public void setThearranges(List<WorkArrange> thearranges) {
        this.thearranges = thearranges;
    }

    public List<WorkArrange> getFriarranges() {
        return friarranges;
    }

    public void setFriarranges(List<WorkArrange> friarranges) {
        this.friarranges = friarranges;
    }

    public List<WorkArrange> getSatarranges() {
        return satarranges;
    }

    public void setSatarranges(List<WorkArrange> satarranges) {
        this.satarranges = satarranges;
    }

    public List<WorkArrange> getSunarranges() {
        return sunarranges;
    }

    public void setSunarranges(List<WorkArrange> sunarranges) {
        this.sunarranges = sunarranges;
    }

    public String list() throws Exception {
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {

        user = userSessionService.getCurrentSysUser();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        Date d = new Date();
        calendar.setTime(d);
        Integer week = calendar.get(Calendar.WEEK_OF_YEAR);
        Integer year = calendar.get(Calendar.YEAR);

        monarranges = workArrangeDao.findByQuery("from WorkArrange where week=? and year=? and dow=? order by starttime", week.toString(), year.toString(), "2");
        tusarranges = workArrangeDao.findByQuery("from WorkArrange where week=? and year=? and dow=? order by starttime", week.toString(), year.toString(), "3");
        wedarranges = workArrangeDao.findByQuery("from WorkArrange where week=? and year=? and dow=? order by starttime", week.toString(), year.toString(), "4");
        thearranges = workArrangeDao.findByQuery("from WorkArrange where week=? and year=? and dow=? order by starttime", week.toString(), year.toString(), "5");
        friarranges = workArrangeDao.findByQuery("from WorkArrange where week=? and year=? and dow=? order by starttime", week.toString(), year.toString(), "6");
        satarranges = workArrangeDao.findByQuery("from WorkArrange where week=? and year=? and dow=? order by starttime", week.toString(), year.toString(), "7");
        sunarranges = workArrangeDao.findByQuery("from WorkArrange where week=? and year=? and dow=? order by starttime", week.toString(), year.toString(), "1");

        return super.execute();    //To change body of overridden methods use File | Settings | File Templates.
    }
}