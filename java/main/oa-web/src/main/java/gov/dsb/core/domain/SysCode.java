package gov.dsb.core.domain;


import gov.dsb.core.domain.base.IdEntity;
import gov.dsb.core.interceptor.Treeable;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-5-6
 * Time: 15:22:29
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SYSCODE")
public class SysCode extends IdEntity implements Treeable {

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

    private String description;

    @Basic
    @Column(name = "DESCRIPTION", length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Boolean reserved;

    @Basic
    @Column(name = "RESERVED", length = 1)
    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
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

        SysCode syscode = (SysCode) o;

        if (code != null ? !code.equals(syscode.code) : syscode.code != null) {
            return false;
        }
        if (description != null ? !description.equals(syscode.description) : syscode.description != null) {
            return false;
        }
        if (id != null ? !id.equals(syscode.id) : syscode.id != null) {
            return false;
        }
        if (leaf != null ? !leaf.equals(syscode.leaf) : syscode.leaf != null) {
            return false;
        }
        if (name != null ? !name.equals(syscode.name) : syscode.name != null) {
            return false;
        }
        if (orderno != null ? !orderno.equals(syscode.orderno) : syscode.orderno != null) {
            return false;
        }
        if (reserved != null ? !reserved.equals(syscode.reserved) : syscode.reserved != null) {
            return false;
        }
        if (treeid != null ? !treeid.equals(syscode.treeid) : syscode.treeid != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (reserved != null ? reserved.hashCode() : 0);
        result = 31 * result + (treeid != null ? treeid.hashCode() : 0);
        result = 31 * result + (leaf != null ? leaf.hashCode() : 0);
        result = 31 * result + (orderno != null ? orderno.hashCode() : 0);
        return result;
    }

    private SysCode parent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENTID")
    public SysCode getParent() {
        return parent;
    }

    public void setParent(SysCode parent) {
        this.parent = parent;
    }

    private Collection<SysCode> children;

    @OneToMany(mappedBy = "parent")
    public Collection<SysCode> getChildren() {
        return children;
    }

    public void setChildren(Collection<SysCode> children) {
        this.children = children;
    }

    private Collection<SysCodeList> syscodelists;

    @OneToMany(mappedBy = "syscode")
    public Collection<SysCodeList> getSyscodelists() {
        return syscodelists;
    }

    public void setSyscodelists(Collection<SysCodeList> syscodelists) {
        this.syscodelists = syscodelists;
    }
}
