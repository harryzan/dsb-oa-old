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
 * Time: 15:21:54
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "DOCCATEGORY")
public class DocCategory extends IdEntity implements Treeable {

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

    private String path;

    @Basic
    @Column(name = "PATH", length = 200)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    private Boolean issystem;

    @Basic
    @Column(name = "ISSYSTEM", length = 1)
    public Boolean getIssystem() {
        return issystem;
    }

    public void setIssystem(Boolean issystem) {
        this.issystem = issystem;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocCategory that = (DocCategory) o;

        if (code != null ? !code.equals(that.code) : that.code != null) {
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
        if (path != null ? !path.equals(that.path) : that.path != null) {
            return false;
        }
        if (treeid != null ? !treeid.equals(that.treeid) : that.treeid != null) {
            return false;
        }
        if (issystem != null ? !issystem.equals(that.issystem) : that.issystem != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (treeid != null ? treeid.hashCode() : 0);
        result = 31 * result + (leaf != null ? leaf.hashCode() : 0);
        result = 31 * result + (orderno != null ? orderno.hashCode() : 0);
        result = 31 * result + (issystem != null ? issystem.hashCode() : 0);
        return result;
    }

    private DocCategory parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID", referencedColumnName = "ID")
    public DocCategory getParent() {
        return parent;
    }

    public void setParent(DocCategory parent) {
        this.parent = parent;
    }

    private Collection<DocCategory> children;

    @OneToMany(mappedBy = "parent")
    public Collection<DocCategory> getChildren() {
        return children;
    }

    public void setChildren(Collection<DocCategory> children) {
        this.children = children;
    }

    private Collection<DocDocument> docdocuments;

    @OneToMany(mappedBy = "doccategory")
    public Collection<DocDocument> getDocdocuments() {
        return docdocuments;
    }

    public void setDocdocuments(Collection<DocDocument> docdocuments) {
        this.docdocuments = docdocuments;
    }

    private Collection<DocRelateCategory> docrelatecategories;

    @OneToMany(mappedBy = "doccategory")
    public Collection<DocRelateCategory> getDocrelatecategories() {
        return docrelatecategories;
    }

    public void setDocrelatecategories(
            Collection<DocRelateCategory> docrelatecategories) {
        this.docrelatecategories = docrelatecategories;
    }

//    public transient String authorityView = "1";  //目录权限--查看
//
//    @Transient
//    @Column(name = "AUTHORITYVIEW",updatable = false)
//    public String getAuthorityView() {
//        return authorityView;
//    }
//
//    public transient String authorityEdit= "2";   //目录权限--修改
//
//    @Column(name = "AUTHORITYEDIT",updatable = false)
//    public String getAuthorityEdit() {
//        return authorityEdit;
//    }
//
//    public transient String authorityDept = "1";      //目录授权--单位
//
//    @Transient
//    @Column(name = "AUTHORITYDEPT",updatable = false)
//    public String getAuthorityDept() {
//        return authorityDept;
//    }
//
//    public transient String authorityUser= "2";       //目录授权--用户
//
//    @Transient
//    @Column(name = "AUTHORITYUSER",updatable = false)
//    public String getAuthorityUser() {
//        return authorityUser;
//    }
//
//    public transient String authorityPrivilege= "3"; //目录授权--权限项
//
//    @Transient
//    @Column(name = "AUTHRITYPRIVILEGE",updatable = false)
//    public String getAuthorityPrivilege() {
//        return authorityPrivilege;
//    }

//    public transient String authorityIcon_none= "1";    //无任何权限
//    public transient String authorityIcon_view= "2";    //查看权限
//    public transient String authorityIcon_edit= "3";    //修改权限
//    public transient String authorityIcon_viewAndEdit= "4";  //查看及修改权限

    private Set<SysDept> viewPermittedDept;

    @ManyToMany(targetEntity = SysDept.class)
    @JoinTable(name = "DOCCATEGORY_DEPTVIEW",
            joinColumns = {@JoinColumn(name = "DOCCATEGORYID", referencedColumnName = "ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "REFID", referencedColumnName = "ID", nullable = false)})
    public Set<SysDept> getViewPermittedDept() {
        return viewPermittedDept;
    }

    public void setViewPermittedDept(Set<SysDept> viewPermittedDept) {
        this.viewPermittedDept = viewPermittedDept;
    }

    private Set<SysDept> editPermittedDept;

    @ManyToMany(targetEntity = SysDept.class)
    @JoinTable(name = "DOCCATEGORY_DEPTEDIT",
            joinColumns = {@JoinColumn(name = "DOCCATEGORYID", referencedColumnName = "ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "REFID", referencedColumnName = "ID", nullable = false)})
    public Set<SysDept> getEditPermittedDept() {
        return editPermittedDept;
    }

    public void setEditPermittedDept(Set<SysDept> editPermittedDept) {
        this.editPermittedDept = editPermittedDept;
    }

    private Set<SysUser> viewPermittedUser;

    @ManyToMany(targetEntity = SysUser.class)
    @JoinTable(name = "DOCCATEGORY_USERVIEW",
            joinColumns = {@JoinColumn(name = "DOCCATEGORYID", referencedColumnName = "ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "REFID", referencedColumnName = "ID", nullable = false)})
    public Set<SysUser> getViewPermittedUser() {
        return viewPermittedUser;
    }

    public void setViewPermittedUser(Set<SysUser> viewPermittedUser) {
        this.viewPermittedUser = viewPermittedUser;
    }

    private Set<SysUser> editPermittedUser;

    @ManyToMany(targetEntity = SysUser.class)
    @JoinTable(name = "DOCCATEGORY_USEREDIT",
            joinColumns = {@JoinColumn(name = "DOCCATEGORYID", referencedColumnName = "ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "REFID", referencedColumnName = "ID", nullable = false)})
    public Set<SysUser> getEditPermittedUser() {
        return editPermittedUser;
    }

    public void setEditPermittedUser(Set<SysUser> editPermittedUser) {
        this.editPermittedUser = editPermittedUser;
    }

    private Set<SysPrivilege> viewPermittedPrivilege;

    @ManyToMany(targetEntity = SysPrivilege.class)
    @JoinTable(name = "DOCCATEGORY_PRIVILEGEVIEW",
            joinColumns = {@JoinColumn(name = "DOCCATEGORYID", referencedColumnName = "ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "REFID", referencedColumnName = "ID", nullable = false)})
    public Set<SysPrivilege> getViewPermittedPrivilege() {
        return viewPermittedPrivilege;
    }

    public void setViewPermittedPrivilege(Set<SysPrivilege> viewPermittedPrivilege) {
        this.viewPermittedPrivilege = viewPermittedPrivilege;
    }

    private Set<SysPrivilege> editPermittedPrivilege;

    @ManyToMany(targetEntity = SysPrivilege.class)
    @JoinTable(name = "DOCCATEGORY_PRIVILEGEEDIT",
            joinColumns = {@JoinColumn(name = "DOCCATEGORYID", referencedColumnName = "ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "REFID", referencedColumnName = "ID", nullable = false)})
    public Set<SysPrivilege> getEditPermittedPrivilege() {
        return editPermittedPrivilege;
    }

    public void setEditPermittedPrivilege(Set<SysPrivilege> editPermittedPrivilege) {
        this.editPermittedPrivilege = editPermittedPrivilege;
    }
}
