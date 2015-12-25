package upnp.utils;

public class Md5Util {

    public static String createMd5(String text) {
        Md5 md5 = new Md5(text);
        return md5.getMd5();
    }
}
