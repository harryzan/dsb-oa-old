package gov.dsb.web.ui;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-6-29
 * Time: 15:16:26
 * To change this template use File | Settings | File Templates.
 */
public interface GridConstant {

    /**
     * ***********************************grid查询中用到的操作符及类型常量 begin **********************
     */
    public static final String OPRATER_EQUAL = "=";

    public static final String OPRATER_UNEQUAL = "<>";

    public static final String OPRATER_GREATER_AND_EQUAL = ">=";

    public static final String OPRATER_LESS_AND_EQUAL = "<=";

    public static final String OPRATER_GREATER = ">";

    public static final String OPRATER_LESS = "<";

    public static final String OPRATER_CONTAINS = "like '%|%'";

    public static final String OPRATER_NOT_CONTAINS = "not like '%|%'";

    public static final String OPRATER_LEFT_CONTAINS = "like '|%'";

    public static final String OPRATER_RIGHT_CONTAINS = "like '%|'";

    public static final String OPRATER_BETWEEN = "between";

    public static final String OPRATER_ISNULL = "is null";

    public static final String OPRATER_ISNOTNULL = "is not null";

    public static final String TYPE_IS_DATETIME = "Datetime";

    public static final String TYPE_IS_NUMBER = "Number";

    public static final String TYPE_IS_STRING = "String";
    /**********************************grid查询中用到的操作符及类型常量 end *************************/

}
