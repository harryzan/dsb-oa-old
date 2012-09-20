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
 * Time: 15:22:32
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SYSDEPT")
public class SysDept extends IdEntity implements Treeable {

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

    private String shortname;

    @Basic
    @Column(name = "SHORTNAME", length = 200)
    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    private String stamper;

    @Basic
    @Column(name = "STAMPER", length = 200)
    public String getStamper() {
        return stamper;
    }

    public void setStamper(String stamper) {
        this.stamper = stamper;
    }

    private Boolean type;

    @Basic
    @Column(name = "TYPE", length = 10)
    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    private String telephone;

    @Basic
    @Column(name = "TELEPHONE", length = 200)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    private String address;

    @Basic
    @Column(name = "ADDRESS", length = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String description;

    @Basic
    @Column(name = "DESCRIPTION", length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String profile;

    @Basic
    @Column(name = "PROFILE", length = 2000)
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    private String country;

    @Basic
    @Column(name = "COUNTRY", length = 200)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String province;

    @Basic
    @Column(name = "PROVINCE", length = 200)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    private String city;

    @Basic
    @Column(name = "CITY", length = 200)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String zipcode;

    @Basic
    @Column(name = "ZIPCODE", length = 200)
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    private String enterprisecharacter;

    @Basic
    @Column(name = "ENTERPRISECHARACTER", length = 200)
    public String getEnterprisecharacter() {
        return enterprisecharacter;
    }

    public void setEnterprisecharacter(String enterprisecharacter) {
        this.enterprisecharacter = enterprisecharacter;
    }

    private String aptitude;

    @Basic
    @Column(name = "APTITUDE", length = 200)
    public String getAptitude() {
        return aptitude;
    }

    public void setAptitude(String aptitude) {
        this.aptitude = aptitude;
    }

    private String corporationrepresent;

    @Basic
    @Column(name = "CORPORATIONREPRESENT", length = 200)
    public String getCorporationrepresent() {
        return corporationrepresent;
    }

    public void setCorporationrepresent(String corporationrepresent) {
        this.corporationrepresent = corporationrepresent;
    }

    private String bank;

    @Basic
    @Column(name = "BANK", length = 200)
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    private String bankaccount;

    @Basic
    @Column(name = "BANKACCOUNT", length = 200)
    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    private String registeredbankroll;

    @Basic
    @Column(name = "REGISTEREDBANKROLL", length = 200)
    public String getRegisteredbankroll() {
        return registeredbankroll;
    }

    public void setRegisteredbankroll(String registeredbankroll) {
        this.registeredbankroll = registeredbankroll;
    }

    private Boolean otherdept;

    @Basic
    @Column(name = "OTHERDEPT", length = 1)
    public Boolean getOtherdept() {
        return otherdept;
    }

    public void setOtherdept(Boolean otherdept) {
        this.otherdept = otherdept;
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

        SysDept sysdept = (SysDept) o;

        if (address != null ? !address.equals(sysdept.address) : sysdept.address != null) {
            return false;
        }
        if (aptitude != null ? !aptitude.equals(sysdept.aptitude) : sysdept.aptitude != null) {
            return false;
        }
        if (bank != null ? !bank.equals(sysdept.bank) : sysdept.bank != null) {
            return false;
        }
        if (bankaccount != null ? !bankaccount.equals(sysdept.bankaccount) : sysdept.bankaccount != null) {
            return false;
        }
        if (city != null ? !city.equals(sysdept.city) : sysdept.city != null) {
            return false;
        }
        if (code != null ? !code.equals(sysdept.code) : sysdept.code != null) {
            return false;
        }
        if (corporationrepresent != null ? !corporationrepresent.equals(sysdept.corporationrepresent) :
                sysdept.corporationrepresent != null) {
            return false;
        }
        if (country != null ? !country.equals(sysdept.country) : sysdept.country != null) {
            return false;
        }
        if (description != null ? !description.equals(sysdept.description) : sysdept.description != null) {
            return false;
        }
        if (enterprisecharacter != null ? !enterprisecharacter.equals(sysdept.enterprisecharacter) :
                sysdept.enterprisecharacter != null) {
            return false;
        }
        if (id != null ? !id.equals(sysdept.id) : sysdept.id != null) {
            return false;
        }
        if (leaf != null ? !leaf.equals(sysdept.leaf) : sysdept.leaf != null) {
            return false;
        }
        if (name != null ? !name.equals(sysdept.name) : sysdept.name != null) {
            return false;
        }
        if (orderno != null ? !orderno.equals(sysdept.orderno) : sysdept.orderno != null) {
            return false;
        }
        if (otherdept != null ? !otherdept.equals(sysdept.otherdept) : sysdept.otherdept != null) {
            return false;
        }
        if (profile != null ? !profile.equals(sysdept.profile) : sysdept.profile != null) {
            return false;
        }
        if (province != null ? !province.equals(sysdept.province) : sysdept.province != null) {
            return false;
        }
        if (registeredbankroll != null ? !registeredbankroll.equals(sysdept.registeredbankroll) :
                sysdept.registeredbankroll != null) {
            return false;
        }
        if (shortname != null ? !shortname.equals(sysdept.shortname) : sysdept.shortname != null) {
            return false;
        }
        if (stamper != null ? !stamper.equals(sysdept.stamper) : sysdept.stamper != null) {
            return false;
        }
        if (telephone != null ? !telephone.equals(sysdept.telephone) : sysdept.telephone != null) {
            return false;
        }
        if (treeid != null ? !treeid.equals(sysdept.treeid) : sysdept.treeid != null) {
            return false;
        }
        if (type != null ? !type.equals(sysdept.type) : sysdept.type != null) {
            return false;
        }
        if (zipcode != null ? !zipcode.equals(sysdept.zipcode) : sysdept.zipcode != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (shortname != null ? shortname.hashCode() : 0);
        result = 31 * result + (stamper != null ? stamper.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);
        result = 31 * result + (enterprisecharacter != null ? enterprisecharacter.hashCode() : 0);
        result = 31 * result + (aptitude != null ? aptitude.hashCode() : 0);
        result = 31 * result + (corporationrepresent != null ? corporationrepresent.hashCode() : 0);
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        result = 31 * result + (bankaccount != null ? bankaccount.hashCode() : 0);
        result = 31 * result + (registeredbankroll != null ? registeredbankroll.hashCode() : 0);
        result = 31 * result + (otherdept != null ? otherdept.hashCode() : 0);
        result = 31 * result + (orderno != null ? orderno.hashCode() : 0);
        result = 31 * result + (leaf != null ? leaf.hashCode() : 0);
        result = 31 * result + (treeid != null ? treeid.hashCode() : 0);
        return result;
    }

    private Collection<DocDocument> docdocuments;

    @OneToMany(mappedBy = "sysdept")
    public Collection<DocDocument> getDocdocuments() {
        return docdocuments;
    }

    public void setDocdocuments(Collection<DocDocument> docdocuments) {
        this.docdocuments = docdocuments;
    }

    private SysDept parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID", referencedColumnName = "ID")
    public SysDept getParent() {
        return parent;
    }

    public void setParent(SysDept parent) {
        this.parent = parent;
    }

    private Collection<SysDept> children;

    @OneToMany(mappedBy = "parent")
    public Collection<SysDept> getChildren() {
        return children;
    }

    public void setChildren(Collection<SysDept> children) {
        this.children = children;
    }

    private Collection<SysUser> sysusers;

    @OneToMany(mappedBy = "sysdept")
    public Collection<SysUser> getSysusers() {
        return sysusers;
    }

    public void setSysusers(Collection<SysUser> sysusers) {
        this.sysusers = sysusers;
    }

    //from now add by xiejiao

    private Set<DocCategory> viewDocPermitted;
    
    @ManyToMany(mappedBy = "viewPermittedDept")
    public Set<DocCategory> getViewDocPermitted() {
        return viewDocPermitted;
    }

    public void setViewDocPermitted(Set<DocCategory> viewDocPermitted) {
        this.viewDocPermitted = viewDocPermitted;
    }

    private Set<DocCategory> editDocPermitted;
    
    @ManyToMany(mappedBy = "editPermittedDept")
    public Set<DocCategory> getEditDocPermitted() {
        return editDocPermitted;
    }

    public void setEditDocPermitted(Set<DocCategory> editDocPermitted) {
        this.editDocPermitted = editDocPermitted;
    }
}
