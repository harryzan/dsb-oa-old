package gov.dsb.core.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2007-11-16
 * Time: 13:46:06
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeHelp {

    /**
     * 获取日期(如：2005-05-05)
     *
     * @return
     */
    public static String getCurrentDate() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String strdate = time.toString();
        return (strdate.substring(0, 10));
    }

    /**
     * 格式化日期，显示成带“ 年 月 日”的格式
     * 一般用于打印输出
     *
     * @param date
     * @return
     */
    public static String formatDateGBK(Date date) {
        String str = date == null ? null : String.valueOf(date);
        if (null != str && !"".equals(str)) {
            str = str.substring(0, 4) + "年" + str.substring(5, 7) + "月" + str.substring(8, 10) + "日";
        }
        else {
            str = "　　年" + "　月" + "　日";
        }
        return str;
    }

    /**
     * 格式化日期，显示成带“ 年 月 日 时 分”的格式
     * 一般用于打印输出
     *
     * @return
     */
    public static String formatDateGBK(Timestamp timestamp) {
        String str = formatTimestamp(timestamp, "YYYY-MM-DD HH:MM");
        if (null != str && !"".equals(str)) {
            str = str.substring(0, 4) + "年" + str.substring(5, 7) + "月" + str.substring(8, 10) + "日" +
                    str.substring(11, 13) + "时" + str.substring(14, 16) + "分";
        }
        else {
            str = "　　年" + "　月" + "　日" + "　时" + "　分";
        }
        return str;
    }

    /**
     * 格式化日期时间
     *
     * @param timestamp
     * @param format
     * @return
     */
    public static final String formatTimestamp(Timestamp timestamp, String format) {
        if (timestamp == null) {
            return "";
        }

        String result = timestamp.toString();
        if (result.length() > 18) {
            if ("YYYY-MM-DD HH:MM".equalsIgnoreCase(format)) {
                return result.substring(0, 16);
            }
            else if ("MM-DD HH:MM".equalsIgnoreCase(format)) {
                return result.substring(5, 16);
            }
            else if ("YYYY-MM-DD HH".equalsIgnoreCase(format)) {
                return result.substring(0, 13);
            }
            else if ("YYYY-MM-DD".equalsIgnoreCase(format)) {
                return result.substring(0, 10);
            }
            else if ("YYYY-MM".equalsIgnoreCase(format)) {
                return result.substring(0, 7);
            }
            else if ("YYYY".equalsIgnoreCase(format)) {
                return result.substring(0, 4);
            }
            else if ("HH:MM".equalsIgnoreCase(format)) {
                return result.substring(11, 16);
            }
            else if ("HH:MM:SS".equalsIgnoreCase(format)) {
                return result.substring(11, 19);
            }
            else {
                return result.substring(0, 19);
            }
        }
        return "";
    }

    /**
     * 得到当前日期加入指定的second
     *
     * @return datetime 增加后的日期时间
     */
    public static Timestamp getCreaseTime(int hour, int minute, int second) {
        long seconds = hour * 60 * 60 + minute * 60 + second;
        return getCreaseTime(seconds);
    }

    /**
     * 得到当前日期加入指定的second
     *
     * @return datetime 增加后的日期时间
     */
    public static Timestamp getCreaseTime(long second) {
        Timestamp nowDatetime = new Timestamp(System.currentTimeMillis());
        long time = (nowDatetime.getTime() / 1000) + second;
        nowDatetime.setTime(time * 1000);
        return nowDatetime;
    }

    /**
     * 转换字符串为时间格式;
     *
     * @param dateStr    时间字符串;
     * @param dateFormat 日期字符串格式化的类型 1为"yyyy-MM-dd"格式 2为"yyyy-MM-dd
     *                   hh:mm:ss"格式,3为yyyy/MM/dd格式
     * @return 返回为 格式化后的java.util.date;
     */
    public static java.util.Date dateConvert(String dateStr, int dateFormat) {

        if (null == dateStr || dateStr.trim().equals("")) {
            return null;
        }
        SimpleDateFormat dateFormatType = null;
        if (1 == dateFormat) {
            dateFormatType = new SimpleDateFormat("yyyy-MM-dd");
        }
        else if (2 == dateFormat) {
            dateFormatType = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        else if (3 == dateFormat) {
            dateFormatType = new SimpleDateFormat("yyyy/MM/dd");
        }
        try {
            return dateFormatType.parse(dateStr);
        }
        catch (ParseException e) {
            try {
                // 当转换为2时,当日期字符串为yyyy-MM-dd,而不为yyyy-MM-dd hh:mm:ss 时,
                // 也会出现ParseException 异常,但我们大多时,是使用yyyy-MM-dd格式的date类
                // 因此为了方便在下面,试着转换为yyyy-MM-dd的date类的实例
                dateFormatType = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormatType.parse(dateStr);
            }
            catch (ParseException e1) {
                StackTraceElement trace[] = e1.getStackTrace();
//                System.out.println(trace[0].getClass() + "."
//                        + trace[0].getMethodName() + "方法中的第 "
//                        + trace[0].getLineNumber() + " 行出现" + e1);
                return null;
            }
        }
        catch (Exception e) {
            StackTraceElement trace[] = e.getStackTrace();
//            System.out.println(trace[0].getClass() + "."
//                    + trace[0].getMethodName() + "方法中的第 "
//                    + trace[0].getLineNumber() + " 行出现" + e);
            return null;
        }
    }


    public static void main(String[] args) {

    }
}
