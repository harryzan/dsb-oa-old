package gov.dsb.core.domain;

import gov.dsb.core.domain.base.IdEntity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-5-6
 * Time: 15:22:36
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "WORKARRANGE")
public class WorkArrange extends IdEntity {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private String month;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    private String week;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    private String dow;

    public String getDow() {
        return dow;
    }

    public void setDow(String dow) {
        this.dow = dow;
    }

    private String workdate;

    public String getWorkdate() {
        return workdate;
    }

    public void setWorkdate(String workdate) {
        this.workdate = workdate;
    }

    private String starttime;

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    private String endtime;

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    private long worktime;

    public long getWorktime() {
        return worktime;
    }

    public void setWorktime(long worktime) {
        this.worktime = worktime;
    }

    private String content;

    @Basic
    @Column(name = "CONTENT", length = 2000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String period;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WorkArrange syslog = (WorkArrange) o;

        if (content != null ? !content.equals(syslog.content) : syslog.content != null) {
            return false;
        }
        if (id != null ? !id.equals(syslog.id) : syslog.id != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    private SysUser sysuser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID", referencedColumnName = "ID")
    public SysUser getSysuser() {
        return sysuser;
    }

    public void setSysuser(SysUser sysuser) {
        this.sysuser = sysuser;
    }

    private Collection<SysUser> sysuserworkarranges;

    @ManyToMany
    @JoinTable(name = "WORKARRANGE_SYSUSER ",
            joinColumns = @JoinColumn(name = "WORKARRANGEID", referencedColumnName = "ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "USERID", referencedColumnName = "ID",
                    nullable = false))
    public Collection<SysUser> getSysuserworkarranges() {
        return sysuserworkarranges;
    }

    public void setSysuserworkarranges(Collection<SysUser> sysuserworkarranges) {
        this.sysuserworkarranges = sysuserworkarranges;
    }
}
