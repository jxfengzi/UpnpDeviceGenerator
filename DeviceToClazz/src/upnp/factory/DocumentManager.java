package upnp.factory;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import upnp.factory.loader.DocumentLoader;
import upnp.factory.loader.DocumentLoaderFactory;
import upnp.factory.loader.DocumentLoaderType;

public class DocumentManager {

    private static final String TAG = "DocumentManager";
    private static final int RETRY_TIMES = 3;

    private DocumentManager() {
    }

    public static InputStream getDocument(String url) {
        Log.e(TAG, "get: " + url);

        InputStream is = null;

        do {
            DocumentLoader loader;

            if (url.startsWith("http://")) {
                loader = DocumentLoaderFactory.create(DocumentLoaderType.CACHE);
                is = loader.getStream(url);
                if (is != null) {
                    break;
                }

                loader = DocumentLoaderFactory.create(DocumentLoaderType.HTTP);
                for (int i = 0; i < RETRY_TIMES; ++i) {
                    is = loader.getStream(url);
                    if (is != null) {
                        break;
                    }
                }

                if (is != null) {
                    break;
                }
            }
            else {
                try {
                    is = new FileInputStream(url);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } while (false);

        Log.e(TAG, "get finished");

        return is;
    }
}