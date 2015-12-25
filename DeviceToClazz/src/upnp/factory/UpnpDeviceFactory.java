package upnp.factory;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import upnp.typedef.device.Device;
import upnp.typedef.device.DiscoveryType;
import upnp.typedef.device.Service;
import upnp.typedef.device.helper.DeviceHelper;
import upnp.typedef.device.helper.ServiceHelper;
import upnp.typedef.exception.InvalidDataTypeException;

public class UpnpDeviceFactory {

    private static final String TAG = "UpnpDeviceFactory";

    private UpnpDeviceFactory() {
    }

    public static Device create(String location) {
        Log.d(TAG, "create");

        Device device = null;

        do {
            InputStream ddd = DocumentManager.getDocument(location);
            if (ddd == null) {
                Log.d(TAG, "getStream failed");
                break;
            }

            do {
                device = new Device();
                device.setDiscoveryType(DiscoveryType.UPNP);
                device.setLocation(location);

                setHost(device, location);

                if (! DeviceHelper.parse(device, ddd)) {
                    Log.d(TAG, "parseDdd failed");
                    device = null;
                    break;
                }

                if (! device.getDeviceType().getDomain().equals("schemas-upnp-org")) {
                    Log.d(TAG, "deviceType domain is: "  + device.getDeviceType().getDomain());
                    break;
                }

                for (Service service : device.getServices().values()) {
                    if (service.getScpdUrl() == null) {
                        Log.d(TAG, "SCPD url is null: " + service.getServiceId());
                        device = null;
                        break;
                    }

                    String url = service.getDevice().getUrlBase() + service.getScpdUrl();

                    InputStream scpd = DocumentManager.getDocument(url);
                    if (scpd == null) {
                        Log.d(TAG, "get failed");
                        break;
                    }

                    try {
                        if (! ServiceHelper.parse(service, scpd)) {
                            Log.d(TAG, "ServiceLoader parse failed: " + service.getScpdUrl());
                            device = null;
                        }
                    } catch (InvalidDataTypeException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            scpd.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } while (false);

            try {
                ddd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (false);

        Log.d(TAG, String.format("create %s", (device != null) ? "ok" : "failed"));

        return device;
    }

    private static void setHost(Device device, String location) {
        if (location.startsWith("http://")) {
            try {
                URL u = new URL(location);
                device.setHostIp(u.getHost());
                device.setHostPort(u.getPort());
                device.setUrlBase(String.format("http://%s:%d", u.getHost(), u.getPort()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else {
            File f = new File(location);
            String folder = f.getParent();
            Log.d(TAG, "folder: " + folder);
            device.setUrlBase(folder);
        }
    }
}