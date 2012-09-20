package gov.dsb.core.domain;

import gov.dsb.core.domain.base.IdEntity;
import gov.dsb.core.interceptor.Treeable;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-5-6
 * Time: 15:22:37
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SYSPRIVILEGE")
public class SysPrivilege extends IdEntity implements Treeable {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String code;

    @Basic
    @Column(name = "CODE", length = 200)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    private Integer tag;

    @Basic
    @Column(name = "TAG", length = 10)
    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
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

    private String definition;

    @Basic
    @Column(name = "DEFINITION", length = 400)
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    private String treeid;

    @Basic
    @Column(name = "TREEID", length = 2000)
    public String getTreeid() {
        return treeid;
    }

    public void setTreeid(String treeid) {
        this.treeid = treeid;
    }

    private Boolean leaf;

    @Basic
    @Column(name = "LEAF", length = 1)
    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    private Long orderno;

    @Basic
    @Column(name = "ORDERNO", length = 10)
    public Long getOrderno() {
        return orderno;
    }

    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysPrivilege that = (SysPrivilege) o;

        if (code != null ? !code.equals(that.code) : that.code != null) {
            return false;
        }
        if (definition != null ? !definition.equals(that.definition) : that.definition != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (leaf != null ? !leaf.equals(that.leaf) : that.leaf != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (orderno != null ? !orderno.equals(that.orderno) : that.orderno != null) {
            return false;
        }
        if (tag != null ? !tag.equals(that.tag) : that.tag != null) {
            return false;
        }
        if (treeid != null ? !treeid.equals(that.treeid) : that.treeid != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (definition != null ? definition.hashCode() : 0);
        result = 31 * result + (treeid != null ? treeid.hashCode() : 0);
        result = 31 * result + (leaf != null ? leaf.hashCode() : 0);
        result = 31 * result + (orderno != null ? orderno.hashCode() : 0);
        return result;
    }

    private SysPrivilege parent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENTID", referencedColumnName = "ID")
    public SysPrivilege getParent() {
        return parent;
    }

    public void setParent(SysPrivilege parent) {
        this.parent = parent;
    }

    private Collection<SysPrivilege> children;

    @OneToMany(mappedBy = "parent")
    public Collection<SysPrivilege> getChildren() {
        return children;
    }

    public void setChildren(Collection<SysPrivilege> children) {
        this.children = children;
    }

    private Collection<SysLog> syslogs;

    @OneToMany(mappedBy = "sysprivilege")
    public Collection<SysLog> getSyslogs() {
        return syslogs;
    }

    public void setSyslogs(Collection<SysLog> syslogs) {
        this.syslogs = syslogs;
    }

    private SysCodeList privilegetype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRIVILEGETYPE", referencedColumnName = "ID")
    public SysCodeList getPrivilegetype() {
        return privilegetype;
    }

    public void setPrivilegetype(SysCodeList privilegetype) {
        this.privilegetype = privilegetype;
    }

    private Collection<SysRole> sysroleprivileges;

    @ManyToMany(mappedBy = "sysprivilegeroles")
    public Collection<SysRole> getSysroleprivileges() {
        return sysroleprivileges;
    }

    public void setSysroleprivileges(
            Collection<SysRole> sysroleprivileges) {
        this.sysroleprivileges = sysroleprivileges;
    }

    private Collection<SysUserPrivilege> sysuserprivileges;

    @OneToMany(mappedBy = "sysprivilege")
    public Collection<SysUserPrivilege> getSysuserprivileges() {
        return sysuserprivileges;
    }

    public void setSysuserprivileges(Collection<SysUserPrivilege> sysuserprivileges) {
        this.sysuserprivileges = sysuserprivileges;
    }

    ///////from now add by xiejiao

    private Set<DocCategory> viewDocPermitted;
    
    @ManyToMany(mappedBy = "viewPermittedPrivilege")
    public Set<DocCategory> getViewDocPermitted() {
        return viewDocPermitted;
    }

    public void setViewDocPermitted(Set<DocCategory> viewDocPermitted) {
        this.viewDocPermitted = viewDocPermitted;
    }

    private Set<DocCategory> editDocPermitted;
    
    @ManyToMany(mappedBy = "editPermittedPrivilege")
    public Set<DocCategory> getEditDocPermitted() {
        return editDocPermitted;
    }

    public void setEditDocPermitted(Set<DocCategory> editDocPermitted) {
        this.editDocPermitted = editDocPermitted;
    }
}
