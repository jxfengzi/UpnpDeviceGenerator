/* Automatic generated by DeviceToClazz */

package upnps.api.host.device.avplayer;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import upnp.typedef.device.urn.DeviceType;
import upnp.typedef.device.urn.ServiceType;
import upnp.typedef.error.UpnpError;
import upnp.typedef.device.Device;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.exception.UpnpException;

import upnps.manager.UpnpManager;
import upnps.manager.handler.MyActionHandler;
import upnps.manager.handler.MyCompletionHandler;
import upnps.manager.host.config.DeviceConfig;
import upnps.manager.host.ServiceStub;

public class AVPlayer implements MyActionHandler {

    private static final String TAG = "AVPlayer";

    /**
     * deviceType & serviceType
     */
    public static final DeviceType DEVICE_TYPE = new DeviceType("AVPlayer", "1");
    public static final ServiceType SERVICE_AVTransport =  new ServiceType("AVTransport", "1");
    public static final ServiceType SERVICE_RenderingControl =  new ServiceType("RenderingControl", "1");
    public static final ServiceType SERVICE_SessionManager =  new ServiceType("SessionManager", "1");

    /**
     * serviceId
     */
    private static final String ID_AVTransport = "urn:upnp-org:serviceId:AVTransport";
    private static final String ID_RenderingControl = "urn:upnp-org:serviceId:RenderingControl";
    private static final String ID_SessionManager = "urn:upnp-org:serviceId:SessionManager";

    /**
     * device & service handler;
     */

    private Device _device;
    private Map<String, ServiceStub> _services = new HashMap<String, ServiceStub>();

    public AVPlayer(Context context, DeviceConfig config) throws UpnpException {
        _device = config.build(context);
        _services.put(ID_AVTransport, new AVTransport(_device.getService(ID_AVTransport)));
        _services.put(ID_RenderingControl, new RenderingControl(_device.getService(ID_RenderingControl)));
        _services.put(ID_SessionManager, new SessionManager(_device.getService(ID_SessionManager)));
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
        ServiceStub handler = _services.get(info.getServiceId());
        if (handler == null) {
            Log.e(TAG, "service not found: " + info.getServiceId());
            return UpnpError.UPNP_INTERNAL_ERROR;
        }

        return handler.onAction(info);
    }

    public AVTransport getAVTransport() {
        return (AVTransport)_services.get(ID_AVTransport);
    }

    public RenderingControl getRenderingControl() {
        return (RenderingControl)_services.get(ID_RenderingControl);
    }

    public SessionManager getSessionManager() {
        return (SessionManager)_services.get(ID_SessionManager);
    }

}
