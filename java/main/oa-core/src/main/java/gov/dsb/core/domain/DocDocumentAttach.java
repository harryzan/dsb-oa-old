package gov.dsb.core.domain;

import gov.dsb.core.domain.base.IdEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-5-6
 * Time: 15:21:59
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "DOCDOCUMENTATTACH")
public class DocDocumentAttach extends IdEntity {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String filename;

    @Basic
    @Column(name = "FILENAME", length = 200)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    private String savefilename;

    @Basic
    @Column(name = "SAVEFILENAME", length = 200)
    public String getSavefilename() {
        return savefilename;
    }

    public void setSavefilename(String savefilename) {
        this.savefilename = savefilename;
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

    private Float filesize;

    @Basic
    @Column(name = "FILESIZE", length = 12, precision = 2)
    public Float getFilesize() {
        return filesize;
    }

    public void setFilesize(Float filesize) {
        this.filesize = filesize;
    }

    private String content;

    @Basic
    @Column(name = "CONTENT", length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

        DocDocumentAttach that = (DocDocumentAttach) o;

        if (content != null ? !content.equals(that.content) : that.content != null) {
            return false;
        }
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (filename != null ? !filename.equals(that.filename) : that.filename != null) {
            return false;
        }
        if (filesize != null ? !filesize.equals(that.filesize) : that.filesize != null) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (savefilename != null ? !savefilename.equals(that.savefilename) : that.savefilename != null) {
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
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        result = 31 * result + (savefilename != null ? savefilename.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (filesize != null ? filesize.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    private DocDocument docdocument;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCDOCUMENTID", referencedColumnName = "ID")
    public DocDocument getDocdocument() {
        return docdocument;
    }

    public void setDocdocument(DocDocument docdocument) {
        this.docdocument = docdocument;
    }
}
