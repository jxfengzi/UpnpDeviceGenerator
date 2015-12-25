
package upnp.typedef.device.invocation;

import android.util.Log;

import upnp.typedef.device.Device;
import upnp.typedef.device.Service;
import upnp.typedef.property.Property;

public class EventInfoCreator {

    private static final String TAG = "ActionInfoCreator";

    public static EventInfo create(Device device, String serviceId) {
        EventInfo info = null;

        do {
            Service service = device.getService(serviceId);
            if (service == null) {
                Log.d(TAG, String.format("Service not found: %s", serviceId));
                break;
            }

            info = EventInfoCreator.create(service);
        } while (false);

        return info;
    }

    public static EventInfo create(Service service) {
        EventInfo info = new EventInfo();
        info.setDiscoveryType(service.getDevice().getDiscoveryType());
        info.setServiceType(service.getType());
        info.setServiceId(service.getServiceId());
        info.setDeviceId(service.getDevice().getDeviceId());

        for (Property p : service.getProperties()) {
            if (p.getDefinition().isSendEvents()) {
                info.getProperties().add(p);
            }
        }

        return info;
    }
}
