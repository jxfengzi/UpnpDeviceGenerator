package upnp.codegen;

import java.io.IOException;

public interface DeviceGenerator {

    boolean generate(String folder, String url) throws IOException;
}