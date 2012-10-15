package gov.dsb.core.utils;

import gov.dsb.core.common.Const;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-3-18
 * Time: 13:03:18
 * To change this template use File | Settings | File Templates.
 */
public final class TimeHelper {
    //private static SimpleDateFormat filenameTimeFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    //private static SimpleDateFormat directoryTimeFormat = new SimpleDateFormat("yyyyMM");
    //private static SimpleDateFormat datetimeFromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    //public static long DAY = 24 * 60 * 60 * 1000L;

    /**
     * 私有构造函数 该类不可以实例化 是一个工具类
     */
    private TimeHelper() {
    }

    /**
     * 获取当前时间
     * @return long
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间的格式化后的字符串形式
     * @return  String
     */
    public static synchronized String getCurrentTimeString() {
        SimpleDateFormat datetimeFromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return datetimeFromat.format(new Timestamp(getCurrentTime()));
    }

    /**
     * 获取当前的天数
     * @return long
     */
    public static long getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 给定时间和内容 获取指定值
     * @param t long 时间
     * @param type   int 类型
     * @return   int
     */
    private static int get(long t, int type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(t);
        return calendar.get(type);
    }

    /**
     * 获取年数
     * @param t long
     * @return   int
     */
    public static int getYear(long t) {
        return get(t, Calendar.YEAR);
    }

    /**
     * 获取月数
     * @param t  long
     * @return int
     */
    public static int getMonth(long t) {
        return get(t, Calendar.MONTH);
    }

    /**
     * 获取天时数
     * @param t  long
     * @return   int
     */
    public static int getHourOfDay(long t) {
        return get(t, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取月日数
     * @param t   long
     * @return        int
     */
    public static int getDayOfMonth(long t) {
        return get(t, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前时
     * @return  long
     */
    public static long getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }


    /**
     * 将采样周期转换为毫秒级的采样周期。
     * 如: 1MS=1L
     * 1S=1000L
     * 1M=60000L
     * 1H=3600000L
     * 1D=86400000L
     * @param dtString 采样周期
     * @return 采样周期。
     */
    public static long dtString2DtLong(String dtString) {
        long dtLong = Long.MAX_VALUE;
        if (dtString.endsWith(Const.DTUNIT_YEAR)) {
            int count = (int) Double.parseDouble(dtString.substring(0, dtString.indexOf(Const.DTUNIT_YEAR)));
            dtLong = 1000L * 24 * 3600 * 365 * count;
         }
        if (dtString.endsWith(Const.DTUNIT_MONTH)) {
            int count = (int) Double.parseDouble(dtString.substring(0, dtString.indexOf(Const.DTUNIT_MONTH)));
            dtLong = 1000L * 24 * 3600 * 30 * count;
         }
        else if (dtString.endsWith(Const.DTUNIT_WEEK)) {
            int count = (int) Double.parseDouble(dtString.substring(0, dtString.indexOf(Const.DTUNIT_WEEK)));
            dtLong = 1000L * 7 * 24 * 3600 * count;
        }
        else if (dtString.endsWith(Const.DTUNIT_DAY)) {
            int count = (int) Double.parseDouble(dtString.substring(0, dtString.indexOf(Const.DTUNIT_DAY)));
            dtLong = 1000L * 24 * 3600 * count;
        }
        else if (dtString.endsWith(Const.DTUNIT_HOUR)) {
            int count = (int) Double.parseDouble(dtString.substring(0, dtString.indexOf(Const.DTUNIT_HOUR)));
            dtLong = 1000L * 3600 * count;

        }
        else if (dtString.endsWith(Const.DTUNIT_MINUTE)) {
            int count = (int) Double.parseDouble(dtString.substring(0, dtString.indexOf(Const.DTUNIT_MINUTE)));
            dtLong = 1000L * 60 * count;
        }
        else if (dtString.endsWith(Const.DTUNIT_MILLISECOND)) {
            int count = (int) Double.parseDouble(dtString.substring(0, dtString.indexOf(Const.DTUNIT_MILLISECOND)));
            dtLong = 1L * count;

        }
        else if (dtString.endsWith(Const.DTUNIT_SECOND)) {
            int count = (int) Double.parseDouble(dtString.substring(0, dtString.indexOf(Const.DTUNIT_SECOND)));
            dtLong = 1000L * count;
        }
        return dtLong;
    }

    /**
     * 根据timestamp的long值得到string值
     * @param dtLong
     * @return
     */
    public static String dtLong2DtString(Long dtLong) {
        Long result = 0l;
        result = dtLong / 1000;
        if (result > 60) {
            result = result / 60;
            if (result > 60) {
                result = result / 60;
                if (result > 24) {
                    result = result / 24;
                    return result.toString() + Const.DTUNIT_DAY;
                }
                return result.toString() + Const.DT_HOUR;
            }
            return result.toString() + Const.DT_MINUTE;
        }
        return result.toString() + Const.DT_SECOND;
    }



    /**
     * 根据dtString进行时间上的截取
     * @param calendar   Calendar
     * @param dtString   String
     * @return Calendar
     */
    public static Calendar truncTime(Calendar calendar, String dtString) {
        long dt = dtString2DtLong(dtString);

        /**if (dtString.endsWith(Const.DTUNIT_NAN)) {

         } else*/
        if (dtString.endsWith(Const.DTUNIT_YEAR)) {
            long timeMills = truncTime(calendar.getTimeInMillis(), dtString2DtLong("1D"));
            calendar.setTimeInMillis(timeMills);
            calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        }
        else if (dtString.endsWith(Const.DTUNIT_MONTH)) {
            long timeMills = truncTime(calendar.getTimeInMillis(), dtString2DtLong("1D"));
            calendar.setTimeInMillis(timeMills);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        }
        else if (dtString.endsWith(Const.DTUNIT_WEEK)) {
            long timeMills = truncTime(calendar.getTimeInMillis(), dtString2DtLong("1D"));
            calendar.setTimeInMillis(timeMills);
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
        }
        else if (dtString.endsWith(Const.DTUNIT_DAY)) {
            long timeMills = truncTime(calendar.getTimeInMillis(), dtString2DtLong("1D"));
            calendar.setTimeInMillis(timeMills);
            calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        }
        else if (dtString.endsWith(Const.DTUNIT_HOUR)) {
            long timeMills = truncTime(calendar.getTimeInMillis(), dt);
            calendar.setTimeInMillis(timeMills);
        }
        else if (dtString.endsWith(Const.DTUNIT_MINUTE)) {
            long timeMills = truncTime(calendar.getTimeInMillis(), dt);
            calendar.setTimeInMillis(timeMills);
        }
        else if (dtString.endsWith(Const.DTUNIT_MILLISECOND)) {
            long timeMills = truncTime(calendar.getTimeInMillis(), dt);
            calendar.setTimeInMillis(timeMills);
        }
        else if (dtString.endsWith(Const.DTUNIT_SECOND)) {
            long timeMills = truncTime(calendar.getTimeInMillis(), dt);
            calendar.setTimeInMillis(timeMills);
        }

        return calendar;
    }

    /**
     * wy 6.19 按 dt 对 time 进行截整
     * @param time  long
     * @param dt    long
     * @return   long
     */
    public static long truncTime(long time, long dt) {
        // todo 时区处理
        return time - (time % dt);
    }

    public static long truncTimeWithTimezone(long time, long dt) {
        // 时区处理
        long offset = 0;
        if (dt > dtString2DtLong("8H")) {
            offset = dtString2DtLong("8H");
        }
        time += offset;
        time -= (time % dt);
        return time - offset;
    }

    /**
     * 转化字符串如：200410252000000 为long型的数据
     * @param strTime    String
     * @return  long
     * @throws Exception     .
     */
    public static synchronized long parseTime(String strTime) throws Exception {
        SimpleDateFormat filenameTimeFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return filenameTimeFormat.parse(strTime).getTime();
    }

    /**
     * @param time  Timestamp
     * @return .
     */
    public static synchronized String formatTime(Timestamp time) {
        SimpleDateFormat filenameTimeFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return filenameTimeFormat.format(time);
    }

    /**
     * @param time   long
     * @return format time
     */
    public static String formatTime(long time) {
        return formatTime(new Timestamp(time));
    }

    /**
     * @param time  long
     * @return  String
     */
    public static String formatDirectory(long time) {
        return formatDirectory(new Timestamp(time));
    }

    /**
     * @param time   Timestamp
     * @return     String
     */
    public static synchronized String formatDirectory(Timestamp time) {
        SimpleDateFormat directoryTimeFormat = new SimpleDateFormat("yyyyMM");
        return directoryTimeFormat.format(time);
    }

    /**
     * @param time    long
     * @return            String
     */
    public static String formatDateTime(long time) {
        return formatDateTime(new Timestamp(time));
    }

    /**
     * @param time  Timestamp
     * @return     String
     */
    public static synchronized String formatDateTime(Timestamp time) {
        SimpleDateFormat datetimeFromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return datetimeFromat.format(time);
    }

    /**
     * 测试主函数
     * @param args  String[]
     */
    public static void main(String[] args) throws Exception {
//        String dtString = "1D";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = "2012-12-28";
//        Timestamp timestamp = Timestamp.valueOf(time);
//        System.out.println("parse = " + timestamp);
//        long l = truncTimeWithTimezone(Timestamp.valueOf(time).getTime(), dtString2DtLong(dtString));
//        System.out.println("l = " + new Timestamp(l));
//        Calendar calendar = Calendar.getInstance();
//        System.out.println("timestamp.toString() = " + timestamp.toString());
//        truncTime(calendar, dtString);
//        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
//        System.out.println("timestamp = " + timestamp);
//        timestamp = new Timestamp(calendar.getTimeInMillis());
//        System.out.println("timestamp.toString() = " + timestamp.toString());

//        Long test = 5*60*1000l;
//        String s = TimeHelper.dtLong2DtString(test);
//        System.out.println("s = " + s);
//        String s = null;
//        long l = parseTime(s);
//        System.out.println("l = " + l);
        Date d = sdf.parse(time);
        System.out.println("d = " + d);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        int weekofyear = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("weekofyear = " + weekofyear);
        int weekofmonth = calendar.get(Calendar.WEEK_OF_MONTH);
        System.out.println("weekofmonth = " + weekofmonth);
        int month = calendar.get(Calendar.MONTH)+1;
        System.out.println("month = " + month);
        int firstDayOfWeek = calendar.getFirstDayOfWeek();
        System.out.println("firstDayOfWeek = " + firstDayOfWeek);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("day = " + day);
    }

}
