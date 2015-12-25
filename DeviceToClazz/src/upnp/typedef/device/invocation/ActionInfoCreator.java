
package upnp.typedef.device.invocation;

import android.util.Log;

import upnp.typedef.device.Action;
import upnp.typedef.device.Argument;
import upnp.typedef.device.Device;
import upnp.typedef.device.Service;
import upnp.typedef.property.Property;

public class ActionInfoCreator {

    private static final String TAG = "ActionInfoCreator";

    public static ActionInfo create(Service service, String actionName) {
        ActionInfo info = null;

        do {
            Action action = service.getActions().get(actionName);
            if (action == null) {
                Log.d(TAG, String.format("Action not found: %s", actionName));
                break;
            }

            info = ActionInfoCreator.create(action);
        } while (false);

        return info;
    }

    public static ActionInfo create(Device device, String serviceId, String actionName) {
        ActionInfo info = null;

        do {
            Service service = device.getService(serviceId);
            if (service == null) {
                Log.d(TAG, String.format("Service not found: %s", serviceId));
                break;
            }

            info = ActionInfoCreator.create(service, actionName);
        } while (false);

        return info;
    }

    public static ActionInfo create(Action action) {
        ActionInfo info = null;

        do {
            if (action == null) {
                Log.d(TAG, "action is null");
                break;
            }

            Service service = action.getService();
            Device device = service.getDevice();

            info = new ActionInfo();
            info.setAction(action);
            info.setDiscoveryType(device.getDiscoveryType());
            info.setServiceType(service.getType());

            info.setHostIp(device.getHostIp());
            info.setHostPort(device.getHostPort());
            info.setControlUrl(service.getControlUrl());
            info.setDeviceId(device.getDeviceId());

            for (Argument arg : action.getArguments()) {
                Property p = service.getProperty(arg.getRelatedProperty());
                info.getProperties().put(arg.getRelatedProperty(), p);
            }
        } while (false);

        return info;
    }
}
