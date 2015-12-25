package upnp.factory.loader.impl;

import android.util.Log;

import java.io.InputStream;

import upnp.factory.loader.DocumentLoader;
import upnp.cache.FileCache;

public class CacheLoader implements DocumentLoader {

    private static final String TAG = "CacheLoader";
    private static final Object classLock = CacheLoader.class;
    private static CacheLoader instance = null;

    public static CacheLoader getInstance() {
        synchronized (classLock) {
            if (instance == null) {
                instance = new CacheLoader();
            }

            return instance;
        }
    }

    private CacheLoader() {
    }

    @Override
    public InputStream getStream(String url) {
        Log.d(TAG, "getStream");

        return FileCache.getInstance().getCache(url);
    }
}
