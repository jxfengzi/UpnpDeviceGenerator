/* Automatic generated by DeviceToClazz */

package upnps.api.host.device.binarylight;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import upnp.typedef.error.UpnpError;
import upnp.typedef.device.Device;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.exception.UpnpException;

import upnps.api.manager.UpnpManager;
import upnps.api.manager.handler.MyActionHandler;
import upnps.api.manager.handler.MyCompletionHandler;
import upnps.api.manager.host.config.DeviceConfig;
import upnps.api.manager.host.ServiceStub;

public class BinaryLight implements MyActionHandler {

    private static final String TAG = "BinaryLight";

    /**
     * deviceType & serviceType
     */
    public static final String DEVICE_TYPE = "BinaryLight";
    public static final String SERVICE_SwitchPower = "SwitchPower";

    /**
     * serviceId
     */
    private static final String ID_SwitchPower = "urn:upnp-org:serviceId:SwitchPower";

    /**
     * device & service handler;
     */

    private Device _device;
    private Map<String, ServiceStub> _services = new HashMap<String, ServiceStub>();

    public BinaryLight(Context context, DeviceConfig config) throws UpnpException {
        _device = config.build(context);
        _services.put(ID_SwitchPower, new SwitchPower(_device.getService(ID_SwitchPower)));
    }

    public String getDeviceId() {
        return _device.getDeviceId();
    }

    public void start(MyCompletionHandler handler) throws UpnpException {
        UpnpManager.getUpnp().register(_device, handler, this);
    }

    public void stop(MyCompletionHandler handler) throws UpnpException {
        UpnpManager.getUpnp().unregister(_device, handler);
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

    public SwitchPower getSwitchPower() {
        return (SwitchPower)_services.get(ID_SwitchPower);
    }

}