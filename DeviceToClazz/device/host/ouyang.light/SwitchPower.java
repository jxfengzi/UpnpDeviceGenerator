/* Automatic generated by DeviceToClazz */

package upnps.api.host.device.binarylight;

import android.util.Log;

import upnp.typedef.device.Argument;
import upnp.typedef.device.Service;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.device.invocation.EventInfo;
import upnp.typedef.device.invocation.EventInfoCreator;
import upnp.typedef.exception.UpnpException;

import upnps.api.manager.UpnpManager;
import upnps.api.manager.host.ServiceStub;

public class SwitchPower implements ServiceStub {
    private static final String TAG = "SwitchPower";

    //-------------------------------------------------------
    // Action Names (3)
    //-------------------------------------------------------
    public static final String ACTION_GetTarget = "GetTarget";
    public static final String _GetTarget_ARG_RetTargetValue = "RetTargetValue";
    public static final String ACTION_SetTarget = "SetTarget";
    public static final String _SetTarget_ARG_newTargetValue = "newTargetValue";
    public static final String ACTION_GetStatus = "GetStatus";
    public static final String _GetStatus_ARG_ResultStatus = "ResultStatus";

    //-------------------------------------------------------
    // Property Name (2)
    //-------------------------------------------------------
    public static final String PROPERTY_Status = "Status";
    public static final String PROPERTY_Target = "Target";

    //-------------------------------------------------------
    // Property value defined (0)
    //-------------------------------------------------------

    //-------------------------------------------------------
    // Action Result (3)
    //-------------------------------------------------------

    public class GetTarget_Result {
        public Boolean theRetTargetValue;
    }

    // SetTarget has no Result

    public class GetStatus_Result {
        public Boolean theResultStatus;
    }


    //-------------------------------------------------------
    // Action Handler (3)
    //-------------------------------------------------------

    public interface Handler {
        boolean onGetTarget(GetTarget_Result result);
        boolean onSetTarget(Boolean thenewTargetValue);
        boolean onGetStatus(GetStatus_Result result);
    }

    private boolean handle_GetTarget(ActionInfo info) {
        GetTarget_Result result = new GetTarget_Result();

        if (_handler.onGetTarget(result)) {
            if (! info.setArgumentValue(_GetTarget_ARG_RetTargetValue, result.theRetTargetValue, Argument.Direction.OUT)) {
                Log.d(TAG, "setArgumentValue: false");
                return false;
            }

            return true;
        }

        return false;
    }

    private boolean handle_SetTarget(ActionInfo info) {
        Boolean thenewTargetValue = (Boolean)info.getArgumentValue(_SetTarget_ARG_newTargetValue);

        if (_handler.onSetTarget(thenewTargetValue)) {
            return true;
        }

        return false;
    }

    private boolean handle_GetStatus(ActionInfo info) {
        GetStatus_Result result = new GetStatus_Result();

        if (_handler.onGetStatus(result)) {
            if (! info.setArgumentValue(_GetStatus_ARG_ResultStatus, result.theResultStatus, Argument.Direction.OUT)) {
                Log.d(TAG, "setArgumentValue: false");
                return false;
            }

            return true;
        }

        return false;
    }

    //-------------------------------------------------------
    // Method
    //-------------------------------------------------------

    private Service _service;
    private Handler _handler;

    public SwitchPower(Service service) {
        _service = service;
    }

    public void setHandler(Handler handler) {
        _handler = handler;
    }

    @Override
    public boolean onAction(ActionInfo info) {
        if (_handler == null) {
           Log.e(TAG, "handler is null");
           return false;
        }

        if (info.getAction().getName().equals(ACTION_GetTarget)) {
            return handle_GetTarget(info);
        }

        if (info.getAction().getName().equals(ACTION_SetTarget)) {
            return handle_SetTarget(info);
        }

        if (info.getAction().getName().equals(ACTION_GetStatus)) {
            return handle_GetStatus(info);
        }

        return false;
    }

    public void sendEvents() {
        EventInfo info = EventInfoCreator.create(_service);

        try {
            UpnpManager.getUpnp().publishEvent(info);
        } catch (UpnpException e) {
            e.printStackTrace();
        }
    }

    public void setStatus(Boolean theStatus) {
         _service.setPropertyValue(PROPERTY_Status, theStatus);
    }

}
