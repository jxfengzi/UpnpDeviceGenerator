package upnp.cache;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import upnp.utils.Md5Util;

public class FileCache {

    private static final String TAG = "FileCache";
    private static FileCache instance = null;
    private static Object classLock = FileCache.class;
    private String cacheDir;

    public static FileCache getInstance() {
        synchronized (classLock) {
            if (instance == null) {
                instance = new FileCache();
            }

            return instance;
        }
    }

    private FileCache() {
    }

    public void setCacheDir(String cacheDir) {
        this.cacheDir = cacheDir;
    }

    public InputStream getCache(String url) {
        InputStream in = null;

        do {
            if (url == null) {
                break;
            }

            if (cacheDir == null) {
                break;
            }

            String name = Md5Util.createMd5(url);
            String cachedFile = cacheDir + "/" + name;

            try {
                in = new FileInputStream(cachedFile);
            } catch (FileNotFoundException e) {
                break;
            }
        } while (false);

        return in;
    }

    public InputStream saveCache(String url, InputStream ins) throws IOException {
        InputStream stream = null;

        do {
            if (url == null) {
                break;
            }

            if (ins == null) {
                break;
            }

            if (cacheDir == null) {
                Log.e(TAG, "cacheDir is null");
                stream = ins;
                break;
            }

            String name = Md5Util.createMd5(url);
            String cacheFile = cacheDir + "/" + name;

            File file = new File(cacheFile);

            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();

            stream = new FileInputStream(cacheFile);
        } while (false);

        return stream;
    }
}