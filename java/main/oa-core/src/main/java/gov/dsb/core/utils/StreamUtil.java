package gov.dsb.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-3-20
 * Time: 10:01:43
 * To change this template use File | Settings | File Templates.
 */
public final class StreamUtil {

    /**
     * 私有构造函数 工具类
     */
    private StreamUtil(){
    }
//    private static Category cat = Category.getInstance(StreamUtil.class.getName());

    /**
     * 缺省换成之大小
     */
    public static final int DEF_BUF_SIZE = 4096;


    /**
     * Read a given bytes from the input stream and write them to the output stream
     * @param is     InputStream
     * @param os     OutputStream
     * @param length int
     * @throws java.io.IOException     .
     */

    public static void readStream(InputStream is, OutputStream os, int length) throws IOException {
        readStream(is, os, length, DEF_BUF_SIZE);
    }


    /**
     * Read a given bytes from the input stream and write them to the output stream
     * <p/>
     * NOTE: the initial buffer size is specified.
     * @param is InputStream
     * @param os  OutputStream
     * @param length  int
     * @param bufsize  int
     * @throws java.io.IOException   .
     */

    public static void readStream(InputStream is, OutputStream os, int length, int bufsize) throws IOException {

        byte[] buff = new byte[bufsize];

        for (int remain = length, read = 0; remain > 0; remain -= read){

            if (remain > bufsize) {

                read =  is.read(buff);

            }
            else {

                read =  is.read(buff, 0, remain);

            }

            if (read < 0) {

                throw new IOException("Premature EOF");

            }

            os.write(buff, 0, read);

        }

    }


    /**
     * Reads the given {@link java.io.InputStream} until a newline character is
     * <p/>
     * reached (0x0A) or end of stream.  Note this will work fine for
     * <p/>
     * lines ending in CRLF, but not CR..
     * @param is input stream to read from
     * @return string of all characters read, including the final newline
     *         <p/>
     *         (if not end-of-stream instead)  Returns <code>null</code>
     *         <p/>
     *         if at end-of-stream (i.e. no characters to return)
     * @throws java.io.IOException if it occurs in reading.
     */

    public static String readLine(InputStream is) throws IOException {

        int ch;

        StringBuffer sb = new StringBuffer();

        while ((ch = is.read()) != -1) {

            sb.append((char) ch);

            if (ch == '\n') {
                break;
            }

        }

        if(sb.length() == 0)  {

            return null;
        }

        return sb.toString();

    }


    /**
     * Read the stream into a string.
     * @param in InputStream
     * @return   String
     * @throws java.io.IOException    .
     */

    public static String readStream(InputStream in) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream(DEF_BUF_SIZE);

        byte[] buff = new byte[DEF_BUF_SIZE];

        int read = -1;

        while ((read = in.read(buff)) != -1) {

            baos.write(buff, 0, read);

        }

        return baos.toString();

    }


    /**
     * Read a number of bytes from the given stream into a string.
     * @param is  InputStream
     * @param length   int
     * @return        String
     * @throws java.io.IOException  .
     */

    public static String readStream(InputStream is, int length) throws IOException {

        return readStream(is, length, DEF_BUF_SIZE);

    }


    /**
     * Read a number of bytes from the given stream into a string using an
     * <p/>
     * initial buffer size.
     * @param is   InputStream
     * @param length int
     * @param bufsize int
     * @return    String
     * @throws java.io.IOException    .
     */

    public static String readStream(InputStream is, int length, int bufsize) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream(length);

        readStream(is, baos, length, bufsize);

        return baos.toString();

    }

    /**
     * @param is InputStream
     * @param length  int
     * @param bufsize    int
     * @return      byte[]
     * @throws java.io.IOException    .
     */
    public static byte[] readStreamIntoBytes(InputStream is, int length, int bufsize) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream(length);

        readStream(is, baos, length, bufsize);

        return baos.toByteArray();

    }

    /**
     *
     * @param is   InputStream
     * @param length int
     * @return      byte[]
     * @throws java.io.IOException       .
     */
    public static byte[] readStreamIntoBytes(InputStream is, int length) throws IOException {

        return readStreamIntoBytes(is, length, DEF_BUF_SIZE);

    }

    /**
     * @param is   InputStream
     * @return    byte[]
     * @throws java.io.IOException    .
     */
    public static byte[] readStreamIntoBytes(InputStream is) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream(DEF_BUF_SIZE);

        byte[] buff = new byte[DEF_BUF_SIZE];

        int read = -1;

        while ((read = is.read(buff)) != -1) {

            baos.write(buff, 0, read);

        }

        return baos.toByteArray();

    }


    /**
     * Copy stream until input stream indicated end of stream.
     * @param in  input stream to copy from
     * @param out output stream to copy to
     * @throws java.io.IOException if the stream accesses cause one
     */

    public static void copyStream(InputStream in, OutputStream out) throws IOException {

        byte[] buff = new byte[8192];

        int read = -1;

        while ((read = in.read(buff)) != -1) {

            out.write(buff, 0, read);

        }

    }


/*

    public static void copyStream(InputStream in, OutputStream out, DavProgressItem progItem)

        throws IOException

    {

        // Sanity check

        if (progItem == null)

        {

            copyStream(in, out);

            return;

        }



        // Adjust the reading position if necessary

        skipInStream(in, progItem.getCompletion(), progItem);



        // Do copy

        byte[] buff = new byte[8192];

        int read = -1;

        while(!progItem.isStopped() && (read = in.read(buff)) != -1)

        {

            out.write(buff,0,read);

            progItem.adjCompletion(read);

        }

    }

*/


    /**
     * Skip a number of bytes in the input stream. This method does skipping
     * <p/>
     * repeatingly until the total number of bytes to skip is met.
     * @param in    InputStream
     * @param toSkip  long
     * @throws java.io.IOException       .
     */

    public static void skipInStream(InputStream in, long toSkip) throws IOException {

        while (toSkip > 0) {

            toSkip -= in.skip(toSkip);

        }
    }


    /*

    public static void skipInStream(InputStream in, long toSkip, DavProgressItem progItem)

    throws IOException

    {

        while (toSkip > 0 && !progItem.isStopped())

            toSkip -= in.skip(toSkip);

    }

    */


    /**
     * Copy stream until input stream hits end of stream, or designated number
     * <p/>
     * of bytes have been copied.
     *
     * @param in     input stream to copy from
     * @param out    output stream to copy to
     * @param amount maximum number of bytes to copy
     * @return the number bytes actually copied
     * @throws java.io.IOException if the stream accesses cause one
     */

    public static long copyStream(InputStream in, OutputStream out, long amount) throws IOException {

        byte[] buff = new byte[(int) Math.min(amount, 8192L)];

        int read = 0;

        long copied = 0;

        //(int) Math.min(amount - copied, 8192L);
        //int tocopy = buff.length;

        read = in.read(buff, 0, buff.length);

        while (copied < amount && read != -1) {

            out.write(buff, 0, read);

            copied += read;

        }

        out.flush();

        return copied;

    }


    /*

    public static long copyStream(InputStream in, OutputStream out, long amount,

                                  DavProgressItem progItem)

    throws IOException

    {

        // Sanity check

        if (progItem == null) return copyStream(in, out, amount);



        // Adjust the reading position if necessary

        skipInStream(in, progItem.getCompletion());



        // Do copy

        byte[] buff = new byte[(int) Math.min(amount, 8192L)];

        int read = 0;

        long copied = 0;

        int tocopy = (int) Math.min(amount - copied, 8192L);

        while ( !progItem.isStopped() && copied < amount &&

                (read = in.read(buff, 0, tocopy)) != -1 )

        {

            out.write(buff, 0, read);

            copied += read;

            progItem.adjCompletion(read);

        }

        out.flush();

        return copied;

    }

    */


}
