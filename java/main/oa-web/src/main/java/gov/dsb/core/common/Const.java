package gov.dsb.core.common;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-3-18
 * Time: 9:55:48
 * To change this template use File | Settings | File Templates.
 */
public final class Const {
    /**
     * 私有的构造函数
     */
    private Const() {
    }

    /**
     * 标识为数据数据
     */
    public static final String DATAORSTATUS_DATA = "D";
    /**
     * 标识为状态数据
     */
    public static final String DATAORSTATUS_STATUS = "S";

    /**
     * 没有包含时间戳
     */
    public static final String ISINCLUDETIME_YES = "Y";
    /**
     * 包含时间戳
     */
    public static final String ISINCLUDETIME_NOT = "N";

    /**
     * 没有采样周期单位
     */
    public static final String DTUNIT_NAN = "NA";
    /**
     * 采样周期单位为毫秒
     */
    public static final String DTUNIT_MILLISECOND = "MS";
    /**
     * 采样周期单位为秒
     */
    public static final String DTUNIT_SECOND = "S";
    /**
     * 采样周期单位为分钟
     */
    public static final String DTUNIT_MINUTE = "M";
    /**
     * 采样周期单位为小时
     */
    public static final String DTUNIT_HOUR = "H";
    /**
     * 采样周期单位为天
     */
    public static final String DTUNIT_DAY = "D";
    // add by zyl on 2007.9.3
    /**
     * 采样周期单位为星期
     */
    public static final String DTUNIT_WEEK = "W";
    /**
     * 采样周期单位为月
     */
    public static final String DTUNIT_MONTH = "MM";
    /**
     * 采样周期单位为年
     */
    public static final String DTUNIT_YEAR = "Y";

    /**
     * 临时文件名称
     */
    public static final String TMP_FILE = ".TMP";

    /**
     * 空的float数据 即太大
     */
    public static final float EMPTY_FLOAT = 9999999;

    /**
     * 状态换行
     */
    public static final String STATUS_BREAKLINE = "\r\n";
    /**
     * 状态分割
     */
    public static final String STATUS_SPLIT = "/";

    /**
     * add by zyl on 2007.10.9
     * 历史时间
     */
    public static final int HISTORY_DATA_LIMIT = 1440000;


//1、是否删除
//2、是否有效
//3、是否叶节点
//4、是否显示
//5、是否单位：是表示单位、否表示部门
//6、是否系统单位：是表示系统单位、否表示第三方单位
    public static final String FLAG_TRUE = "1";
    public static final String FLAG_FALSE = "0";

    //树根节点缺省显示名称
    public static final String TREE_ROOT = "根节点";

    //选择对话框缺省标题
    public static final String SELECT_TITLE = "请选择";

    //服务器类型
    public static final String SERVER_TYPE_TOMCAT = "Tomcat";
    public static final String SERVER_TYPE_WEBLOGIC = "Weblogic";

    public static final String SELECTOPTION = "<option value=''{0}'' >{1}</option>\n";
    public static final String SELECT_OPTION = "<option value=''{0}'' {2}>{1}</option>\n";

    public static final String DT_DAY = "D";
    public static final String DT_HOUR = "H";
    public static final String DT_MINUTE = "M";
    public static final String DT_SECOND = "S";
    public static final String DT_MILLSECOND = "MS";
    public static final String DT_NAN = "NA"
            ;
    public static final String DT_DAY_S = "天";
    public static final String DT_HOUR_S = "小时";
    public static final String DT_MINUTE_S = "分钟";
    public static final String DT_SECOND_S = "秒";
    public static final String DT_MILLSECOND_S = "毫秒";
    public static final String DT_NAN_S = "其他";
    public static final String[][] DT = {{DT_DAY, DT_DAY_S}, {DT_HOUR, DT_HOUR_S}, {DT_MINUTE, DT_MINUTE_S}, {DT_SECOND, DT_SECOND_S}, {DT_MILLSECOND, DT_MILLSECOND_S}, {DT_NAN, DT_NAN_S}};

    public static final long ONEDAY = 24 * 3600 * 1000;
    public static final long ONEHOUR = 3600 * 1000;
}
