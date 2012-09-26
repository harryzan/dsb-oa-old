package gov.dsb.core.domain;

import gov.dsb.core.domain.base.IdEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-5-6
 * Time: 15:22:39
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SYSROLE")
public class SysRole extends IdEntity {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;

    @Basic
    @Column(name = "NAME", length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String description;

    @Basic
    @Column(name = "DESCRIPTION", length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysRole sysrole = (SysRole) o;

        if (description != null ? !description.equals(sysrole.description) : sysrole.description != null) {
            return false;
        }
        if (id != null ? !id.equals(sysrole.id) : sysrole.id != null) {
            return false;
        }
        if (name != null ? !name.equals(sysrole.name) : sysrole.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    private Collection<SysPrivilege> sysprivilegeroles;

    @ManyToMany
    @JoinTable(name = "SYSROLE_PRIVILEGE",
            joinColumns = @JoinColumn(name = "ROLEID", referencedColumnName = "ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "PRIVILEGEID", referencedColumnName = "ID",
                    nullable = false))
    public Collection<SysPrivilege> getSysprivilegeroles() {
        return sysprivilegeroles;
    }

    public void setSysprivilegeroles(
            Collection<SysPrivilege> sysprivilegeroles) {
        this.sysprivilegeroles = sysprivilegeroles;
    }

    private Collection<SysUser> sysuserroles;

    @ManyToMany(mappedBy = "sysroleusers", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    public Collection<SysUser> getSysuserroles() {
        return sysuserroles;
    }

    public void setSysuserroles(Collection<SysUser> sysuserroles) {
        this.sysuserroles = sysuserroles;
    }
}
