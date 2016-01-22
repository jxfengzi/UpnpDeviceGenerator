/* Automatic generated by DeviceToClazz */

package upnps.api.host.device.avserver;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import upnp.typedef.device.urn.DeviceType;
import upnp.typedef.error.UpnpError;
import upnp.typedef.device.Device;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.exception.UpnpException;

import upnps.manager.UpnpManager;
import upnps.manager.handler.MyActionHandler;
import upnps.manager.handler.MyCompletionHandler;
import upnps.manager.host.config.DeviceConfig;
import upnps.manager.host.ServiceHandler;

public class AVServer implements MyActionHandler {

    private static final String TAG = "AVServer";

    /**
     * deviceType
     */
    public static final DeviceType DEVICE_TYPE = new DeviceType("AVServer", "1");

    /**
     * serviceId
     */
    public static final String ID_ScreenCast = "urn:upnp-org:serviceId:ScreenCast";
    public static final String ID_SessionManager = "urn:upnp-org:serviceId:SessionManager";

    /**
     * device & service handler;
     */

    private Device _device;
    private Map<String, ServiceHandler> _services = new HashMap<String, ServiceHandler>();

    public AVServer(Context context, DeviceConfig config) throws UpnpException {
        _device = config.build(context);
        _services.put(ID_ScreenCast, new ScreenCast(_device));
        _services.put(ID_SessionManager, new SessionManager(_device));
    }

    public String getDeviceId() {
        return _device.getDeviceId();
    }

    public void start(MyCompletionHandler handler) throws UpnpException {
        UpnpManager.getHost().register(_device, handler, this);
    }

    public void stop(MyCompletionHandler handler) throws UpnpException {
        UpnpManager.getHost().unregister(_device, handler);
    }

    @Override
    public UpnpError onAction(ActionInfo info) {
        ServiceHandler handler = _services.get(info.getServiceId());
        if (handler == null) {
            Log.e(TAG, "service not found: " + info.getServiceId());
            return UpnpError.UPNP_INTERNAL_ERROR;
        }

        return handler.onAction(info);
    }

    public ScreenCast getScreenCast() {
        return (ScreenCast)_services.get(ID_ScreenCast);
    }

    public SessionManager getSessionManager() {
        return (SessionManager)_services.get(ID_SessionManager);
    }

}
