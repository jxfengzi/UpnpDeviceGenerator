/* Automatic generated by DeviceToClazz */

package upnps.api.host.device.avplayer;

import android.util.Log;

import upnp.typedef.datatype.DataType;
import upnp.typedef.device.Action;
import upnp.typedef.device.Device;
import upnp.typedef.device.urn.ServiceType;
import upnp.typedef.error.UpnpError;
import upnp.typedef.device.Argument;
import upnp.typedef.device.Service;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.device.invocation.EventInfo;
import upnp.typedef.device.invocation.EventInfoCreator;
import upnp.typedef.exception.UpnpException;

import upnp.typedef.property.AllowedValueList;
import upnp.typedef.property.AllowedValueRange;
import upnp.typedef.property.PropertyDefinition;
import upnps.manager.UpnpManager;
import upnps.manager.host.ServiceHandler;

public class SessionManager extends ServiceHandler {
    private static final String TAG = "SessionManager";
    private static final ServiceType SERVICE_TYPE =  new ServiceType("SessionManager", "1");

    //-------------------------------------------------------
    // Action Names (5)
    //-------------------------------------------------------
    public static final String ACTION_GetSessionCapabilities = "GetSessionCapabilities";
    public static final String _GetSessionCapabilities_ARG_Source = "Source";
    public static final String _GetSessionCapabilities_ARG_Sink = "Sink";
    public static final String ACTION_PrepareForSession = "PrepareForSession";
    public static final String _PrepareForSession_ARG_RemoteCapabilityInfo = "RemoteCapabilityInfo";
    public static final String _PrepareForSession_ARG_PeerSessionID = "PeerSessionID";
    public static final String _PrepareForSession_ARG_Direction = "Direction";
    public static final String _PrepareForSession_ARG_SessionID = "SessionID";
    public static final String _PrepareForSession_ARG_Address = "Address";
    public static final String _PrepareForSession_ARG_ServiceInstanceIDs = "ServiceInstanceIDs";
    public static final String ACTION_GetCurrentSessionInfo = "GetCurrentSessionInfo";
    public static final String _GetCurrentSessionInfo_ARG_SessionID = "SessionID";
    public static final String _GetCurrentSessionInfo_ARG_ServiceInstanceIDs = "ServiceInstanceIDs";
    public static final String _GetCurrentSessionInfo_ARG_CapabilityInfo = "CapabilityInfo";
    public static final String _GetCurrentSessionInfo_ARG_PeerSessionID = "PeerSessionID";
    public static final String _GetCurrentSessionInfo_ARG_Address = "Address";
    public static final String _GetCurrentSessionInfo_ARG_Direction = "Direction";
    public static final String _GetCurrentSessionInfo_ARG_Status = "Status";
    public static final String ACTION_SessionComplete = "SessionComplete";
    public static final String _SessionComplete_ARG_SessionID = "SessionID";
    public static final String ACTION_GetCurrentSessionIDs = "GetCurrentSessionIDs";
    public static final String _GetCurrentSessionIDs_ARG_SessionIDs = "SessionIDs";

    //-------------------------------------------------------
    // Property Name (9)
    //-------------------------------------------------------
    public static final String PROPERTY_A_ARG_TYPE_Direction = "A_ARG_TYPE_Direction";
    public static final String PROPERTY_SinkCapabilityInfo = "SinkCapabilityInfo";
    public static final String PROPERTY_SourceCapabilityInfo = "SourceCapabilityInfo";
    public static final String PROPERTY_A_ARG_TYPE_InstanceIDs = "A_ARG_TYPE_InstanceIDs";
    public static final String PROPERTY_A_ARG_TYPE_CapabilityInfo = "A_ARG_TYPE_CapabilityInfo";
    public static final String PROPERTY_A_ARG_TYPE_SessionID = "A_ARG_TYPE_SessionID";
    public static final String PROPERTY_CurrentSessionIDs = "CurrentSessionIDs";
    public static final String PROPERTY_A_ARG_TYPE_Address = "A_ARG_TYPE_Address";
    public static final String PROPERTY_A_ARG_TYPE_SessionStatus = "A_ARG_TYPE_SessionStatus";

    //-------------------------------------------------------
    // Property value defined (2)
    //-------------------------------------------------------

    public enum A_ARG_TYPE_Direction {
        UNDEFINED,
        V_Input,
        V_Output;

        private static final String CONST_UNDEFINED = "UNDEFINED";
        private static final String CONST_V_Input = "Input";
        private static final String CONST_V_Output = "Output";

        public static A_ARG_TYPE_Direction retrieveType(String value) {
            if (value.equals(CONST_UNDEFINED)) {
                return UNDEFINED;
            }

            if (value.equals(CONST_V_Input)) {
                return V_Input;
            }

            if (value.equals(CONST_V_Output)) {
                return V_Output;
            }

            return UNDEFINED;
        }

        public String getValue() {
            String value = null;
            switch (this) {
                case V_Input:
                    value = CONST_V_Input;
                    break;

                case V_Output:
                    value = CONST_V_Output;
                    break;

                default:
                    break;
            }

            return value;
        }
    }

    public enum A_ARG_TYPE_SessionStatus {
        UNDEFINED,
        V_OK,
        V_ContentFormatMismatch,
        V_InsufficientBandwidth,
        V_UnreliableChannel,
        V_Unknown;

        private static final String CONST_UNDEFINED = "UNDEFINED";
        private static final String CONST_V_OK = "OK";
        private static final String CONST_V_ContentFormatMismatch = "ContentFormatMismatch";
        private static final String CONST_V_InsufficientBandwidth = "InsufficientBandwidth";
        private static final String CONST_V_UnreliableChannel = "UnreliableChannel";
        private static final String CONST_V_Unknown = "Unknown";

        public static A_ARG_TYPE_SessionStatus retrieveType(String value) {
            if (value.equals(CONST_UNDEFINED)) {
                return UNDEFINED;
            }

            if (value.equals(CONST_V_OK)) {
                return V_OK;
            }

            if (value.equals(CONST_V_ContentFormatMismatch)) {
                return V_ContentFormatMismatch;
            }

            if (value.equals(CONST_V_InsufficientBandwidth)) {
                return V_InsufficientBandwidth;
            }

            if (value.equals(CONST_V_UnreliableChannel)) {
                return V_UnreliableChannel;
            }

            if (value.equals(CONST_V_Unknown)) {
                return V_Unknown;
            }

            return UNDEFINED;
        }

        public String getValue() {
            String value = null;
            switch (this) {
                case V_OK:
                    value = CONST_V_OK;
                    break;

                case V_ContentFormatMismatch:
                    value = CONST_V_ContentFormatMismatch;
                    break;

                case V_InsufficientBandwidth:
                    value = CONST_V_InsufficientBandwidth;
                    break;

                case V_UnreliableChannel:
                    value = CONST_V_UnreliableChannel;
                    break;

                case V_Unknown:
                    value = CONST_V_Unknown;
                    break;

                default:
                    break;
            }

            return value;
        }
    }

    //-------------------------------------------------------
    // Action Result (5)
    //-------------------------------------------------------

    public class GetSessionCapabilities_Result {
        public String theSource;
        public String theSink;
    }

    public class PrepareForSession_Result {
        public String theSessionID;
        public String theAddress;
        public String theServiceInstanceIDs;
    }

    public class GetCurrentSessionInfo_Result {
        public String theServiceInstanceIDs;
        public String theCapabilityInfo;
        public String thePeerSessionID;
        public String theAddress;
        public A_ARG_TYPE_Direction theDirection;
        public A_ARG_TYPE_SessionStatus theStatus;
    }

    // SessionComplete has no Result

    public class GetCurrentSessionIDs_Result {
        public String theSessionIDs;
    }


    //-------------------------------------------------------
    // Action Handler (5)
    //-------------------------------------------------------

    public interface Handler {
        UpnpError onGetSessionCapabilities(GetSessionCapabilities_Result result);
        UpnpError onPrepareForSession(String theRemoteCapabilityInfo, String thePeerSessionID, A_ARG_TYPE_Direction theDirection, PrepareForSession_Result result);
        UpnpError onGetCurrentSessionInfo(String theSessionID, GetCurrentSessionInfo_Result result);
        UpnpError onSessionComplete(String theSessionID);
        UpnpError onGetCurrentSessionIDs(GetCurrentSessionIDs_Result result);
    }

    private UpnpError handle_GetSessionCapabilities(ActionInfo info) {
        GetSessionCapabilities_Result result = new GetSessionCapabilities_Result();

        UpnpError error = _handler.onGetSessionCapabilities(result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_GetSessionCapabilities_ARG_Source, result.theSource, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_GetSessionCapabilities_ARG_Sink, result.theSink, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_PrepareForSession(ActionInfo info) {
        String theRemoteCapabilityInfo = (String)info.getArgumentValue(_PrepareForSession_ARG_RemoteCapabilityInfo);
        String thePeerSessionID = (String)info.getArgumentValue(_PrepareForSession_ARG_PeerSessionID);
        A_ARG_TYPE_Direction theDirection = A_ARG_TYPE_Direction.retrieveType((String)info.getArgumentValue(_PrepareForSession_ARG_Direction));
        PrepareForSession_Result result = new PrepareForSession_Result();

        UpnpError error = _handler.onPrepareForSession(theRemoteCapabilityInfo, thePeerSessionID, theDirection, result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_PrepareForSession_ARG_SessionID, result.theSessionID, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_PrepareForSession_ARG_Address, result.theAddress, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_PrepareForSession_ARG_ServiceInstanceIDs, result.theServiceInstanceIDs, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_GetCurrentSessionInfo(ActionInfo info) {
        String theSessionID = (String)info.getArgumentValue(_GetCurrentSessionInfo_ARG_SessionID);
        GetCurrentSessionInfo_Result result = new GetCurrentSessionInfo_Result();

        UpnpError error = _handler.onGetCurrentSessionInfo(theSessionID, result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_GetCurrentSessionInfo_ARG_ServiceInstanceIDs, result.theServiceInstanceIDs, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_GetCurrentSessionInfo_ARG_CapabilityInfo, result.theCapabilityInfo, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_GetCurrentSessionInfo_ARG_PeerSessionID, result.thePeerSessionID, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_GetCurrentSessionInfo_ARG_Address, result.theAddress, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_GetCurrentSessionInfo_ARG_Direction, result.theDirection.getValue(), Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_GetCurrentSessionInfo_ARG_Status, result.theStatus.getValue(), Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_SessionComplete(ActionInfo info) {
        String theSessionID = (String)info.getArgumentValue(_SessionComplete_ARG_SessionID);

        UpnpError error = _handler.onSessionComplete(theSessionID);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_GetCurrentSessionIDs(ActionInfo info) {
        GetCurrentSessionIDs_Result result = new GetCurrentSessionIDs_Result();

        UpnpError error = _handler.onGetCurrentSessionIDs(result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_GetCurrentSessionIDs_ARG_SessionIDs, result.theSessionIDs, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    //-------------------------------------------------------
    // Method
    //-------------------------------------------------------

    private Service _service;
    private Handler _handler;

    public SessionManager(Device device) {
        _service = new Service(SERVICE_TYPE);
        _service.setServiceId(toServiceId(SERVICE_TYPE));
        _service.setScpdUrl(toScpdUrl(device.getDeviceId(), SERVICE_TYPE));
        _service.setControlUrl(toCtrlUrl(device.getDeviceId(), SERVICE_TYPE));
        _service.setEventSubUrl(toEventUrl(device.getDeviceId(), SERVICE_TYPE));

        Action _GetSessionCapabilities = new Action(ACTION_GetSessionCapabilities);
        _GetSessionCapabilities.addArgument(new Argument(_GetSessionCapabilities_ARG_Source, Argument.Direction.OUT, PROPERTY_SourceCapabilityInfo));
        _GetSessionCapabilities.addArgument(new Argument(_GetSessionCapabilities_ARG_Sink, Argument.Direction.OUT, PROPERTY_SinkCapabilityInfo));
        _service.addAction(_GetSessionCapabilities);

        Action _PrepareForSession = new Action(ACTION_PrepareForSession);
        _PrepareForSession.addArgument(new Argument(_PrepareForSession_ARG_RemoteCapabilityInfo, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_CapabilityInfo));
        _PrepareForSession.addArgument(new Argument(_PrepareForSession_ARG_PeerSessionID, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_SessionID));
        _PrepareForSession.addArgument(new Argument(_PrepareForSession_ARG_Direction, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_Direction));
        _PrepareForSession.addArgument(new Argument(_PrepareForSession_ARG_SessionID, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_SessionID));
        _PrepareForSession.addArgument(new Argument(_PrepareForSession_ARG_Address, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_Address));
        _PrepareForSession.addArgument(new Argument(_PrepareForSession_ARG_ServiceInstanceIDs, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_InstanceIDs));
        _service.addAction(_PrepareForSession);

        Action _GetCurrentSessionInfo = new Action(ACTION_GetCurrentSessionInfo);
        _GetCurrentSessionInfo.addArgument(new Argument(_GetCurrentSessionInfo_ARG_SessionID, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_SessionID));
        _GetCurrentSessionInfo.addArgument(new Argument(_GetCurrentSessionInfo_ARG_ServiceInstanceIDs, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_InstanceIDs));
        _GetCurrentSessionInfo.addArgument(new Argument(_GetCurrentSessionInfo_ARG_CapabilityInfo, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_CapabilityInfo));
        _GetCurrentSessionInfo.addArgument(new Argument(_GetCurrentSessionInfo_ARG_PeerSessionID, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_SessionID));
        _GetCurrentSessionInfo.addArgument(new Argument(_GetCurrentSessionInfo_ARG_Address, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_Address));
        _GetCurrentSessionInfo.addArgument(new Argument(_GetCurrentSessionInfo_ARG_Direction, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_Direction));
        _GetCurrentSessionInfo.addArgument(new Argument(_GetCurrentSessionInfo_ARG_Status, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_SessionStatus));
        _service.addAction(_GetCurrentSessionInfo);

        Action _SessionComplete = new Action(ACTION_SessionComplete);
        _SessionComplete.addArgument(new Argument(_SessionComplete_ARG_SessionID, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_SessionID));
        _service.addAction(_SessionComplete);

        Action _GetCurrentSessionIDs = new Action(ACTION_GetCurrentSessionIDs);
        _GetCurrentSessionIDs.addArgument(new Argument(_GetCurrentSessionIDs_ARG_SessionIDs, Argument.Direction.OUT, PROPERTY_CurrentSessionIDs));
        _service.addAction(_GetCurrentSessionIDs);

        PropertyDefinition _A_ARG_TYPE_Direction = new PropertyDefinition(PROPERTY_A_ARG_TYPE_Direction, DataType.STRING, false);
        AllowedValueList _A_ARG_TYPE_Direction_list = new AllowedValueList(DataType.STRING);
        _A_ARG_TYPE_Direction_list.appendAllowedValue("Input");
        _A_ARG_TYPE_Direction_list.appendAllowedValue("Output");
        _A_ARG_TYPE_Direction.setAllowedValueList(_A_ARG_TYPE_Direction_list);
        _service.addProperty(_A_ARG_TYPE_Direction);

        PropertyDefinition _SinkCapabilityInfo = new PropertyDefinition(PROPERTY_SinkCapabilityInfo, DataType.STRING, false);
        _service.addProperty(_SinkCapabilityInfo);

        PropertyDefinition _SourceCapabilityInfo = new PropertyDefinition(PROPERTY_SourceCapabilityInfo, DataType.STRING, false);
        _service.addProperty(_SourceCapabilityInfo);

        PropertyDefinition _A_ARG_TYPE_InstanceIDs = new PropertyDefinition(PROPERTY_A_ARG_TYPE_InstanceIDs, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_InstanceIDs);

        PropertyDefinition _A_ARG_TYPE_CapabilityInfo = new PropertyDefinition(PROPERTY_A_ARG_TYPE_CapabilityInfo, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_CapabilityInfo);

        PropertyDefinition _A_ARG_TYPE_SessionID = new PropertyDefinition(PROPERTY_A_ARG_TYPE_SessionID, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_SessionID);

        PropertyDefinition _CurrentSessionIDs = new PropertyDefinition(PROPERTY_CurrentSessionIDs, DataType.STRING, false);
        _service.addProperty(_CurrentSessionIDs);

        PropertyDefinition _A_ARG_TYPE_Address = new PropertyDefinition(PROPERTY_A_ARG_TYPE_Address, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_Address);

        PropertyDefinition _A_ARG_TYPE_SessionStatus = new PropertyDefinition(PROPERTY_A_ARG_TYPE_SessionStatus, DataType.STRING, false);
        AllowedValueList _A_ARG_TYPE_SessionStatus_list = new AllowedValueList(DataType.STRING);
        _A_ARG_TYPE_SessionStatus_list.appendAllowedValue("OK");
        _A_ARG_TYPE_SessionStatus_list.appendAllowedValue("ContentFormatMismatch");
        _A_ARG_TYPE_SessionStatus_list.appendAllowedValue("InsufficientBandwidth");
        _A_ARG_TYPE_SessionStatus_list.appendAllowedValue("UnreliableChannel");
        _A_ARG_TYPE_SessionStatus_list.appendAllowedValue("Unknown");
        _A_ARG_TYPE_SessionStatus.setAllowedValueList(_A_ARG_TYPE_SessionStatus_list);
        _service.addProperty(_A_ARG_TYPE_SessionStatus);

        device.addService(_service);
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

        if (info.getAction().getName().equals(ACTION_GetSessionCapabilities)) {
            return handle_GetSessionCapabilities(info);
        }

        if (info.getAction().getName().equals(ACTION_PrepareForSession)) {
            return handle_PrepareForSession(info);
        }

        if (info.getAction().getName().equals(ACTION_GetCurrentSessionInfo)) {
            return handle_GetCurrentSessionInfo(info);
        }

        if (info.getAction().getName().equals(ACTION_SessionComplete)) {
            return handle_SessionComplete(info);
        }

        if (info.getAction().getName().equals(ACTION_GetCurrentSessionIDs)) {
            return handle_GetCurrentSessionIDs(info);
        }

        return UpnpError.UPNP_ACTION_NOT_IMPLEMENTED;
    }

    public void sendEvents() {
        EventInfo info = EventInfoCreator.create(_service);

        try {
            UpnpManager.getHost().sendEvents(info);
        } catch (UpnpException e) {
            e.printStackTrace();
        }
    }

}
