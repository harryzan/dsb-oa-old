package gov.dsb.core.domain;

import gov.dsb.core.domain.base.IdEntity;
import gov.dsb.core.interceptor.Treeable;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2008-12-12
 * Time: 16:29:25
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SYSCODELIST")
public class SysCodeList extends IdEntity implements Treeable {
    protected Long id;

    /************ 系统编码定义 ****************/

    // 结构类别
    public static final String STRUCTURETYPE_SECTION = "section";

    public static final String STRUCTURETYPE_COMPONENT = "component";

    public static final String STRUCTURETYPE_CONBINETS = "conbinets";

    public static final String STRUCTURETYPE_OTHER = "other";

    // 采集手段
    public static final String ACQUIREMODE_AUTO = "auto";

    public static final String ACQUIREMODE_MANUAL = "manual";

    // 监测类型 MONITORTYPE
    public static final String MONITORTYPE_HEALTHEVALUATE = "healthevaluate";

    // 通道类型
    public static final String CHANNELTYPE_DATA = "1";

    public static final String CHANNELTYPE_STATUS = "3";

    public static final String CHANNELTYPE_STRING = "2";

    public static final String CHANNELTYPE_CALCULATE = "4";

    // 结构部位
    public static final String POSITIONCODE_TOPBEAM = "1";

    public static final String POSITIONCODE_BOTTOMBEAM = "2";

    public static final String POSITIONCODE_PM61 = "3";

    public static final String POSITIONCODE_PM62 = "4";

    public static final String POSITIONCODE_MAINCHANNEL = "5";

    public static final String POSITIONCODE_NONE = "0";  //ZERO

    public static final String POSITIONCODE_RING49 = "A";

    public static final String POSITIONCODE_RING984 = "B";

    public static final String POSITIONCODE_RING985 = "C";

    public static final String POSITIONCODE_RING986 = "D";

    public static final String POSITIONCODE_RING2667 = "E";

    public static final String POSITIONCODE_RING2985 = "F";

    public static final String POSITIONCODE_RING2986 = "G";

    public static final String POSITIONCODE_RING2987 = "H";

    public static final String POSITIONCODE_RING3077 = "J";

    public static final String POSITIONCODE_RING3078 = "K";

    public static final String POSITIONCODE_RING3079 = "M";

    public static final String POSITIONCODE_RING3429 = "N";

    public static final String POSITIONCODE_RING3430 = "P";

    public static final String POSITIONCODE_RING3431 = "R";

    // 存储制度
    public static final String BSSTYPE_JUNIOR = "1";

    public static final String BSSTYPE_MIDDLE = "2";

    public static final String BSSTYPE_SENIOR = "3";

    // 权限类型、
    public static final String PRIVILEGETYPE_MENU = "menu";

    public static final String PRIVILEGETYPE_DOC = "doc";

    public static final String PRIVILEGETYPE_CAPACITY = "capacity";

    // 评估体系类型
    public static final String EVALUATETYPE_MONTH = "month";

    public static final String EVALUATETYPE_YEAR = "year";

    // 评估节点类型
    public static final String ITEMTYPE_LOGICGROUP = "logicgroup";

    public static final String ITEMTYPE_MONITORPROJ = "monitorproj";

    // 预警类型
    public static final String ALARMTYPE_SHORT = "active";

    public static final String ALARMTYPE_CONTINUANCE = "along";

    // 预警方式
    public static final String ALARMMETHOD_MANUAL = "manual";

    public static final String ALARMMETHOD_ACTIVE= "active";

    // 指标算法
    // 可靠度指标算法
    public static final String INDEXALGORITHMTYPE_RELIABILITY = "reliability";
    // add 2010/02/24
    public static final String INDEXALGORITHMTYPE_RELIABILITY2 = "reliability2";
    // add 2010/09/02
    public static final String INDEXALGORITHMTYPE_RELIABILITY3 = "reliability3";
    // 隶属度指标算法
    public static final String INDEXALGORITHMTYPE_MEMBERSHIP = "membership";

    // 评估项方法
    public static final String ITEMMETHOD_AVG = "avg";

    public static final String ITEMMETHOD_MAX = "max";

    public static final String ITEMMETHOD_MIN = "min";

    public static final String ITEMMETHOD_FUZZY = "fuzzy";

    // 人工检测报告类型
    public static final String REPORTTYPE_TunnelD = "TunnelD";

    public static final String REPORTTYPE_SSZ = "SSZ";

    public static final String REPORTTYPE_Bline = "Bline";

    public static final String REPORTTYPE_Extend = "Extend";

    public static final String REPORTTYPE_DEFAULT = "default";

    //人工检测项目运算通道算法

    public static final String CHANNELCALCULATE_NORMAL = "normal";

    public static final String CHANNELCALCULATE_SUMDESIGN = "sumdesign";

    public static final String CHANNELCALCULATE_SUMZERO = "sumzero";

    public static final String CHANNELCALCULATE_SSZ = "ssz";

    public static final String CHANNELCALCULATE_SUM = "sum";

    public static final String CHANNELCALCULATE_RISK = "risk";

    public static final String CHANNELCALCULATE_SUMRISK = "sumrisk";

    public static final String CHANNELCALCULATE_RISKSPEED = "riskspeed";

    public static final String CHANNELCALCULATE_SUMRISKSPEED = "sumriskspeed";

    public static final String CHANNELCALCULATE_NORMAL1000 = "normal1000";

    public static final String CHANNELCALCULATE_SUMDESIGN1000 = "sumdesign1000";

    public static final String CHANNELCALCULATE_SUMZERO1000 = "sumzero1000";


    //报警推送类型
    public static final String ALARMPUSHTYPE_SYSTEMALARM = "systemalarm";

    public static final String ALARMPUSHTYPE_STRUCTUREALARMTYPE = "structurealarmtype";

    public static final String ALARMPUSHTYPE_LOGICCHANNEL = "logicchannel";

    public static final String ALARMPUSHTYPE_CHANNEL = "channel";

    public static final String COMPONENT_TYPE = "componenttype";


    // 系统维护类型
    public static final String SYSTEMMAINTAINTYPE_DEVICEREBOOT = "devicereboot";
    public static final String SYSTEMMAINTAINTYPE_SOFTWAREREBOOT = "softwarereboot";
    public static final String SYSTEMMAINTAINTYPE_SOFTWAREDEBUG = "softwaredebuging";
    public static final String SYSTEMMAINTAINTYPE_SOFTWAREUPDATE = "softwareupdate";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    private String listcode;

    @Basic
    @Column(name = "LISTCODE", length = 100)
    public String getListcode() {
        return listcode;
    }

    public void setListcode(String listcode) {
        this.listcode = listcode;
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

    private String listname;

    @Basic
    @Column(name = "LISTNAME", length = 100)
    public String getListname() {
        return listname;
    }

    public void setListname(String listname) {
        this.listname = listname;
    }

    private String description;

    @Basic
    @Column(name = "DESCRIPTION", length = 1000)
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

    private String tag;

    @Basic
    @Column(name = "TAG", length = 100)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
    @Column(name = "TREEID", length = 1000)
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

        SysCodeList that = (SysCodeList) o;

        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (leaf != null ? !leaf.equals(that.leaf) : that.leaf != null) {
            return false;
        }
        if (listcode != null ? !listcode.equals(that.listcode) : that.listcode != null) {
            return false;
        }
        if (listname != null ? !listname.equals(that.listname) : that.listname != null) {
            return false;
        }
        if (orderno != null ? !orderno.equals(that.orderno) : that.orderno != null) {
            return false;
        }
        if (reserved != null ? !reserved.equals(that.reserved) : that.reserved != null) {
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
        result = 31 * result + (listcode != null ? listcode.hashCode() : 0);
        result = 31 * result + (orderno != null ? orderno.hashCode() : 0);
        result = 31 * result + (listname != null ? listname.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (reserved != null ? reserved.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (leaf != null ? leaf.hashCode() : 0);
        result = 31 * result + (treeid != null ? treeid.hashCode() : 0);
        return result;
    }

    private SysCode syscode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODEID", referencedColumnName = "ID", nullable = false)
    public SysCode getSyscode() {
        return syscode;
    }

    public void setSyscode(SysCode syscode) {
        this.syscode = syscode;
    }

    private SysCodeList parent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENTID", referencedColumnName = "ID")
    public SysCodeList getParent() {
        return parent;
    }

    public void setParent(SysCodeList parent) {
        this.parent = parent;
    }

    private Set<SysCodeList> children;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    public Set<SysCodeList> getChildren() {
        return children;
    }

    public void setChildren(Set<SysCodeList> children) {
        this.children = children;
    }
}
