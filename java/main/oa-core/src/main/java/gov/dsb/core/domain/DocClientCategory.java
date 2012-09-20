package gov.dsb.core.domain;

import gov.dsb.core.domain.base.IdEntity;
import gov.dsb.core.interceptor.Treeable;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-5-6
 * Time: 15:21:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "DOCCLIENTCATEGORY")
public class DocClientCategory extends IdEntity implements Treeable {

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

    private String purpose;

    @Basic
    @Column(name = "PURPOSE", length = 200)
    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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

    private Boolean leaf;

    @Basic
    @Column(name = "LEAF", length = 1)
    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocClientCategory that = (DocClientCategory) o;

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
        if (purpose != null ? !purpose.equals(that.purpose) : that.purpose != null) {
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
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (purpose != null ? purpose.hashCode() : 0);
        result = 31 * result + (orderno != null ? orderno.hashCode() : 0);
        result = 31 * result + (leaf != null ? leaf.hashCode() : 0);
        result = 31 * result + (treeid != null ? treeid.hashCode() : 0);
        return result;
    }

    private DocClientCategory parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID", referencedColumnName = "ID")
    public DocClientCategory getParent() {
        return parent;
    }

    public void setParent(DocClientCategory parent) {
        this.parent = parent;
    }

    private Collection<DocClientCategory> children;

    @OneToMany(mappedBy = "parent")
    public Collection<DocClientCategory> getChildren() {
        return children;
    }

    public void setChildren(
            Collection<DocClientCategory> children) {
        this.children = children;
    }

    private Collection<DocRelateCategory> docrelatecategories;

    @OneToMany(mappedBy = "docclientcategory")
    public Collection<DocRelateCategory> getDocrelatecategories() {
        return docrelatecategories;
    }

    public void setDocrelatecategories(
            Collection<DocRelateCategory> docrelatecategories) {
        this.docrelatecategories = docrelatecategories;
    }
}
