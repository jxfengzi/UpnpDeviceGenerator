package upnp.factory.loader;

import java.io.InputStream;

public interface DocumentLoader {

    InputStream getStream(String url);
}