package gov.dsb.core.utils;

import java.text.NumberFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-3-20
 * Time: 11:23:52
 * To change this template use File | Settings | File Templates.
 */
public final class MathHelper {

    /**
     * 私有的构造函数 工具类
     */
    private MathHelper(){
    }

    /**
     * double类型的最小值
     */
    private static final double NULLDOUBLE = 4.9E-324;

    /**
     * 根据输入的条件 将double类型的数据进行格式化 并返回字符串
     * @param val     double
     * @param decimal int
     * @return String
     */
    public static String formatCurrency(double val, int decimal) {
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(true);
        format.setMaximumFractionDigits(decimal);
        if (val == NULLDOUBLE){
            return "";
        }

        return format.format(val);
    }

    /**
     * @param val     double
     * @param decimal int
     * @return String
     */
    public static String formatDouble(double val, int decimal) {
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        format.setMaximumFractionDigits(decimal);
        if (val == NULLDOUBLE){
            return "";
        }
        return format.format(val);
    }

    /**
     * 将float型数据格式化
     * @param val     要格式化的double数
     * @param decimal 精度 int
     * @return String
     */
    public static String formatFloat(float val, int decimal) {
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        format.setMaximumFractionDigits(decimal);
        if (val == NULLDOUBLE) {
            return "";
        }

        return format.format(val);
    }

    /**
     * 四舍五入
     * @param d       double型数据
     * @param decimal 四舍五入的精度要求
     * @return double
     */
    public static double roundDouble(double d, int decimal) {
        return Math.round(d * Math.pow(10, decimal)) / Math.pow(10, decimal);
    }

    /**
     * 四舍五入
     * @param f       要进行四舍五入的float数
     * @param decimal 精度 int
     * @return String
     */
    public static float roundFloat(float f, int decimal) {
        return Math.round(f * Math.pow(10, decimal)) / (float) Math.pow(10, decimal);
    }

    /**
     * 用于测试的主函数
     * @param args String[]
     */
    public static void main(String[] args) {
//        System.out.println(formatCurrency(20000000.1234, 3));
        double amount = 54321.0;
        //eshop format
        NumberFormat myformat = NumberFormat.getInstance();
        myformat.setMaximumFractionDigits(2);
        myformat.setGroupingUsed(true);
        System.out.println(myformat.format(amount));

    }
}
