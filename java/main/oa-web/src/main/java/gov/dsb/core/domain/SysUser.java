package gov.dsb.core.domain;

import gov.dsb.core.domain.base.IdEntity;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-5-6
 * Time: 15:22:43
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SYSUSER")
public class SysUser extends IdEntity {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String loginname;

    @Basic
    @Column(name = "LOGINNAME", nullable = false, length = 200)
    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    private String password;

    @Basic
    @Column(name = "PASSWORD", length = 200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String displayname;

    @Basic
    @Column(name = "DISPLAYNAME", length = 200)
    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    private Boolean status;

    @Basic
    @Column(name = "STATUS", length = 1)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    private String profile;

    @Basic
    @Column(name = "PROFILE", length = 4000)
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }


    private String phonenumber;

    @Basic
    @Column(name = "PHONENUMBER", length = 100)
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }


    private String email;


    @Basic
    @Column(name = "EMAIL", length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysUser sysuser = (SysUser) o;

        if (displayname != null ? !displayname.equals(sysuser.displayname) : sysuser.displayname != null) {
            return false;
        }
        if (id != null ? !id.equals(sysuser.id) : sysuser.id != null) {
            return false;
        }
        if (loginname != null ? !loginname.equals(sysuser.loginname) : sysuser.loginname != null) {
            return false;
        }
        if (password != null ? !password.equals(sysuser.password) : sysuser.password != null) {
            return false;
        }
        if (profile != null ? !profile.equals(sysuser.profile) : sysuser.profile != null) {
            return false;
        }
        if (status != null ? !status.equals(sysuser.status) : sysuser.status != null) {
            return false;
        }
        if (phonenumber != null ? !phonenumber.equals(sysuser.phonenumber) : sysuser.phonenumber != null) {
            return false;
        }
        if (email != null ? !email.equals(sysuser.email) : sysuser.email != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (loginname != null ? loginname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (displayname != null ? displayname.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        result = 31 * result + (phonenumber != null ? phonenumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    private SysDept sysdept;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPTID", referencedColumnName = "ID")
    public SysDept getSysdept() {
        return sysdept;
    }

    public void setSysdept(SysDept sysdept) {
        this.sysdept = sysdept;
    }

    private Collection<DocDocument> createdocdocuments;

    @OneToMany(mappedBy = "createuser")
    public Collection<DocDocument> getCreatedocdocuments() {
        return createdocdocuments;
    }

    public void setCreatedocdocuments(
            Collection<DocDocument> createdocdocuments) {
        this.createdocdocuments = createdocdocuments;
    }

    private Collection<DocDocument> updatedocdocuments;

    @OneToMany(mappedBy = "updateuser")
    public Collection<DocDocument> getUpdatedocdocuments() {
        return updatedocdocuments;
    }

    public void setUpdatedocdocuments(
            Collection<DocDocument> updatedocdocuments) {
        this.updatedocdocuments = updatedocdocuments;
    }

    private Collection<SysLog> syslogs;

    @OneToMany(mappedBy = "sysuser")
    public Collection<SysLog> getSyslogs() {
        return syslogs;
    }

    public void setSyslogs(Collection<SysLog> syslogs) {
        this.syslogs = syslogs;
    }

    private Collection<SysUserPrivilege> sysuserprivileges;
    @OneToMany(mappedBy = "sysuser")
    public Collection<SysUserPrivilege> getSysuserprivileges() {
        return sysuserprivileges;
    }

    public void setSysuserprivileges(Collection<SysUserPrivilege> sysuserprivileges) {
        this.sysuserprivileges = sysuserprivileges;
    }

    private Collection<SysRole> sysroleusers;

    @ManyToMany()
    @JoinTable(name = "SYSUSER_ROLE",
            joinColumns = @JoinColumn(name = "USERID", referencedColumnName = "ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "ROLEID", referencedColumnName = "ID",
                    nullable = false))
    public Collection<SysRole> getSysroleusers() {
        return sysroleusers;
    }

    public void setSysroleusers(Collection<SysRole> sysroleusers) {
        this.sysroleusers = sysroleusers;
    }

//    private Collection<Bulletin> bulletinsysusers;
//
//    @ManyToMany(mappedBy = "sysuserbulletins")
//    public Collection<Bulletin> getBulletinusers() {
//        return bulletinsysusers;
//    }
//
//    public void setBulletinusers(
//            Collection<Bulletin> bulletinsysusers) {
//        this.bulletinsysusers = bulletinsysusers;
//    }


    //from now add by xiejiao

    private Set<DocCategory> viewDocPermitted;

    @ManyToMany(mappedBy = "viewPermittedUser")
    public Set<DocCategory> getViewDocPermitted() {
        return viewDocPermitted;
    }

    public void setViewDocPermitted(Set<DocCategory> viewDocPermitted) {
        this.viewDocPermitted = viewDocPermitted;
    }

    private Set<DocCategory> editDocPermitted;

    @ManyToMany(mappedBy = "editPermittedUser")
    public Set<DocCategory> getEditDocPermitted() {
        return editDocPermitted;
    }

    public void setEditDocPermitted(Set<DocCategory> editDocPermitted) {
        this.editDocPermitted = editDocPermitted;
    }
}
