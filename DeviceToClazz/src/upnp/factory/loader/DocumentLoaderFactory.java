package upnp.factory.loader;

import upnp.factory.loader.impl.CacheLoader;
import upnp.factory.loader.impl.HttpClientLoader;

public class DocumentLoaderFactory {

    private DocumentLoaderFactory() {
    }

    public static DocumentLoader create(DocumentLoaderType type) {
        DocumentLoader loader = null;

        switch (type) {
            case CACHE:
                loader = CacheLoader.getInstance();
                break;

            case HTTP:
                loader = HttpClientLoader.getInstance();
                break;
        }

        return loader;
    }
}