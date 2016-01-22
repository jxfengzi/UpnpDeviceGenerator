/* Automatic generated by DeviceToClazz */

package upnps.api.ctrlpoint.device.binarylight;

import android.util.Log;

import java.util.List;

import upnp.typedef.error.UpnpError;
import upnp.typedef.device.Argument;
import upnp.typedef.device.Service;
import upnp.typedef.device.PropertyChanged;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.device.invocation.ActionInfoCreator;
import upnp.typedef.exception.UpnpException;
import upnp.typedef.datatype.DataType;
import upnp.typedef.property.Property;
import upnp.typedef.property.PropertyDefinition;

import upnps.manager.UpnpManager;
import upnps.manager.ctrlpoint.device.AbstractService;
import upnps.manager.handler.MyCompletionHandler;
import upnps.manager.handler.MyEventListener;
import upnps.manager.handler.MyInvokeCompletionHandler;

public class SwitchPower extends AbstractService {

    private static final String TAG = "SwitchPower";

    public SwitchPower(Service service) {
        super(service);
    }

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
    // ActionList (3)
    //-------------------------------------------------------

    public interface GetTarget_CompletedHandler {
        void onSucceed(Boolean theRetTargetValue);
        void onFailed(UpnpError error);
    }

    public void GetTarget(final GetTarget_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_GetTarget);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getControlPoint().invoke(action, new MyInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                do {
                    Property pRetTargetValue = invocation.getResult(_GetTarget_ARG_RetTargetValue);
                    if (pRetTargetValue == null) {
                        Log.d(TAG, String.format("%s not found", _GetTarget_ARG_RetTargetValue));
                        break;
                    }

                    Boolean theRetTargetValue = (Boolean) pRetTargetValue.getCurrentValue();

                    handler.onSucceed(theRetTargetValue);
                } while (false);
            }

            @Override
            public void onFailed(UpnpError error) {
                handler.onFailed(error);
            }
        });
    }

    public interface SetTarget_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void SetTarget(Boolean newTargetValue, final SetTarget_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_SetTarget);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_SetTarget_ARG_newTargetValue, newTargetValue, Argument.Direction.IN)) {
            throw new UpnpException(UpnpError.INVALID_ARGUMENT);
        }

        UpnpManager.getControlPoint().invoke(action, new MyInvokeCompletionHandler() {

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

    public interface GetStatus_CompletedHandler {
        void onSucceed(Boolean theResultStatus);
        void onFailed(UpnpError error);
    }

    public void GetStatus(final GetStatus_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_GetStatus);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getControlPoint().invoke(action, new MyInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                do {
                    Property pResultStatus = invocation.getResult(_GetStatus_ARG_ResultStatus);
                    if (pResultStatus == null) {
                        Log.d(TAG, String.format("%s not found", _GetStatus_ARG_ResultStatus));
                        break;
                    }

                    Boolean theResultStatus = (Boolean) pResultStatus.getCurrentValue();

                    handler.onSucceed(theResultStatus);
                } while (false);
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

    public interface CompletionHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public interface EventListener {
        void onSubscriptionExpired();
        void onStatusChanged(Boolean currentValue);
    }

    public void subscribe(final CompletionHandler handler, final EventListener listener) throws UpnpException {
        if (this.service.isSubscribed()) {
            throw new UpnpException(UpnpError.SERVICE_SUBSCRIBED);
        }

        if (handler == null) {
            throw new UpnpException(UpnpError.INVALID_ARGUMENT);
        }

        if (listener == null) {
            throw new UpnpException(UpnpError.INVALID_ARGUMENT);
        }


        UpnpManager.getControlPoint().subscribe(this.service,
            new MyCompletionHandler() {

                 @Override
                public void onSucceed() {
                    handler.onSucceed();
                }

                @Override
                public void onFailed(UpnpError error) {
                    handler.onFailed(error);
                }
            },
            new MyEventListener() {
                @Override
                public void onSubscriptionExpired(String serviceId) {
                    listener.onSubscriptionExpired();
                }

                @Override
                public void onEvent(String serviceId, List<PropertyChanged> list) {
                    for (PropertyChanged c : list) {
                        if (c.getName().equals(PROPERTY_Status)) {
                            PropertyDefinition def = service.getPropertyDefinition(PROPERTY_Status);
                            listener.onStatusChanged((Boolean)def.getDataType().toObjectValue(c.getValue()));
                        }
                    }
                }
            });
    }

    public void unsubscribe(final CompletionHandler handler) throws UpnpException {
        if (! this.service.isSubscribed()) {
            throw new UpnpException(UpnpError.SERVICE_UNSUBSCRIBED);
        }

        if (handler == null) {
            throw new UpnpException(UpnpError.INVALID_ARGUMENT);
        }

        UpnpManager.getControlPoint().unsubscribe(this.service,
            new MyCompletionHandler() {
                @Override
                public void onSucceed() {
                    handler.onSucceed();
                }

                @Override
                public void onFailed(UpnpError error) {
                    handler.onFailed(error);
                }
            });
    }

}
