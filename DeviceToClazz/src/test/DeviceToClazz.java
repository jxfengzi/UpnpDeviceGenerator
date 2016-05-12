
package test;

import java.io.IOException;

import upnp.codegen.DeviceGenerator;
import upnp.codegen.impl.DeviceControlCGeneratorImpl;
import upnp.codegen.impl.DeviceControlJavaGeneratorImpl;
import upnp.codegen.impl.DeviceHostCGeneratorImpl;
import upnp.codegen.impl.DeviceHostJavaGeneratorImpl;

public class DeviceToClazz {

    public static void main(String[] args) {

        do {
            if (args.length < 1) {
                System.out.println(String.format("%s url", DeviceToClazz.class.getSimpleName()));
                break;
            }

            String url = args[0];
            String type = args[1];
            String folder = System.getProperty("user.dir");

            System.out.println(String.format("Parse url: %s", url));

            DeviceGenerator ctrlGennerator;
            DeviceGenerator hostGennerator;
            if (type == null || type.equals("java") || type.equals("Java") || type.equals("JAVA")) {
                ctrlGennerator = new DeviceControlJavaGeneratorImpl();
                hostGennerator = new DeviceHostJavaGeneratorImpl();
            } else if (type.equals("c") || type.equals("C")) {
                ctrlGennerator = new DeviceControlCGeneratorImpl();
                hostGennerator = new DeviceHostCGeneratorImpl();
            } else {
                System.out.println(String.format("Unknown transform language type: %s, the generator only support JAVA or C", url));
                break;
            }

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