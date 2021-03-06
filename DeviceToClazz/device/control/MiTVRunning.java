/* Automatic generated by DeviceToClazz */

package upnps.api.ctrlpoint.device.mitv;

import android.util.Log;

import java.util.List;

import miui.upnp.typedef.device.urn.ServiceType;
import miui.upnp.typedef.error.UpnpError;
import miui.upnp.typedef.device.Argument;
import miui.upnp.typedef.device.Service;
import miui.upnp.typedef.device.PropertyChanged;
import miui.upnp.typedef.device.invocation.ActionInfo;
import miui.upnp.typedef.device.invocation.ActionInfoCreator;
import miui.upnp.typedef.exception.UpnpException;
import miui.upnp.typedef.datatype.DataType;
import miui.upnp.typedef.property.Property;
import miui.upnp.typedef.property.PropertyDefinition;

import miui.upnp.manager.UpnpManager;
import miui.upnp.manager.ctrlpoint.AbstractService;
import miui.upnp.manager.handler.UpnpCompletionHandler;
import miui.upnp.manager.handler.UpnpEventListener;
import miui.upnp.manager.handler.UpnpInvokeCompletionHandler;

public class MiTVRunning extends AbstractService {

    private static final String TAG = "MiTVRunning";
    private static final ServiceType SERVICE_TYPE =  new ServiceType("MiTVRunning", "0.1");

    public MiTVRunning(Service service) {
        super(service);
    }

    //-------------------------------------------------------
    // Action Names (5)
    //-------------------------------------------------------
    public static final String ACTION_GetPlayState = "GetPlayState";
    public static final String _GetPlayState_ARG_OutPlayState = "OutPlayState";
    public static final String ACTION_SetPlayState = "SetPlayState";
    public static final String _SetPlayState_ARG_InPlayState = "InPlayState";
    public static final String ACTION_SendCommand = "SendCommand";
    public static final String _SendCommand_ARG_InApplication = "InApplication";
    public static final String _SendCommand_ARG_InCommand = "InCommand";
    public static final String ACTION_StopApp = "StopApp";
    public static final String _StopApp_ARG_InApplication = "InApplication";
    public static final String ACTION_StartApp = "StartApp";
    public static final String _StartApp_ARG_InApplication = "InApplication";

    //-------------------------------------------------------
    // Property Name (4)
    //-------------------------------------------------------
    public static final String PROPERTY_RunningApp = "RunningApp";
    public static final String PROPERTY_A_ARG_TYPE_Command = "A_ARG_TYPE_Command";
    public static final String PROPERTY_PlayState = "PlayState";
    public static final String PROPERTY_A_ARG_TYPE_Application = "A_ARG_TYPE_Application";

    //-------------------------------------------------------
    // Property value defined (1)
    //-------------------------------------------------------

    public enum PlayState {
        UNDEFINED,
        V_Playing,
        V_Paused,
        V_Stopped;

        private static final String CONST_UNDEFINED = "UNDEFINED";
        private static final String CONST_V_Playing = "Playing";
        private static final String CONST_V_Paused = "Paused";
        private static final String CONST_V_Stopped = "Stopped";

        public static PlayState retrieveType(String value) {
            if (value.equals(CONST_UNDEFINED)) {
                return UNDEFINED;
            }

            if (value.equals(CONST_V_Playing)) {
                return V_Playing;
            }

            if (value.equals(CONST_V_Paused)) {
                return V_Paused;
            }

            if (value.equals(CONST_V_Stopped)) {
                return V_Stopped;
            }

            return UNDEFINED;
        }

        public String getValue() {
            String value = null;
            switch (this) {
                case V_Playing:
                    value = CONST_V_Playing;
                    break;

                case V_Paused:
                    value = CONST_V_Paused;
                    break;

                case V_Stopped:
                    value = CONST_V_Stopped;
                    break;

                default:
                    break;
            }

            return value;
        }
    }

    //-------------------------------------------------------
    // ActionList (5)
    //-------------------------------------------------------

    public interface GetPlayState_CompletedHandler {
        void onSucceed(PlayState theOutPlayState);
        void onFailed(UpnpError error);
    }

    public void GetPlayState(final GetPlayState_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_GetPlayState);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                do {
                    Property pOutPlayState = invocation.getResult(_GetPlayState_ARG_OutPlayState);
                    if (pOutPlayState == null) {
                        Log.d(TAG, String.format("%s not found", _GetPlayState_ARG_OutPlayState));
                        break;
                    }

                    PlayState theOutPlayState = PlayState.retrieveType(pOutPlayState.getCurrentValue().toString());

                    handler.onSucceed(theOutPlayState);
                } while (false);
            }

            @Override
            public void onFailed(UpnpError error) {
                handler.onFailed(error);
            }
        });
    }

    public interface SetPlayState_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void SetPlayState(PlayState InPlayState, final SetPlayState_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_SetPlayState);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_SetPlayState_ARG_InPlayState, InPlayState.getValue(), Argument.Direction.IN)) {
            throw new UpnpException(UpnpError.INVALID_ARGUMENT);
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

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

    public interface SendCommand_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void SendCommand(String InApplication, String InCommand, final SendCommand_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_SendCommand);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_SendCommand_ARG_InApplication, InApplication, Argument.Direction.IN)) {
            throw new UpnpException(UpnpError.INVALID_ARGUMENT);
        }

        if (!action.setArgumentValue(_SendCommand_ARG_InCommand, InCommand, Argument.Direction.IN)) {
            throw new UpnpException(UpnpError.INVALID_ARGUMENT);
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

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

    public interface StopApp_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void StopApp(String InApplication, final StopApp_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_StopApp);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_StopApp_ARG_InApplication, InApplication, Argument.Direction.IN)) {
            throw new UpnpException(UpnpError.INVALID_ARGUMENT);
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

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

    public interface StartApp_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void StartApp(String InApplication, final StartApp_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_StartApp);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_StartApp_ARG_InApplication, InApplication, Argument.Direction.IN)) {
            throw new UpnpException(UpnpError.INVALID_ARGUMENT);
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

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

    public interface CompletionHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public interface EventListener {
        void onSubscriptionExpired();
        void onRunningAppChanged(String currentValue);
        void onPlayStateChanged(PlayState currentValue);
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


        UpnpManager.getInstance().getControlPoint().subscribe(this.service,
            new UpnpCompletionHandler() {

                 @Override
                public void onSucceed() {
                    handler.onSucceed();
                }

                @Override
                public void onFailed(UpnpError error) {
                    handler.onFailed(error);
                }
            },
            new UpnpEventListener() {
                @Override
                public void onSubscriptionExpired(String serviceId) {
                    listener.onSubscriptionExpired();
                }

                @Override
                public void onEvent(String serviceId, List<PropertyChanged> list) {
                    for (PropertyChanged c : list) {
                        if (c.getName().equals(PROPERTY_RunningApp)) {
                            PropertyDefinition def = service.getPropertyDefinition(PROPERTY_RunningApp);
                            listener.onRunningAppChanged((String)def.getDataType().toObjectValue(c.getValue()));
                        }
                        if (c.getName().equals(PROPERTY_PlayState)) {
                            PropertyDefinition def = service.getPropertyDefinition(PROPERTY_PlayState);
                            listener.onPlayStateChanged(PlayState.retrieveType((String)def.getDataType().toObjectValue(c.getValue())));
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

        UpnpManager.getInstance().getControlPoint().unsubscribe(this.service,
            new UpnpCompletionHandler() {
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
