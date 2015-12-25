package upnp.utils;

public class HttpUrlUtil {

    private static String HTTP_PREFIX = "http://";

    private HttpUrlUtil() {
    }

    public static String getUri(String url) {
        if (! url.startsWith(HTTP_PREFIX)) {
            return url;
        }

        int p = HTTP_PREFIX.length();

        String s = url.substring(p);

        int index = s.indexOf('/');

        String uri = s.substring(index);

        return uri;
    }
}