
package test;

import java.io.IOException;

import upnp.codegen.DeviceGenerator;
import upnp.codegen.impl.DeviceControlGeneratorImpl;
import upnp.codegen.impl.DeviceHostGeneratorImpl;

public class DeviceToClazz {

    public static void main(String[] args) {

        do {
            if (args.length < 1) {
                System.out.println(String.format("%s url", DeviceToClazz.class.getSimpleName()));
                break;
            }

            String url = args[0];
            String folder = System.getProperty("user.dir");

            System.out.println(String.format("Parse url: %s", url));

            DeviceGenerator ctrlGennerator = new DeviceControlGeneratorImpl();
            DeviceGenerator hostGennerator = new DeviceHostGeneratorImpl();

            try {
                ctrlGennerator.generate(folder + "/device/control", url);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            System.out.println(String.format("Device for Control create ok"));

            try {
                hostGennerator.generate(folder + "/device/host", url);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            System.out.println(String.format("Device for Host create ok"));
        } while (false);

        return;
    }
}