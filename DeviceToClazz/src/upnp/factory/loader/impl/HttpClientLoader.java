package upnp.factory.loader.impl;

import android.util.Log;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import upnp.cache.FileCache;
import upnp.factory.loader.DocumentLoader;

public class HttpClientLoader implements DocumentLoader {

    private static final String TAG = "HttpClientLoader";
    private static final int MAX_LENGTH = 1024 * 20;
    private static final int CONNECT_TIMEOUT = 1000 * 10;
    private static final Object classLock = HttpClientLoader.class;
    private static HttpClientLoader instance = null;

    public static HttpClientLoader getInstance() {
        synchronized (classLock) {
            if (instance == null) {
                instance = new HttpClientLoader();
            }

            return instance;
        }
    }

    private HttpClientLoader() {
    }

    @Override
    public InputStream getStream(String url) {
        Log.d(TAG, "getStream");

        InputStream is = null;

        do {
            try {
                URL u = new URL(url);

                URLConnection uc = u.openConnection();
                uc.setConnectTimeout(CONNECT_TIMEOUT);

                int length = uc.getContentLength();
                if (length == 0) {
                    Log.d(TAG, String.format("getStream finished, content length: %d", length));
                    break;
                }

                if (length > MAX_LENGTH) {
                    Log.d(TAG, String.format("getStream finished, content length: %d", length));
                    break;
                }

                is = FileCache.getInstance().saveCache(url, (InputStream) uc.getContent());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (EOFException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (false);

        return is;
    }
}