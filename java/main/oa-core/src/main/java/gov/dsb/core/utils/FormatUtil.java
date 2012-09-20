package gov.dsb.core.utils;

import java.sql.Timestamp;
import java.text.NumberFormat;

public class FormatUtil {


    public static final String format(String s, Long tag) {
        return format(s, String.valueOf(tag));
    }

    public static final String format(String s, Long tag1, Long tag2) {
        return format(s, String.valueOf(tag1), String.valueOf(tag2));
    }

    public static final String format(String s, Long tag1, Long tag2, Long tag3) {
        return format(s, String.valueOf(tag1), String.valueOf(tag2), String.valueOf(tag3));
    }

    public static final String format(String s, Long tag1, Long tag2, Long tag3, Long tag4) {
        return format(s, String.valueOf(tag1), String.valueOf(tag2), String.valueOf(tag3), String.valueOf(tag4));
    }

    public static final String format(String s, Integer tag) {
        return format(s, String.valueOf(tag));
    }

    public static final String format(String s, Integer tag1, Integer tag2) {
        return format(s, String.valueOf(tag1), String.valueOf(tag2));
    }

    public static final String format(String s, Integer tag1, Integer tag2, Integer tag3) {
        return format(s, String.valueOf(tag1), String.valueOf(tag2), String.valueOf(tag3));
    }

    public static final String format(String s, Integer tag1, Integer tag2, Integer tag3, Integer tag4) {
        return format(s, String.valueOf(tag1), String.valueOf(tag2), String.valueOf(tag3), String.valueOf(tag4));
    }


    public static final String format(String s, String tag) {
        String[] tags = {tag};
        return format(s, tags);
    }

    public static final String format(String s, String tag1, String tag2) {
        String[] tags = {tag1, tag2};
        return format(s, tags);
    }

    public static final String format(String s, String tag1, String tag2, String tag3) {
        String[] tags = {tag1, tag2, tag3};
        return format(s, tags);
    }

    public static final String format(String s, String tag1, String tag2, String tag3, String tag4) {
        String[] tags = {tag1, tag2, tag3, tag4};
        return format(s, tags);
    }

    public static final String format(String s, String tag1, String tag2, String tag3, String tag4, String tag5) {
        String[] tags = {tag1, tag2, tag3, tag4, tag5};
        return format(s, tags);
    }

    public static final String format(String s, String tag1, String tag2, String tag3, String tag4, String tag5,
                                      String tag6) {
        String[] tags = {tag1, tag2, tag3, tag4, tag5, tag6};
        return format(s, tags);
    }

    public static final String format(String s, String tag1, String tag2, String tag3, String tag4, String tag5,
                                      String tag6, String tag7) {
        String[] tags = {tag1, tag2, tag3, tag4, tag5, tag6, tag7};
        return format(s, tags);
    }

    public static final String format(String s, String[] tags) {
        for (int i = 0; i < tags.length; i++) {

            s = s.replaceAll("\\{" + i + "\\}", tags[i] == null ? "" : tags[i]);
        }
        return s;
    }

    public static final String formatTimestamp(Timestamp timestamp) {
        return DateTimeHelp.formatTimestamp(timestamp, "YYYY-MM-DD HH24:MI:SS");
    }

    public static final String formatTimestamp(long timemillis) {
        return DateTimeHelp.formatTimestamp(new Timestamp(timemillis), "YYYY-MM-DD HH24:MI:SS");
    }

    public static final String formatOracleDate(Timestamp timestamp) {
        return "to_date('" + formatTimestamp(timestamp) + "','yyyy-mm-dd hh24:mi:ss')";
    }

    public static final String formatOracleDate(long timemillis) {
        return "to_date('" + formatTimestamp(timemillis) + "','yyyy-mm-dd hh24:mi:ss')";
    }

    /**
     * 格式化数字，保留小数位数
     * @param val
     * @param decimal 保留小数 位数
     * @return
     */
    public static String format(double val, int decimal)
    {
        if (val == Double.NaN) return "";

        double minGap = Math.pow(0.1, decimal);
        if (val == Nulls.getNullDouble())
            return "";
        if (Math.abs(val - Nulls.getNullInt()) < minGap)
            return "";

        if (val == 0)
            return "0";

        boolean negative = val < 0;
        double abs = Math.abs(val);

        String zeroStr = "0.";
        for (int i = 0; i < decimal; i++)
            zeroStr += "0";

        if (abs < minGap)
            return negative ? "-" + zeroStr : zeroStr;

        double nearest = (double) ((int) abs);

        double minus = abs - nearest;

        if (minus == 0)
            return NumberFormat.getInstance().format((int) val);

        return NumberFormat.getInstance().format(Math.round(val * Math.pow(10, decimal)) / Math.pow(10, decimal));

    }

    public static void main(String[] args) {
    }

}
