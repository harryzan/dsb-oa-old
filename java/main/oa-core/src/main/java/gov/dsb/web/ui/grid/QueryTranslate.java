package gov.dsb.web.ui.grid;

import gov.dsb.core.utils.StringHelp;
import gov.dsb.web.ui.GridConstant;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.hibernate.util.StringHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 该类用于将自定的条件对象condition集合转换成hql
 *
 * @author Youyiming
 */
public class QueryTranslate {

    private String basicQry;

    private List<Condition> conditions = new ArrayList<Condition>();

    private static final String dot = ".";

    private static final String blank = " ";

    public QueryTranslate(String basicHql, List<Condition> conditions) {
        this.basicQry = basicHql;
        this.conditions = conditions;
    }

    //根据json格式的字符串进行构造
    public QueryTranslate(String basicHql, String condition) {
        this.basicQry = basicHql;
        if (!StringHelp.isEmpty(condition)) {
            JSONObject jsonObject = JSONObject.fromObject(condition);
            for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
                conditions.add((Condition) JSONObject.toBean(jsonObject.getJSONObject(iterator.next().toString()),
                        Condition.class));
            }
        }
    }

    public String getBasicQry() {
        return basicQry;
    }

    public void setBasicQry(String basicSql) {
        this.basicQry = basicSql;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public String getColumnName(Condition condition) {
        if (null != condition.getEntity() && !StringHelper.isEmpty(condition.getEntity())) {
            return condition.getEntity() + dot + condition.getPropertyName();
        }
        else {
            return condition.getPropertyName();
        }
    }

    /**
     * to verdict the operator whether it is  a Comparison sign.
     *
     * @param condition
     * @return
     */
    public boolean opIsComparison(Condition condition) {
        boolean isComparison = false;
        if (!StringHelper.isEmpty(condition.getOperator())) {
            if (condition.getOperator().equals(GridConstant.OPRATER_EQUAL)) {
                isComparison = true;
            }
            else if (condition.getOperator().equals(GridConstant.OPRATER_UNEQUAL)) {
                isComparison = true;
            }
            else if (condition.getOperator().equals(GridConstant.OPRATER_GREATER)) {
                isComparison = true;
            }
            else if (condition.getOperator().equals(GridConstant.OPRATER_GREATER_AND_EQUAL)) {
                isComparison = true;
            }
            else if (condition.getOperator().equals(GridConstant.OPRATER_LESS)) {
                isComparison = true;
            }
            else if (condition.getOperator().equals(GridConstant.OPRATER_LESS_AND_EQUAL)) {
                isComparison = true;
            }
        }
        return isComparison;
    }

    /**
     * to verdict the operator whether it is  contains "null".
     *
     * @param condition
     * @return
     */
    public boolean opContainsNull(Condition condition) {
        boolean containsNull = false;
        if (!StringHelper.isEmpty(condition.getOperator())) {
            if (condition.getOperator().equals(GridConstant.OPRATER_ISNULL)) {
                containsNull = true;
            }
            else if (condition.getOperator().equals(GridConstant.OPRATER_ISNOTNULL)) {
                containsNull = true;
            }
        }
        return containsNull;
    }

    public boolean opContainsLike(Condition condition) {
        boolean containsLike = false;
        if (!StringHelper.isEmpty(condition.getOperator())) {
            if (condition.getOperator().equals(GridConstant.OPRATER_CONTAINS)) {
                containsLike = true;
            }
            else if (condition.getOperator().equals(GridConstant.OPRATER_NOT_CONTAINS)) {
                containsLike = true;
            }
            else if (condition.getOperator().equals(GridConstant.OPRATER_RIGHT_CONTAINS)) {
                containsLike = true;
            }
            else if (condition.getOperator().equals(GridConstant.OPRATER_LEFT_CONTAINS)) {
                containsLike = true;
            }
        }
        return containsLike;
    }

    public String comparisonToQuery(Condition condition) {
        if (condition.getType().equals(GridConstant.TYPE_IS_DATETIME)) {
            String firstValue = StringUtils.replace(condition.getFirstValue(), "T", " ");

            return getColumnName(condition) + condition.getOperator() + "'" + firstValue + "'";
        }
        else {
            return getColumnName(condition) + condition.getOperator() + "'" + condition.getFirstValue() + "'";
        }
    }

    public String containNullToQuery(Condition condition) {
        return getColumnName(condition) + " " + condition.getOperator();
    }

    public String containLikeToQuery(Condition condition) {
        return getColumnName(condition) + " " + condition.getOperator().replace("|", condition.getFirstValue());
    }

    public String betweenQuery(Condition condition) {
        if (condition.getType().equals(GridConstant.TYPE_IS_DATETIME)) {
            String firstValue = StringUtils.replace(condition.getFirstValue(), "T", " ");
            String secondValue = StringUtils.replace(condition.getSecondValue(), "T", " ");
            return getColumnName(condition) + " between '" + firstValue + "' AND '" + secondValue + "'";
        }
        else {
            return getColumnName(condition) + " between '" + condition.getFirstValue() + "' AND '" +
                    condition.getSecondValue() + "'";
        }
    }

    public String betweenSQLQuery(Condition condition) {
        if (condition.getType().equals(GridConstant.TYPE_IS_DATETIME)) {
            String firstValue = StringUtils.replace(condition.getFirstValue(), "T", " ");
            String secondValue = StringUtils.replace(condition.getSecondValue(), "T", " ");
            return getColumnName(condition) + " >= to_date('" + firstValue + "', 'yyyy-mm-dd   hh24:mi:ss')  AND " +
                    getColumnName(condition) + " < to_date('" + secondValue + "', 'yyyy-mm-dd   hh24:mi:ss') ";
        }
        else {
            return getColumnName(condition) + " > " + condition.getFirstValue() + " AND " +
                    getColumnName(condition) + " < " + condition.getSecondValue() + "";
        }
    }

    public String conditionToString() {
        List<Condition> conditionList = conditions;
        String queryConditon = "";
        for (Condition condition : conditionList) {
            if (opIsComparison(condition)) {
                queryConditon += comparisonToQuery(condition);
            }
            else if (opContainsNull(condition)) {
                queryConditon += containNullToQuery(condition);
            }
            else if (opContainsLike(condition)) {
                queryConditon += containLikeToQuery(condition);
            }
            else if (condition.getOperator().equals(GridConstant.OPRATER_BETWEEN)) {
                queryConditon += betweenQuery(condition);
            }

            if (!StringHelper.isEmpty(condition.getOperator())) {
                if (!StringHelper.isEmpty(condition.getAndOr())) {
                    queryConditon += blank + condition.getAndOr() + blank;
                }
                else {
                    queryConditon += " AND ";
                }
            }
        }
        if (!StringHelp.isEmpty(queryConditon)) {
            queryConditon =
                    queryConditon.substring(0, queryConditon.length() - 4);           //delete the last join-sign
        }
        return queryConditon;
    }

    public String conditionToSQLString() {
        List<Condition> conditionList = conditions;
        String queryConditon = "";
        for (Condition condition : conditionList) {
            String tempCondition = "";
            if (opIsComparison(condition)) {
                tempCondition = comparisonToQuery(condition);
            }
            else if (opContainsNull(condition)) {
                tempCondition = containNullToQuery(condition);
            }
            else if (opContainsLike(condition)) {
                tempCondition = containLikeToQuery(condition);
            }
            else if (condition.getOperator().equals(GridConstant.OPRATER_BETWEEN)) {
                tempCondition = betweenSQLQuery(condition);
            }

            if(!"".equals(tempCondition)){
                tempCondition = "(" + tempCondition + ")";
            }

            if(tempCondition.indexOf("and") >= 0 && queryConditon.indexOf("((") < 0){
                tempCondition = "(" + tempCondition;
            }

            if (!StringHelper.isEmpty(condition.getOperator())) {
                if (!StringHelper.isEmpty(condition.getAndOr())) {
                    tempCondition += blank + condition.getAndOr() + blank;
                }
                else if(tempCondition.indexOf("and") >= 0){
                    tempCondition += " or ";
                }
                else {
                    tempCondition += " and ";
                }
            }

            queryConditon += tempCondition;
        }
        if (!StringHelp.isEmpty(queryConditon)) {
            queryConditon =
                    queryConditon.substring(0, queryConditon.length() - 4);           //delete the last join-sign
        }
        if(queryConditon.indexOf("((") >= 0){
            queryConditon += ")";
        }
        return queryConditon;
    }


    public String toString() {
        String ret = basicQry;
        String coditionString = conditionToString();
//        System.out.println("coditionString = " + coditionString);
        if (!StringHelp.isEmpty(coditionString)) {

            if (!"".equals(coditionString)) {
                if (basicQry.toUpperCase().indexOf("WHERE") == -1) {
                    ret = ret + " WHERE ";
                }

                if (!basicQry.endsWith(" ")) {
                    ret = ret + " ";
                }

                if (!ret.trim().toUpperCase().endsWith("WHERE")) {
                    ret = ret + " AND ";
                }

                ret += "(" + coditionString + ")";
            }
        }
        return ret;
    }
}
