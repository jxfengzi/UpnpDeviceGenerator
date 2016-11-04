/* Automatic generated by DeviceToClazz */

package upnps.api.ctrlpoint.device.robot;

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

public class RobotVoice extends AbstractService {

    private static final String TAG = "RobotVoice";
    private static final ServiceType SERVICE_TYPE =  new ServiceType("RobotVoice", "0.1");

    public RobotVoice(Service service) {
        super(service);
    }

    //-------------------------------------------------------
    // Action Names (2)
    //-------------------------------------------------------
    public static final String ACTION_RB_setVoiceReply = "RB_setVoiceReply";
    public static final String _RB_setVoiceReply_ARG_Reply = "Reply";
    public static final String ACTION_RB_setMetaData = "RB_setMetaData";
    public static final String _RB_setMetaData_ARG_MetaData = "MetaData";

    //-------------------------------------------------------
    // Property Name (5)
    //-------------------------------------------------------
    public static final String PROPERTY_RB_Voice = "RB_Voice";
    public static final String PROPERTY_A_ARG_TYPE_Reply = "A_ARG_TYPE_Reply";
    public static final String PROPERTY_RB_MetaData = "RB_MetaData";
    public static final String PROPERTY_RB_VoiceStart = "RB_VoiceStart";
    public static final String PROPERTY_A_ARG_TYPE_MetaData = "A_ARG_TYPE_MetaData";

    //-------------------------------------------------------
    // Property value defined (0)
    //-------------------------------------------------------

    //-------------------------------------------------------
    // ActionList (2)
    //-------------------------------------------------------

    public interface RB_setVoiceReply_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void RB_setVoiceReply(String Reply, final RB_setVoiceReply_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_RB_setVoiceReply);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_RB_setVoiceReply_ARG_Reply, Reply, Argument.Direction.IN)) {
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

    public interface RB_setMetaData_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void RB_setMetaData(String MetaData, final RB_setMetaData_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_RB_setMetaData);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_RB_setMetaData_ARG_MetaData, MetaData, Argument.Direction.IN)) {
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
        void onRB_VoiceChanged(String currentValue);
        void onRB_MetaDataChanged(String currentValue);
        void onRB_VoiceStartChanged(String currentValue);
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
                        if (c.getName().equals(PROPERTY_RB_Voice)) {
                            PropertyDefinition def = service.getPropertyDefinition(PROPERTY_RB_Voice);
                            listener.onRB_VoiceChanged((String)def.getDataType().toObjectValue(c.getValue()));
                        }
                        if (c.getName().equals(PROPERTY_RB_MetaData)) {
                            PropertyDefinition def = service.getPropertyDefinition(PROPERTY_RB_MetaData);
                            listener.onRB_MetaDataChanged((String)def.getDataType().toObjectValue(c.getValue()));
                        }
                        if (c.getName().equals(PROPERTY_RB_VoiceStart)) {
                            PropertyDefinition def = service.getPropertyDefinition(PROPERTY_RB_VoiceStart);
                            listener.onRB_VoiceStartChanged((String)def.getDataType().toObjectValue(c.getValue()));
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
