/* Automatic generated by DeviceToClazz */

package upnps.api.host.device.avserver;

import android.util.Log;

import upnp.typedef.error.UpnpError;
import upnp.typedef.device.Argument;
import upnp.typedef.device.Service;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.device.invocation.EventInfo;
import upnp.typedef.device.invocation.EventInfoCreator;
import upnp.typedef.exception.UpnpException;

import upnps.api.manager.UpnpManager;
import upnps.api.manager.host.ServiceStub;

public class ScreenCast implements ServiceStub {
    private static final String TAG = "ScreenCast";

    //-------------------------------------------------------
    // Action Names (2)
    //-------------------------------------------------------
    public static final String ACTION_PrepareForCast = "PrepareForCast";
    public static final String _PrepareForCast_ARG_Port = "Port";
    public static final String ACTION_CastComplete = "CastComplete";

    //-------------------------------------------------------
    // Property Name (1)
    //-------------------------------------------------------
    public static final String PROPERTY_RtspServerPort = "RtspServerPort";

    //-------------------------------------------------------
    // Property value defined (0)
    //-------------------------------------------------------

    //-------------------------------------------------------
    // Action Result (2)
    //-------------------------------------------------------

    public class PrepareForCast_Result {
        public Integer thePort;
    }

    // CastComplete has no Result


    //-------------------------------------------------------
    // Action Handler (2)
    //-------------------------------------------------------

    public interface Handler {
        UpnpError onPrepareForCast(PrepareForCast_Result result);
        UpnpError onCastComplete();
    }

    private UpnpError handle_PrepareForCast(ActionInfo info) {
        PrepareForCast_Result result = new PrepareForCast_Result();

        UpnpError error = _handler.onPrepareForCast(result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_PrepareForCast_ARG_Port, result.thePort, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_CastComplete(ActionInfo info) {

        UpnpError error = _handler.onCastComplete();
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        return UpnpError.OK;
    }

    //-------------------------------------------------------
    // Method
    //-------------------------------------------------------

    private Service _service;
    private Handler _handler;

    public ScreenCast(Service service) {
        _service = service;
    }

    public void setHandler(Handler handler) {
        _handler = handler;
    }

    @Override
    public UpnpError onAction(ActionInfo info) {
        if (_handler == null) {
           Log.e(TAG, "handler is null");
           return UpnpError.UPNP_ACTION_NOT_IMPLEMENTED;
        }

        if (info.getAction().getName().equals(ACTION_PrepareForCast)) {
            return handle_PrepareForCast(info);
        }

        if (info.getAction().getName().equals(ACTION_CastComplete)) {
            return handle_CastComplete(info);
        }

        return UpnpError.UPNP_ACTION_NOT_IMPLEMENTED;
    }

    public void sendEvents() {
        EventInfo info = EventInfoCreator.create(_service);

        try {
            UpnpManager.getUpnp().sendEvents(info);
        } catch (UpnpException e) {
            e.printStackTrace();
        }
    }

}