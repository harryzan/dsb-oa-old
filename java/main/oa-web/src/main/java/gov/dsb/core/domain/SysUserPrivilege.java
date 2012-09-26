package gov.dsb.core.domain;

import gov.dsb.core.domain.base.IdEntity;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: xiejiao
 * Date: 2009-11-25
 * Time: 16:18:43
 */
@Entity
@Table(name = "SYSUSER_PRIVILEGE")
public class SysUserPrivilege extends IdEntity {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Boolean isdeny;

    @Basic
    @Column(name = "ISDENY", length = 10)
    public Boolean getIsdeny() {
        return isdeny;
    }

    public void setIsdeny(Boolean isdeny) {
        this.isdeny = isdeny;
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysUserPrivilege sysUserPrivilege = (SysUserPrivilege) o;

        if (isdeny != null ? !isdeny.equals(sysUserPrivilege.isdeny) : sysUserPrivilege.isdeny != null) {
            return false;
        }
        if (id != null ? !id.equals(sysUserPrivilege.id) : sysUserPrivilege.id != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (isdeny != null ? isdeny.hashCode() : 0);
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

    private SysPrivilege sysprivilege;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRIVILEGEID", referencedColumnName = "ID")
    public SysPrivilege getSysprivilege() {
        return sysprivilege;
    }

    public void setSysprivilege(SysPrivilege sysprivilege) {
        this.sysprivilege = sysprivilege;
    }
}
