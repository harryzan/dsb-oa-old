package gov.dsb.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2007-11-16
 * Time: 12:25:11
 * To change this template use File | Settings | File Templates.
 */
public class FileHelp {

    public static boolean exsist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static String getFileExt(String fileName) {

        String value = "";
        int start = 0;
        int end = 0;
        if (fileName == null) {
            return null;
        }
        if (fileName.lastIndexOf('.') > 0) {
            start = fileName.lastIndexOf('.') + 1;
            end = fileName.length();
            value = fileName.substring(start, end);
            return value;
        }
        else {
            return "";
        }
    }


    public static String getFileWithoutExt(String fileName) {
        String value = "";
        int start;
        int end;
        if (fileName == null) {
            return null;
        }

        if (fileName.lastIndexOf('.') > 0) {
            end = fileName.lastIndexOf('.');
        }
        else {
            end = fileName.length();
        }
        start = 0;
        value = fileName.substring(start, end);
        return value;
    }

    public static boolean mkDirs(String dirName) {
        return exsist(dirName) || new File(dirName).mkdirs();
    }

    public static boolean copyFile(String sourceFileName, String destFileName) throws Exception {
        File sourceFile = new File(sourceFileName);
        File destFile = new File(destFileName);

        FileInputStream fileInputStream = new FileInputStream(sourceFile);
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);

        int in = 0;
        while ((in = fileInputStream.read()) != -1) {
            fileOutputStream.write(in);
        }

        fileOutputStream.close();
        fileInputStream.close();

        return sourceFile.length() == destFile.length();
    }

    public static boolean moveFile(String sourceFileName, String destFileName) throws Exception {
        boolean ret = copyFile(sourceFileName, destFileName);
        if (ret) {
            File sourceFile = new File(sourceFileName);
            sourceFile.deleteOnExit();
        }

        return ret;
    }

    public static void main(String[] args) throws Exception {
        moveFile("e:/Crypt.class", "d:/Crypt.class");
    }
}
