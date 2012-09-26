package gov.dsb.core.common;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-3-19
 * Time: 17:07:27
 * To change this template use File | Settings | File Templates.
 */
public final class Common {

    /**
     * 私有的构造函数 工具类
     */
    private Common(){
    }
    /**
     * 将字符串转换成html格式
     * @param s 原字符串
     * @return 转换后的字符串
     */
    public static String escapeHtml(String s) {
        String html = s;
        if (html != null) {
            html = html.replace("\r\n", "<br>");
            html = html.replace(" ", "&nbsp;");
        }
        return html;
    }

    /**
     * 将长字符串只显示前面一部分
     * @param str    String
     * @param length int
     * @return String
     * @throws Exception     .
     */
    public static String formatLongStr(String str, int length) throws Exception {
        byte[] bs = str.getBytes();
        if (bs.length <= length) {
            return str;
        }

        boolean chinese = false;
        int i = 0;
        for (; i < length - 4; i++) {
            if (bs[i] < 0) {
                chinese = !chinese;
            }
        }

        if (chinese) {
            i++;
        }
        else if (bs[i] > 0) {
            i++;
        }
        return new String(bs, 0, i) + "...";
    }

    /**
     * 获取字符串在字符串数组中的位置
     * @param ss 字符串数组
     * @param s  要检索的字符串
     * @return int index
     */
    public static int indexOfStringArray(String[] ss, String s) {
        int result = -1;
        for (int i = 0; i < ss.length; i++) {
            if (((null == s) && (null == ss[i])) || (s != null && s.equals(ss[i]))) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * 判断一个字符串是否满足一个数字的条件
     * @param s 字符串
     * @return boolean
     */
    public static boolean isNumber(String s) {
        try {
            Float.parseFloat(s);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断一个字符串是否满足整形数字的条件
     * @param s String
     * @return boolean
     */
    public static boolean isInt(String s) {
        return isNumber(s) && (s.indexOf('.') < 0);
    }

    /**
     * 将List转换为字符串数组
     * @param al ArrayList
     * @return String[]
     */
    public static String[] arrayListtoString(ArrayList al) {
        String[] s = new String[al.size()];
        for (int i = 0; i < al.size(); i++) {
            s[i] = (String) al.get(i);
        }
        return s;
    }

    /**
     * 将String 型转换为RMIURL
     * @param rminame String
     * @return String
     */
    public static String formatRMIURL(String rminame) {
        try {
            int b = rminame.indexOf("//");
            int e = rminame.indexOf("/", b + 2);
            String hostName = rminame.substring(b + 2, e);
            int c = hostName.indexOf(":");
            if (c > 0) {
                e = (b + 2 + c);
            }
            hostName = rminame.substring(b + 2, e);
            String hostIp = InetAddress.getByName(hostName).getHostAddress();
            rminame = rminame.substring(0, b + 2) + hostIp + rminame.substring(e);
            return rminame;
        }
        catch (Exception e) {
            e.printStackTrace();
            return rminame;
        }


    }

    /**
     * 将字符串转换为 UnixStr
     * @param dosStr String
     * @return String
     */
    public static String toUnixStr(String dosStr) {
        //remove "\r" from dosStr
        return dosStr.replaceAll("\r\n", "\n");
    }

    /**
     * 将 unixStr 转换为 DosStr
     * @param unixStr String
     * @return String
     */
    public static String toDosStr(String unixStr) {
        //remove "\r" from dosStr
        return unixStr.replaceAll("\n", "\r\n");
    }

    /**
     * 用于测试的主函数
     * @param args String[]
     * @throws Exception    .
     */
    public static void main(String[] args) throws Exception {
        String s = "xxxdfd\r\nadfdf\r\ndfadf\ndf df";
        String s1 = escapeHtml(s);

        System.out.println("s1 = " + s1);


//        String rminame = "rmi://db_server:123/news";
//        String s = formatRMIURL(rminame);
//        System.out.println("s = " + s);
    }
}
