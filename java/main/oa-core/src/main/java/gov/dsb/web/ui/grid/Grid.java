package gov.dsb.web.ui.grid;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.util.StringHelper;

import java.util.*;

/**
 * @author Tian Chungang
 */
public class Grid {

    /**
     * 将col转化为页面grid显示所需要的rows
     *
     * @param col         从数据库取出来的数据（List,set）
     * @param columnNames 列名 格式：以逗号隔开的字符串（"name，id"）
     * @return
     * @throws Exception
     */
    public static List<Row> gridValue2Rows(Collection col, String columnNames) throws Exception {
        List<Map> gridValue = getGridValue(col, columnNames);
        return mapValue2Rows(gridValue);
    }

    public static List<Row> mapValue2Rows(List<Map> maps) {
        List<Row> rows = new ArrayList<Row>();
        for (Map map : maps) {
            Set<Map.Entry> set = map.entrySet();
            Row row = new Row();
            row.setId(String.valueOf(map.get("id")));
            for (Map.Entry entry : set) {
                String key = entry.getKey().toString();
                String value = entry.getValue()==null?"":entry.getValue().toString();

                Cell cell = new Cell();
                cell.setValue(value);
                cell.setKey(key);

                row.addCell(cell);
            }
            rows.add(row);
        }
        return rows;
    }

    /**
     * 获得obj属性columnName的值.
     *
     * @param obj        .
     * @param columnName .
     * @return .
     * @throws Exception .
     */
    private static String getColumnValue(Object obj, String columnName) throws Exception {
        String rel = "";
        if (!columnName.contains(".")) {
            Object value = PropertyUtils.getProperty(obj, columnName);
            if (null != value) {
                rel = value.toString();
            }
        }
        else {
            String firstName = columnName.substring(0, columnName.indexOf("."));
            Object object = PropertyUtils.getProperty(obj, firstName);
            if (null != object) {
                String lastName = columnName.substring(columnName.indexOf(".") + 1, columnName.length());
                rel = getColumnValue(object, lastName);
            }
            else {
                rel = "";
            }
        }
        return rel;
    }

    private static Map getRowValue(Object obj, String[] columnNames)
            throws Exception, NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (String columnName : columnNames) {
            map.put(columnName.replace(".", "_"), getColumnValue(obj, columnName));
        }
        return map;
    }

    private static List<Map> getGridValue(Collection col, String columnNames) throws Exception {
        String[] column_names = string2Array(columnNames);
        List<Map> valueList = new ArrayList<Map>();
        for (Object aList : col) {
            valueList.add(getRowValue(aList, column_names));
        }
        return valueList;
    }

    /**
     * 把用“,”号分隔开的字段名字符串转化成字段名数组
     *
     * @param columnNames
     * @return
     * @throws Exception
     */
    private static String[] string2Array(String columnNames) throws Exception {
        String[] columns;
        if (StringHelper.isEmpty(columnNames)) {
            throw new Exception("您输入的列名为空");
        }
        else {
            columns = columnNames.split(",");
        }
        return columns;
    }
}
