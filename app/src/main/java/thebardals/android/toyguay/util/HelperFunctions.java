package thebardals.android.toyguay.util;


public class HelperFunctions {

    public static String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }
}
