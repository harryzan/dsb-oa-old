package gov.dsb.core.utils;


import java.util.Calendar;

public class CryptUtil {

//    private static final int startKey = 956;

//    private static final int multKey = 37269;

//    private static final int addKey = 28820;

    public CryptUtil() {
    }

    private static String encrypt(String inString, int startKey, int multKey, int addKey) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < inString.length(); i++) {
            result.append((char) ((inString.charAt(i) ^ startKey >> 8) & 0xff));
            startKey = (result.charAt(i) + startKey) * multKey + addKey;
        }

        return result.toString();
    }

    private static String decrypt(String inString, int startKey, int multKey, int addKey) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < inString.length(); i++) {
            result.append((char) ((inString.charAt(i) ^ startKey >> 8) & 0xff));
            startKey = (inString.charAt(i) + startKey) * multKey + addKey;
        }

        return result.toString();
    }

    private static String cl_inttostr(int int1, int len) {
        String s = "" + int1;
        StringBuffer s2 = new StringBuffer();
        int length = s.length();
        if (length >= len) {
            return s;
        }
        for (int i = 0; i < len - length; i++) {
            s2.append("0");
        }
        s2.append(int1);

        return s2.toString();
    }

    private static String cl_chartobytestr(String s) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            result.append(cl_inttostr(s.charAt(i), 3));
        }

        return result.toString();
    }

    private static String cl_bytetocharstr(String s) {
        int i = 1;
        StringBuffer result = new StringBuffer();
        if (s.length() % 3 == 0) {
            for (; i < s.length(); i += 3) {
                result.append((char) (Integer.parseInt(s.substring(i - 1, i + 2)) & 0xff));
            }
        }

        return result.toString();
    }

    public static String cl_encrypt(String s) {
        Calendar calendar = Calendar.getInstance();
        int years = calendar.get(1);
        int months = calendar.get(2);
        int days = calendar.get(5);
        int hours = calendar.get(11);
        int mins = calendar.get(12);
        int secs = calendar.get(13);
        int msec = calendar.get(14);
        int cl_StartKey = msec;
        if (cl_StartKey < 256) {
            cl_StartKey += 256;
        }
        int cl_Multkey = ((years - 1900) * 12 + months) * 30 + days + cl_StartKey * 10 + cl_StartKey;
        int cl_AddKey = (23 * hours + mins) * 60 + secs + cl_StartKey * 10 + cl_StartKey;
        String result = cl_chartobytestr(encrypt(cl_inttostr(cl_StartKey, 3), 956, 37269, 28820)) +
                cl_chartobytestr(encrypt(cl_inttostr(cl_Multkey, 5), 956, 37269, 28820)) +
                cl_chartobytestr(encrypt(cl_inttostr(cl_AddKey, 5), 956, 37269, 28820)) +
                cl_chartobytestr(encrypt(s, cl_StartKey, cl_Multkey, cl_AddKey));
        return result;
    }

    public static String cl_decrypt(String s) {
        int cl_StartKey = Integer.parseInt(decrypt(cl_bytetocharstr(s.substring(0, 9)), 956, 37269, 28820));
        int cl_MultKey = Integer.parseInt(decrypt(cl_bytetocharstr(s.substring(9, 24)), 956, 37269, 28820));
        int cl_AddKey = Integer.parseInt(decrypt(cl_bytetocharstr(s.substring(24, 39)), 956, 37269, 28820));
        return decrypt(cl_bytetocharstr(s.substring(39, s.length())), cl_StartKey, cl_MultKey, cl_AddKey);
    }



	/**
	 * 进行数据加密
	 *
	 * @param s
	 * @return
	 */
	public static String encrypt(String s) {
		Calendar calendar = Calendar.getInstance();
		int years = calendar.get(1);
		int months = calendar.get(2);
		int days = calendar.get(5);
		int hours = calendar.get(11);
		int mins = calendar.get(12);
		int secs = calendar.get(13);
		int msec = calendar.get(14);
		int cl_StartKey = msec;
		if (cl_StartKey < 256)
			cl_StartKey += 256;
		int cl_Multkey = ((years - 1900) * 12 + months) * 30 + days
				+ cl_StartKey * 10 + cl_StartKey;
		int cl_AddKey = (23 * hours + mins) * 60 + secs + cl_StartKey * 10
				+ cl_StartKey;
		String result = cl_chartobytestr(encrypt(cl_inttostr(cl_StartKey, 3),
				956, 37269, 28820))
				+ cl_chartobytestr(encrypt(cl_inttostr(cl_Multkey, 5), 956,
						37269, 28820))
				+ cl_chartobytestr(encrypt(cl_inttostr(cl_AddKey, 5), 956,
						37269, 28820))
				+ cl_chartobytestr(encrypt(s, cl_StartKey, cl_Multkey,
						cl_AddKey));
		return result;
	}

	/**
	 * 进行数据解密
	 *
	 * @param s
	 * @return
	 */
	public static String decrypt(String s) {
		int cl_StartKey = Integer.parseInt(decrypt(cl_bytetocharstr(s
				.substring(0, 9)), 956, 37269, 28820));
		int cl_MultKey = Integer.parseInt(decrypt(cl_bytetocharstr(s.substring(
				9, 24)), 956, 37269, 28820));
		int cl_AddKey = Integer.parseInt(decrypt(cl_bytetocharstr(s.substring(
				24, 39)), 956, 37269, 28820));
		String result = decrypt(cl_bytetocharstr(s.substring(39, s.length())),
				cl_StartKey, cl_MultKey, cl_AddKey);
		return result;
	}    

    public static void main(String args[])
            throws Exception {
        String passwd = "jyadmin";
//        for (int i = 0; i < 10; i++) {
//            String s = "juyee" + i;
//            String ens = cl_encrypt(s);
//            String des = cl_decrypt(ens);
//            System.out.println("original string:" + s);
//            System.out.println("encrypt string:" + ens);
//            System.out.println("decrypt string:" + des);
//            if(s.equals(des))
//                System.out.println("ok!");
//            else
//                System.out.println("fail!");
//            Thread.sleep(100L);
//        }
    }
}
