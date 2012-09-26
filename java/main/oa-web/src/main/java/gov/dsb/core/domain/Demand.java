package gov.dsb.core.domain;

import gov.dsb.core.domain.base.IdEntity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: harryzan
 * Date: 9/16/12
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Demand extends IdEntity {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Date demanddate;

    public Date getDemanddate() {
        return demanddate;
    }

    public void setDemanddate(Date demanddate) {
        this.demanddate = demanddate;
    }

    private Date submitdate;

    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    private Date checkdate;

    public Date getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String checkdesc;

    public String getCheckdesc() {
        return checkdesc;
    }

    public void setCheckdesc(String checkdesc) {
        this.checkdesc = checkdesc;
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

    private SysUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID", referencedColumnName = "ID")
    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    private SysUser checker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHECKERID", referencedColumnName = "ID")
    public SysUser getChecker() {
        return checker;
    }

    public void setChecker(SysUser checker) {
        this.checker = checker;
    }

    private DemandType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPEID", referencedColumnName = "ID")
    public DemandType getType() {
        return type;
    }

    public void setType(DemandType type) {
        this.type = type;
    }
}
