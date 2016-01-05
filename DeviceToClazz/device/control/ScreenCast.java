/* Automatic generated by DeviceToClazz */

package upnps.api.ctrlpoint.device.avserver;

import android.util.Log;

import java.util.List;

import upnp.typedef.error.UpnpError;
import upnp.typedef.device.Argument;
import upnp.typedef.device.Service;
import upnp.typedef.device.PropertyChanged;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.device.invocation.ActionInfoCreator;
import upnp.typedef.exception.UpnpException;
import upnp.typedef.property.DataType;
import upnp.typedef.property.Property;
import upnp.typedef.property.PropertyDefinition;

import upnps.api.manager.UpnpManager;
import upnps.api.manager.ctrlpoint.device.AbstractService;
import upnps.api.manager.handler.MyCompletionHandler;
import upnps.api.manager.handler.MyEventListener;
import upnps.api.manager.handler.MyInvokeCompletionHandler;

public class ScreenCast extends AbstractService {

    private static final String TAG = "ScreenCast";

    public ScreenCast(Service service) {
        super(service);
    }

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
    // ActionList (2)
    //-------------------------------------------------------

    public interface PrepareForCast_CompletedHandler {
        void onSucceed(Integer thePort);
        void onFailed(UpnpError error);
    }

    public void PrepareForCast(final PrepareForCast_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_PrepareForCast);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getUpnp().invoke(action, new MyInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                do {
                    Property pPort = invocation.getResult(_PrepareForCast_ARG_Port);
                    if (pPort == null) {
                        Log.d(TAG, String.format("%s not found", _PrepareForCast_ARG_Port));
                        break;
                    }

                    Integer thePort = (Integer) pPort.getCurrentValue();

                    handler.onSucceed(thePort);
                } while (false);
            }

            @Override
            public void onFailed(UpnpError error) {
                handler.onFailed(error);
            }
        });
    }

    public interface CastComplete_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void CastComplete(final CastComplete_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_CastComplete);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getUpnp().invoke(action, new MyInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                handler.onSucceed();
            }

            @Override
            public void onFailed(UpnpError error) {
                handler.onFailed(error);
            }
        });
    }

    //-------------------------------------------------------
    // Event
    //-------------------------------------------------------

}