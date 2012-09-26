package gov.dsb.core.domain;

import gov.dsb.core.domain.base.IdEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-5-6
 * Time: 15:21:57
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "DOCDOCUMENT")
public class DocDocument extends IdEntity {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String origincode;

    @Basic
    @Column(name = "ORIGINCODE", length = 200)
    public String getOrigincode() {
        return origincode;
    }

    public void setOrigincode(String origincode) {
        this.origincode = origincode;
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

    private String author;

    @Basic
    @Column(name = "AUTHOR", length = 200)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String keywords;

    @Basic
    @Column(name = "KEYWORDS", length = 1000)
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    private String abstractcontent;

    @Basic
    @Column(name = "ABSTRACTCONTENT", length = 2000)
    public String getAbstractcontent() {
        return abstractcontent;
    }

    public void setAbstractcontent(String abstractcontent) {
        this.abstractcontent = abstractcontent;
    }

    private Timestamp createtime;

    @Basic
    @Column(name = "CREATETIME", length = 7)
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    private Timestamp updatetime;

    @Basic
    @Column(name = "UPDATETIME", length = 7)
    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
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

        DocDocument that = (DocDocument) o;

        if (abstractcontent != null ? !abstractcontent.equals(that.abstractcontent) : that.abstractcontent != null) {
            return false;
        }
        if (author != null ? !author.equals(that.author) : that.author != null) {
            return false;
        }
        if (code != null ? !code.equals(that.code) : that.code != null) {
            return false;
        }
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (origincode != null ? !origincode.equals(that.origincode) : that.origincode != null) {
            return false;
        }
        if (updatetime != null ? !updatetime.equals(that.updatetime) : that.updatetime != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (origincode != null ? origincode.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (abstractcontent != null ? abstractcontent.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    private SysDept sysdept;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPTID", referencedColumnName = "ID")
    public SysDept getSysdept() {
        return sysdept;
    }

    public void setSysdept(SysDept sysdept) {
        this.sysdept = sysdept;
    }

    private DocCategory doccategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCCATEGORYID", referencedColumnName = "ID")
    public DocCategory getDoccategory() {
        return doccategory;
    }

    public void setDoccategory(DocCategory doccategory) {
        this.doccategory = doccategory;
    }

    private Collection<DocDocumentAttach> docdocumentattaches;

    @OneToMany(mappedBy = "docdocument")
    public Collection<DocDocumentAttach> getDocdocumentattaches() {
        return docdocumentattaches;
    }

    public void setDocdocumentattaches(
            Collection<DocDocumentAttach> docdocumentattaches) {
        this.docdocumentattaches = docdocumentattaches;
    }

//    private Collection<String> structures;
//
//    @OneToMany(mappedBy = "docdocument")
//    public Collection<Structure> getStructures() {
//        return structures;
//    }
//
//    public void setStructures(Collection<Structure> structures) {
//        this.structures = structures;
//    }

    private SysUser createuser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATEUSERID", referencedColumnName = "ID")
    public SysUser getCreateuser() {
        return createuser;
    }

    public void setCreateuser(SysUser createuser) {
        this.createuser = createuser;
    }

    private SysCodeList format;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FORMATID", referencedColumnName = "ID")
    public SysCodeList getFormat() {
        return format;
    }

    public void setFormat(SysCodeList format) {
        this.format = format;
    }

    private SysCodeList property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROPERTYID", referencedColumnName = "ID")
    public SysCodeList getProperty() {
        return property;
    }

    public void setProperty(SysCodeList property) {
        this.property = property;
    }

    private SysUser updateuser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPDATEUSERID", referencedColumnName = "ID")
    public SysUser getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(SysUser updateuser) {
        this.updateuser = updateuser;
    }
}
