package gov.dsb.core.domain;

import gov.dsb.core.domain.base.IdEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-5-6
 * Time: 15:22:36
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SYSLOG")
public class SysLog extends IdEntity {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Timestamp logtime;

    @Basic
    @Column(name = "LOGDATE", length = 7)
    public Timestamp getLogtime() {
        return logtime;
    }

    public void setLogtime(Timestamp logtime) {
        this.logtime = logtime;
    }

    private String ipaddress;

    @Basic
    @Column(name = "IPADDRESS", length = 200)
    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysLog syslog = (SysLog) o;

        if (content != null ? !content.equals(syslog.content) : syslog.content != null) {
            return false;
        }
        if (id != null ? !id.equals(syslog.id) : syslog.id != null) {
            return false;
        }
        if (ipaddress != null ? !ipaddress.equals(syslog.ipaddress) : syslog.ipaddress != null) {
            return false;
        }
        if (logtime != null ? !logtime.equals(syslog.logtime) : syslog.logtime != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (logtime != null ? logtime.hashCode() : 0);
        result = 31 * result + (ipaddress != null ? ipaddress.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    private SysPrivilege sysprivilege;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRIVILEGEID", referencedColumnName = "ID")
    public SysPrivilege getSysprivilege() {
        return sysprivilege;
    }

    public void setSysprivilege(SysPrivilege sysprivilege) {
        this.sysprivilege = sysprivilege;
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
}
