package gov.dsb.core.utils;


/**
 This class provide a place holder for all default "null" values

 <P>
 CONFIGURATION PARAMETERS:

 <DL>

 <DT> es.NullString
 <DD>

 <P>
 The String value which represents a null value.

 <P>
 The default is "NULL_STRING".

 <DT> es.NullLong
 <DD>

 <P>
 The long value which represents a null value.

 <P>
 The default is -9223372036854775808.

 <DT> es.NullInt
 <DD>

 <P>
 The int value which represents a null value.

 <P>
 The default is -2147483648.

 </DL>

 @ default null short is dangous
 @ default null char is dangous

 */

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public abstract class Nulls {

    //----- Constants --------------------------------------------------------------

    // "null" values

    public final static Object DEF_NULL_OBJECT = null;//wy add

    public final static String DEF_NULL_STRING = null;

    public final static long DEF_NULL_LONG = Long.MIN_VALUE;

    public final static int DEF_NULL_INT = Integer.MIN_VALUE;

    public final static short DEF_NULL_SHORT = Short.MIN_VALUE;

    public final static char DEF_NULL_CHAR = Character.MAX_VALUE; // This will be \ufffe

    public final static byte DEF_NULL_BYTE = Byte.MAX_VALUE;      // Allow the settings of 0

    public final static boolean DEF_NULL_BOOLEAN = false;

    public final static float DEF_NULL_FLOAT = Float.MIN_VALUE;

    public final static double DEF_NULL_DOUBLE = Double.MIN_VALUE;

    public final static Date DEF_NULL_DATE = null;//new Date(Long.MIN_VALUE);

    public final static Timestamp DEF_NULL_TIMESTAMP = null;//new Timestamp(Long.MIN_VALUE);

    public final static Time DEF_NULL_TIME = null;//new Timestamp(Long.MIN_VALUE);

    //----- Configuration ----------------------------------------------------------


    /**
     * Return the value of the configuration parameter which
     * determines the Object value which represents a null value.
     */
    public static synchronized Object getNullObject() {
        return DEF_NULL_OBJECT;
    }

    /**
     * Return the value of the configuration parameter which
     * determines the String value which represents a null value.
     */
    public static String getNullString() {
        return DEF_NULL_STRING;
    }


    /**
     * Return the value of the configuration parameter which
     * determines the long value which represents a null value.
     */
    public static long getNullLong() {
        return DEF_NULL_LONG;
    }


    /**
     * Return the value of the configuration parameter which
     * determines the int value which represents a null value.
     */
    public static int getNullInt() {
        return DEF_NULL_INT;
    }


    /**
     * Return the value of the configuration parameter which
     * determines the int value which represents a null value.
     */

    public static byte getNullByte() {
        return DEF_NULL_BYTE;
    }


    /**
     * Return the char value which represents a null value.
     */

    public static char getNullChar() {
        return DEF_NULL_CHAR;
    }


    /**
     * Return the short value which represents a null value.
     */

    public static short getNullShort() {
        return DEF_NULL_SHORT;
    }


    /**
     * Return the boolean value which represents a null value.
     */

    public static boolean getNullBoolean() {
        return DEF_NULL_BOOLEAN;
    }


    /**
     * Return the float value which represents a null value.
     */

    public static float getNullFloat() {
        return DEF_NULL_FLOAT;
    }


    /**
     * Return the double value which represents a null value.
     */

    public static double getNullDouble() {
        return DEF_NULL_DOUBLE;
    }

    /**
     * Return the Date value which represents a null value.
     */
    public static Date getNullDate() {
        return DEF_NULL_DATE;
    }

    /**
     * Return the timestamp value which represents a null value.
     */
    public static Timestamp getNullTimestamp() {
        return DEF_NULL_TIMESTAMP;
    }

    /**
     * Return the times value which represents a null value.
     */
    public static Time getNullTime() {
        return DEF_NULL_TIME;
    }

//----- Operation --------------------------------------------------------------

    /**
     * is null for the given object variable
     *
     * @param o
     * @return
     */
    public static boolean isNull(Object o) {
        return (null == o);
    }

    /**
     * is null for the given string variable
     *
     * @param s
     * @return
     */
    public static boolean isNull(String s) {
        return (getNullString() == s);
        //return (s.equals(getNullString()));
    }

    /**
     * is null for the given long variable
     *
     * @param l
     * @return
     */
    public static boolean isNull(long l) {
        return (l == getNullLong());
    }

    /**
     * is null for the given int variable
     *
     * @param i
     * @return
     */
    public static boolean isNull(int i) {
        return (i == getNullInt());
    }

    /**
     * is null for the given short variable
     *
     * @param s
     * @return
     */
    public static boolean isNull(short s) {
        return (s == getNullShort());
    }

    /**
     * is null for the given char variable
     *
     * @param c
     * @return
     */
    public static boolean isNull(char c) {
        return (c == getNullChar());
    }

    /**
     * is null for the given float variable
     *
     * @param f
     * @return
     */
    public static boolean isNull(float f) {
        return (f == getNullFloat());
    }

    /**
     * is null for the given double variable
     *
     * @param d
     * @return
     */
    public static boolean isNull(double d) {
        return (d == getNullDouble());
    }

    /**
     * is null for the given boolean variable
     * always return false
     *
     * @param b
     * @return
     */
    public static boolean isNull(boolean b) {
        return false;
        //return (b = getNullBoolean());//wy modify
    }

    /**
     * is null for the given Date variable
     *
     * @param b
     * @return
     */
    public static boolean isNull(Date b) {
        if (null == getNullDate()) {
            return (null == b);
        }
        else {
            return getNullDate().equals(b);
        }
    }

    /**
     * is null for the given Timestamp variable
     *
     * @param b
     * @return
     */
    public static boolean isNull(Timestamp b) {
        if (null == getNullTimestamp()) {
            return (null == b);
        }
        else {
            return getNullTimestamp().equals(b);
        }
    }

    /**
     * is null for the given Time variable
     *
     * @param b
     * @return
     */
    public static boolean isNull(Time b) {
        if (null == getNullTime()) {
            return (null == b);
        }
        else {
            return getNullTime().equals(b);
        }
    }

    //getText()
//    public final static char DEF_NULL_CHAR = Character.MAX_VALUE; // This will be \ufffe
//    public final static boolean DEF_NULL_BOOLEAN = false;

    /**
     * getText deal with Null value in Nulls,
     * if value is Null value in Nulls,then return ""
     * else return the value in string
     * for DAO produced Objects and RowSet Objects
     *
     * @param v
     * @return
     */
    public static String getText(boolean v) {
        return isNull(v) ? "" : String.valueOf(v);
    }

    /**
     * getText deal with Null value in Nulls,
     * if value is Null value in Nulls,then return ""
     * else return the value in string
     * for DAO produced Objects and RowSet Objects
     *
     * @param v
     * @return
     */
    public static String getText(char v) {
        return isNull(v) ? "" : String.valueOf(v);
    }

    /**
     * getText deal with Null value in Nulls,
     * if value is Null value in Nulls,then return ""
     * else return the value in string
     * for DAO produced Objects and RowSet Objects
     *
     * @param v
     * @return
     */
    public static String getText(Date v) {
        return isNull(v) ? "" : v.toString();
    }

    /**
     * getText deal with Null value in Nulls,
     * if value is Null value in Nulls,then return ""
     * else return the value in string
     * for DAO produced Objects and RowSet Objects
     *
     * @param v
     * @return
     */
    public static String getText(Time v) {
        return isNull(v) ? "" : v.toString();
    }

    /**
     * getText deal with Null value in Nulls,
     * if value is Null value in Nulls,then return ""
     * else return the value in string
     * for DAO produced Objects and RowSet Objects
     *
     * @param v
     * @return
     */
    public static String getText(Timestamp v) {
        return isNull(v) ? "" : v.toString();
    }

    /**
     * getText deal with Null value in Nulls,
     * if value is Null value in Nulls,then return ""
     * else return the value in string
     * for DAO produced Objects and RowSet Objects
     *
     * @param v
     * @return
     */
    public static String getText(Object v) {
        return isNull(v) ? "" : v.toString();
    }

    /**
     * getText deal with Null value in Nulls,
     * if value is Null value in Nulls,then return ""
     * else return the value in string
     * for DAO produced Objects and RowSet Objects
     *
     * @param v
     * @return
     */
    public static String getText(String v) {
        return isNull(v) ? "" : v;
    }

    /**
     * getText deal with Null value in Nulls,
     * if value is Null value in Nulls,then return ""
     * else return the value in string
     * for DAO produced Objects and RowSet Objects
     *
     * @param v
     * @return
     */
    public static String getText(int v) {
        return isNull(v) ? "" : Integer.toString(v);
    }

    /**
     * getText deal with Null value in Nulls,
     * if value is Null value in Nulls,then return ""
     * else return the value in string
     * for DAO produced Objects and RowSet Objects
     *
     * @param v
     * @return
     */
    public static String getText(long v) {
        return isNull(v) ? "" : Long.toString(v);
    }

    /**
     * getText deal with Null value in Nulls,
     * if value is Null value in Nulls,then return ""
     * else return the value in string
     * for DAO produced Objects and RowSet Objects
     *
     * @param v
     * @return
     */
    public static String getText(short v) {
        return isNull(v) ? "" : Short.toString(v);
    }

    /**
     * getText deal with Null value in Nulls,
     * if value is Null value in Nulls,then return ""
     * else return the value in string
     *
     * @param v
     * @return
     */
    public static String getText(float v) {
        return isNull(v) ? "" : Float.toString(v);
    }

    /**
     * getText deal with Null value in Nulls,
     * if value is Null value in Nulls,then return ""
     * else return the value in string
     * for DAO produced Objects and RowSet Objects
     *
     * @param v
     * @return
     */
    public static String getText(double v) {
        return isNull(v) ? "" : Double.toString(v);
    }

    /**
     * is empty
     * if the value is null or equals "" then return true
     *
     * @param v
     * @return
     */
    private static boolean isEmpty(String v) {
        return ((v == null) || (v.equals("")));
    }

    /**
     * convert null or "" to Null value in Nulls
     * if the value is empty then return Null value in Nulls
     * else return the value
     *
     * @param v
     * @return
     */
    public static String getString(String v) {
        return (isEmpty(v)) ? getNullString() : v;
    }

    /**
     * convert null or "" to Null value in Nulls
     * if the value is empty then return Null value in Nulls
     * else return the value
     *
     * @param v
     * @return
     */
    public static boolean getBoolean(String v) {
        return (isEmpty(v)) ? getNullBoolean() : Boolean.getBoolean(v);
    }

    /**
     * convert null or "" to Null value in Nulls
     * if the value is empty then return Null value in Nulls
     * else return the value
     *
     * @param v
     * @return
     */
    public static byte getByte(String v) {
        return (isEmpty(v)) ? getNullByte() : Byte.parseByte(v);
    }

    /**
     * convert null or "" to Null value in Nulls
     * if the value is empty then return Null value in Nulls
     * else return the value
     *
     * @param v
     * @return
     */
    public static char getChar(String v) {
        return (isEmpty(v)) ? getNullChar() : v.charAt(0);
    }

    /**
     * convert null or "" to Null value in Nulls
     * if the value is empty then return Null value in Nulls
     * else return the value
     *
     * @param v
     * @return
     */
    public static Date getDate(String v) {
        return (isEmpty(v)) ? getNullDate() : Date.valueOf(v);
    }

    /**
     * convert null or "" to Null value in Nulls
     * if the value is empty then return Null value in Nulls
     * else return the value
     *
     * @param v
     * @return
     */
    public static double getDouble(String v) {
        return (isEmpty(v)) ? getNullDouble() : Double.parseDouble(v);
    }

    /**
     * convert null or "" to Null value in Nulls
     * if the value is empty then return Null value in Nulls
     * else return the value
     *
     * @param v
     * @return
     */
    public static float getFloat(String v) {
        return (isEmpty(v)) ? getNullFloat() : Float.parseFloat(v);
    }

    /**
     * convert null or "" to Null value in Nulls
     * if the value is empty then return Null value in Nulls
     * else return the value
     *
     * @param v
     * @return
     */
    public static int getInt(String v) {
        return (isEmpty(v)) ? getNullInt() : Integer.parseInt(v);
    }

    /**
     * convert null or "" to Null value in Nulls
     * if the value is empty then return Null value in Nulls
     * else return the value
     *
     * @param v
     * @return
     */
    public static long getLong(String v) {
        return (isEmpty(v)) ? getNullLong() : Long.parseLong(v);
    }

    /**
     * convert null or "" to Null value in Nulls
     * if the value is empty then return Null value in Nulls
     * else return the value
     *
     * @param v
     * @return
     */
    public static Object getObject(String v) {
        return (isEmpty(v)) ? getNullObject() : v;
    }

    /**
     * convert null or "" to Null value in Nulls
     * if the value is empty then return Null value in Nulls
     * else return the value
     *
     * @param v
     * @return
     */
    public static short getShort(String v) {
        return (isEmpty(v)) ? getNullShort() : Short.parseShort(v);
    }

    /**
     * convert null or "" to Null value in Nulls
     * if the value is empty then return Null value in Nulls
     * else return the value
     *
     * @param v
     * @return
     */
    public static Time getTime(String v) {
        return (isEmpty(v)) ? getNullTime() : Time.valueOf(v);
    }

    /**
     * convert null or "" to Null value in Nulls
     * if the value is empty then return Null value in Nulls
     * else return the value
     *
     * @param v
     * @return
     */
    public static Timestamp getTimestamp(String v) {
        return (isEmpty(v)) ? getNullTimestamp() : Timestamp.valueOf(v);
    }

    public static void main(final String[] args) {
        System.out.println("Null Values:");
        System.out.println("    getNullString()  = " + getNullString());
        System.out.println("    getNullLong()    = " + getNullLong());
        System.out.println("    getNullInt()     = " + getNullInt());
        System.out.println("    DEF_NULL_SHORT   = " + getNullShort());
        System.out.println("    DEF_NULL_CHAR    = " + getNullChar());
        System.out.println("    DEF_NULL_BYTE    = " + getNullByte());
        System.out.println("    DEF_NULL_BOOLEAN = " + getNullBoolean());
        System.out.println("    DEF_NULL_FLOAT   = " + getNullFloat());
        System.out.println("    DEF_NULL_DOUBLE  = " + getNullDouble());
        System.out.println("    DEF_NULL_DATE  = " + getNullDate());
        System.out.println("    DEF_NULL_TIMESTAMP  = " + getNullTimestamp());
        System.out.println("    DEF_NULL_TIME  = " + getNullTime());
        System.out.println("    DEF_NULL_OBJECT  = " + getNullObject());
        System.out.flush();
        System.out.println("    IS NULL  = " + isNull(false));
        System.out.flush();
        System.out.println("    abc  = " + getText("abc"));
        System.out.println("    _NULL_STRING_  = " + getText("_NULL_STRING_"));
        System.out.flush();
    }
}
