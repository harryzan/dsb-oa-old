package gov.dsb.core.utils;

import org.apache.commons.lang.StringUtils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * UserSession: Administrator
 * Date: 2007-10-18
 * Time: 10:07:02
 * To change this template use File | Settings | File Templates.
 */
public class StringHelp extends StringUtils {

    public static final boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static final Long getIdElement(String s) {
        String ret = getElementValue(s, "id");
        if (ret == "") {
            return null;
        }
        else {
            return Long.valueOf(ret);
        }
    }

    public static String getElementValue(String doc, String tag) {
        if (doc == null) {
            return "";
        }
        String startTag = "<" + tag + ">";
        String endTag = "</" + tag + ">";
        int pStart = doc.indexOf(startTag);
        int pEnd = doc.indexOf(endTag);
        String ret = "";
        //	alert(pStart);
        //	alert(pEnd);
        if (pStart >= 0 && pEnd > pStart) {
            ret = doc.substring(pStart + startTag.length(), pEnd);

        }
        return ret;
    }

    /**
     * shorten String value to indicated length and endwith indicated suffix
     *
     * @param str    input String
     * @param length indicated length
     * @param suffix indicated suffix
     * @return result str
     */
    public static String subString(String str, int length, String suffix) {
        String result = "";

        byte[] strBytes = str.getBytes();
        if (strBytes.length < length) {
            return str;
        }
        if (!isEmpty(suffix)) {
            length = length - suffix.getBytes().length;
        }

        boolean notASCII = false;
        int lastASCIIIndex = 0;
        byte[] newBytes = new byte[strBytes.length];
        int i = 0;
        for (; i < length; i++) {
            int c = strBytes[i];
            if ((c > 128 || c < 0)) {
                if (i == length - 1) {
                    if (!notASCII) {
                        break;
                    }
                    else {
                        newBytes[i] = (byte) c;
                        int lastNotASCIILength = length - lastASCIIIndex - 1;
                        int lastNotASCIICharCount = lastNotASCIILength / 3;
                        int remain = 3 - (lastNotASCIILength - lastNotASCIICharCount * 3);
                        if (remain > 0) {
                            i++;
                            for (; i < length + remain; i++) {
                                c = strBytes[i];
                                newBytes[i] = (byte) c;
                            }
                        }
                    }
                }
                else {
                    newBytes[i] = (byte) c;
                    notASCII = true;
                }
            }
            else {
                newBytes[i] = (byte) c;
                notASCII = false;
                lastASCIIIndex = i;
            }
        }
        result = new String(newBytes, 0, i) + suffix;

        return result;
    }

    /**
     * shorten input string value to indicated length and endwith default suffix - "..."
     *
     * @param str    input str
     * @param length indicated length
     * @return result string endwith "..."
     */
    public static String subString(String str, int length) {
        return subString(str, length, "...");
    }

    public static Date str2Date(String s) {
        if (isEmpty(s)) {
            return null;
        }

        try {
            DateFormat df = DateFormat.getDateInstance();
            df.setLenient(false);
            java.util.Date date = df.parse(s);

            if (null == date) {
                throw new Exception(s + " is not a validate Date String");
            }

            return new Date(date.getTime());
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * excape string with &,<,>",\
     * @param xmlStr input xml str
     * @return result xml str
     */
    public static String escapeXML(String xmlStr)
    {
        //reserve the space for replace
        StringBuffer sb = new StringBuffer(xmlStr.length() * 5 / 3);

        int len = xmlStr.length();

        char c;
        for (int i = 0; i < len; i++)
        {
            c = xmlStr.charAt(i);
            switch (c)
            {
                case '&':
                    sb.append("&amp;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '"':
                    sb.append("&quot;");
                    break;
                case '\'':
                    sb.append("&apos;");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param str
     * @param s
     * @return
     */
    public static String format(String str, String... s)
    {
        return new MessageFormat(str).format(s);
    }    
}
