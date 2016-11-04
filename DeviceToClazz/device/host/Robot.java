/* Automatic generated by DeviceToClazz */

package upnps.api.host.device.robot;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import miui.upnp.typedef.device.urn.DeviceType;
import miui.upnp.typedef.error.UpnpError;
import miui.upnp.typedef.device.Device;
import miui.upnp.typedef.device.invocation.ActionInfo;
import miui.upnp.typedef.exception.UpnpException;

import miui.upnp.manager.UpnpManager;
import miui.upnp.manager.handler.UpnpActionHandler;
import miui.upnp.manager.handler.UpnpCompletionHandler;
import miui.upnp.manager.host.DeviceConfig;
import miui.upnp.manager.host.ServiceHandler;

public class Robot implements UpnpActionHandler {

    private static final String TAG = "Robot";

    /**
     * deviceType & serviceId
     */
    public static final DeviceType DEVICE_TYPE = new DeviceType("Robot", "0.1");
    public static final String ID_RobotBind = "urn:upnp-org:serviceId:RobotBind";
    public static final String ID_RobotVoice = "urn:upnp-org:serviceId:RobotVoice";

    /**
     * device & service handler;
     */
    private Device _device;
    private Map<String, ServiceHandler> _services = new HashMap<String, ServiceHandler>();

    public Robot(Context context, DeviceConfig config) throws UpnpException {
        _device = config.build(context, DEVICE_TYPE);
        _services.put(ID_RobotBind, new RobotBind(_device));
        _services.put(ID_RobotVoice, new RobotVoice(_device));
    }

    public String getDeviceId() {
        return _device.getDeviceId();
    }

    public void start(UpnpCompletionHandler handler) throws UpnpException {
        UpnpManager.getInstance().getHost().register(_device, handler, this);
    }

    public void stop(UpnpCompletionHandler handler) throws UpnpException {
        UpnpManager.getInstance().getHost().unregister(_device, handler);
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

    public RobotBind getRobotBind() {
        return (RobotBind)_services.get(ID_RobotBind);
    }

    public RobotVoice getRobotVoice() {
        return (RobotVoice)_services.get(ID_RobotVoice);
    }

}