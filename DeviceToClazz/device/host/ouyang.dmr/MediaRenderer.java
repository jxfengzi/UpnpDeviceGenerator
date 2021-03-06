/* Automatic generated by DeviceToClazz */

package upnps.api.host.device.mediarenderer;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import upnp.typedef.device.Device;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.exception.UpnpException;

import upnps.api.manager.UpnpManager;
import upnps.api.manager.handler.MyActionHandler;
import upnps.api.manager.handler.MyCompletionHandler;
import upnps.api.manager.host.ServiceActionHandler;
import upnps.api.manager.host.config.DeviceConfig;

public class MediaRenderer implements MyActionHandler {

    private static final String TAG = "MediaRenderer";

    /**
     * deviceType & serviceType
     */
    public static final String DEVICE_TYPE = "MediaRenderer";
    public static final String SERVICE_AVTransport = "AVTransport";
    public static final String SERVICE_ConnectionManager = "ConnectionManager";
    public static final String SERVICE_RenderingControl = "RenderingControl";

    /**
     * serviceId
     */
    private static final String ID_AVTransport = "urn:upnp-org:serviceId:AVTransport";
    private static final String ID_ConnectionManager = "urn:upnp-org:serviceId:ConnectionManager";
    private static final String ID_RenderingControl = "urn:upnp-org:serviceId:RenderingControl";

    /**
     * device & service handler;
     */

    private Device _device;
    private Map<String, ServiceActionHandler> _services = new HashMap<String, ServiceActionHandler>();

    public MediaRenderer(Context context, DeviceConfig config) throws UpnpException {
        _device = config.build(context);
        _services.put(ID_AVTransport, new AVTransport(_device.getService(ID_AVTransport)));
        _services.put(ID_ConnectionManager, new ConnectionManager(_device.getService(ID_ConnectionManager)));
        _services.put(ID_RenderingControl, new RenderingControl(_device.getService(ID_RenderingControl)));
    }

    public String getDeviceId() {
        return _device.getDeviceId();
    }

    public void start(MyCompletionHandler handler) {
        try {
            UpnpManager.getUpnp().register(_device, handler, this);
        } catch (UpnpException e) {
            e.printStackTrace();
        }
    }

    public void stop(MyCompletionHandler handler) {
        try {
            UpnpManager.getUpnp().unregister(_device, handler);
        } catch (UpnpException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAction(ActionInfo info) {
        ServiceActionHandler handler = _services.get(info.getServiceId());
        if (handler == null) {
            Log.e(TAG, "service not found: " + info.getServiceId());
            return;
        }

        handler.onAction(info);
    }

    public AVTransport getAVTransport() {
        return (AVTransport)_services.get(ID_AVTransport);
    }

    public ConnectionManager getConnectionManager() {
        return (ConnectionManager)_services.get(ID_ConnectionManager);
    }

    public RenderingControl getRenderingControl() {
        return (RenderingControl)_services.get(ID_RenderingControl);
    }

}
