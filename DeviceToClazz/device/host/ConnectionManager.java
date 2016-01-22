/* Automatic generated by DeviceToClazz */

package upnps.api.host.device.mediaserver;

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

public class ConnectionManager extends ServiceHandler {
    private static final String TAG = "ConnectionManager";
    private static final ServiceType SERVICE_TYPE =  new ServiceType("ConnectionManager", "1");

    //-------------------------------------------------------
    // Action Names (3)
    //-------------------------------------------------------
    public static final String ACTION_GetCurrentConnectionInfo = "GetCurrentConnectionInfo";
    public static final String _GetCurrentConnectionInfo_ARG_ConnectionID = "ConnectionID";
    public static final String _GetCurrentConnectionInfo_ARG_RcsID = "RcsID";
    public static final String _GetCurrentConnectionInfo_ARG_AVTransportID = "AVTransportID";
    public static final String _GetCurrentConnectionInfo_ARG_ProtocolInfo = "ProtocolInfo";
    public static final String _GetCurrentConnectionInfo_ARG_PeerConnectionManager = "PeerConnectionManager";
    public static final String _GetCurrentConnectionInfo_ARG_PeerConnectionID = "PeerConnectionID";
    public static final String _GetCurrentConnectionInfo_ARG_Direction = "Direction";
    public static final String _GetCurrentConnectionInfo_ARG_Status = "Status";
    public static final String ACTION_GetProtocolInfo = "GetProtocolInfo";
    public static final String _GetProtocolInfo_ARG_Source = "Source";
    public static final String _GetProtocolInfo_ARG_Sink = "Sink";
    public static final String ACTION_GetCurrentConnectionIDs = "GetCurrentConnectionIDs";
    public static final String _GetCurrentConnectionIDs_ARG_ConnectionIDs = "ConnectionIDs";

    //-------------------------------------------------------
    // Property Name (10)
    //-------------------------------------------------------
    public static final String PROPERTY_A_ARG_TYPE_Direction = "A_ARG_TYPE_Direction";
    public static final String PROPERTY_SinkProtocolInfo = "SinkProtocolInfo";
    public static final String PROPERTY_A_ARG_TYPE_RcsID = "A_ARG_TYPE_RcsID";
    public static final String PROPERTY_A_ARG_TYPE_ConnectionManager = "A_ARG_TYPE_ConnectionManager";
    public static final String PROPERTY_A_ARG_TYPE_ProtocolInfo = "A_ARG_TYPE_ProtocolInfo";
    public static final String PROPERTY_SourceProtocolInfo = "SourceProtocolInfo";
    public static final String PROPERTY_A_ARG_TYPE_ConnectionID = "A_ARG_TYPE_ConnectionID";
    public static final String PROPERTY_A_ARG_TYPE_ConnectionStatus = "A_ARG_TYPE_ConnectionStatus";
    public static final String PROPERTY_CurrentConnectionIDs = "CurrentConnectionIDs";
    public static final String PROPERTY_A_ARG_TYPE_AVTransportID = "A_ARG_TYPE_AVTransportID";

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

    public enum A_ARG_TYPE_ConnectionStatus {
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

        public static A_ARG_TYPE_ConnectionStatus retrieveType(String value) {
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
    // Action Result (3)
    //-------------------------------------------------------

    public class GetCurrentConnectionInfo_Result {
        public Integer theRcsID;
        public Integer theAVTransportID;
        public String theProtocolInfo;
        public String thePeerConnectionManager;
        public Integer thePeerConnectionID;
        public A_ARG_TYPE_Direction theDirection;
        public A_ARG_TYPE_ConnectionStatus theStatus;
    }

    public class GetProtocolInfo_Result {
        public String theSource;
        public String theSink;
    }

    public class GetCurrentConnectionIDs_Result {
        public String theConnectionIDs;
    }


    //-------------------------------------------------------
    // Action Handler (3)
    //-------------------------------------------------------

    public interface Handler {
        UpnpError onGetCurrentConnectionInfo(Integer theConnectionID, GetCurrentConnectionInfo_Result result);
        UpnpError onGetProtocolInfo(GetProtocolInfo_Result result);
        UpnpError onGetCurrentConnectionIDs(GetCurrentConnectionIDs_Result result);
    }

    private UpnpError handle_GetCurrentConnectionInfo(ActionInfo info) {
        Integer theConnectionID = (Integer)info.getArgumentValue(_GetCurrentConnectionInfo_ARG_ConnectionID);
        GetCurrentConnectionInfo_Result result = new GetCurrentConnectionInfo_Result();

        UpnpError error = _handler.onGetCurrentConnectionInfo(theConnectionID, result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_GetCurrentConnectionInfo_ARG_RcsID, result.theRcsID, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_GetCurrentConnectionInfo_ARG_AVTransportID, result.theAVTransportID, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_GetCurrentConnectionInfo_ARG_ProtocolInfo, result.theProtocolInfo, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_GetCurrentConnectionInfo_ARG_PeerConnectionManager, result.thePeerConnectionManager, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_GetCurrentConnectionInfo_ARG_PeerConnectionID, result.thePeerConnectionID, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_GetCurrentConnectionInfo_ARG_Direction, result.theDirection.getValue(), Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_GetCurrentConnectionInfo_ARG_Status, result.theStatus.getValue(), Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_GetProtocolInfo(ActionInfo info) {
        GetProtocolInfo_Result result = new GetProtocolInfo_Result();

        UpnpError error = _handler.onGetProtocolInfo(result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_GetProtocolInfo_ARG_Source, result.theSource, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_GetProtocolInfo_ARG_Sink, result.theSink, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_GetCurrentConnectionIDs(ActionInfo info) {
        GetCurrentConnectionIDs_Result result = new GetCurrentConnectionIDs_Result();

        UpnpError error = _handler.onGetCurrentConnectionIDs(result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_GetCurrentConnectionIDs_ARG_ConnectionIDs, result.theConnectionIDs, Argument.Direction.OUT)) {
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

    public ConnectionManager(Device device) {
        _service = new Service(SERVICE_TYPE);
        _service.setServiceId(toServiceId(SERVICE_TYPE));
        _service.setScpdUrl(toScpdUrl(device.getDeviceId(), SERVICE_TYPE));
        _service.setControlUrl(toCtrlUrl(device.getDeviceId(), SERVICE_TYPE));
        _service.setEventSubUrl(toEventUrl(device.getDeviceId(), SERVICE_TYPE));

        Action _GetCurrentConnectionInfo = new Action(ACTION_GetCurrentConnectionInfo);
        _GetCurrentConnectionInfo.addArgument(new Argument(_GetCurrentConnectionInfo_ARG_ConnectionID, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_ConnectionID));
        _GetCurrentConnectionInfo.addArgument(new Argument(_GetCurrentConnectionInfo_ARG_RcsID, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_RcsID));
        _GetCurrentConnectionInfo.addArgument(new Argument(_GetCurrentConnectionInfo_ARG_AVTransportID, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_AVTransportID));
        _GetCurrentConnectionInfo.addArgument(new Argument(_GetCurrentConnectionInfo_ARG_ProtocolInfo, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_ProtocolInfo));
        _GetCurrentConnectionInfo.addArgument(new Argument(_GetCurrentConnectionInfo_ARG_PeerConnectionManager, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_ConnectionManager));
        _GetCurrentConnectionInfo.addArgument(new Argument(_GetCurrentConnectionInfo_ARG_PeerConnectionID, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_ConnectionID));
        _GetCurrentConnectionInfo.addArgument(new Argument(_GetCurrentConnectionInfo_ARG_Direction, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_Direction));
        _GetCurrentConnectionInfo.addArgument(new Argument(_GetCurrentConnectionInfo_ARG_Status, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_ConnectionStatus));
        _service.addAction(_GetCurrentConnectionInfo);

        Action _GetProtocolInfo = new Action(ACTION_GetProtocolInfo);
        _GetProtocolInfo.addArgument(new Argument(_GetProtocolInfo_ARG_Source, Argument.Direction.OUT, PROPERTY_SourceProtocolInfo));
        _GetProtocolInfo.addArgument(new Argument(_GetProtocolInfo_ARG_Sink, Argument.Direction.OUT, PROPERTY_SinkProtocolInfo));
        _service.addAction(_GetProtocolInfo);

        Action _GetCurrentConnectionIDs = new Action(ACTION_GetCurrentConnectionIDs);
        _GetCurrentConnectionIDs.addArgument(new Argument(_GetCurrentConnectionIDs_ARG_ConnectionIDs, Argument.Direction.OUT, PROPERTY_CurrentConnectionIDs));
        _service.addAction(_GetCurrentConnectionIDs);

        PropertyDefinition _A_ARG_TYPE_Direction = new PropertyDefinition(PROPERTY_A_ARG_TYPE_Direction, DataType.STRING, false);
        AllowedValueList _A_ARG_TYPE_Direction_list = new AllowedValueList(DataType.STRING);
        _A_ARG_TYPE_Direction_list.appendAllowedValue("Input");
        _A_ARG_TYPE_Direction_list.appendAllowedValue("Output");
        _A_ARG_TYPE_Direction.setAllowedValueList(_A_ARG_TYPE_Direction_list);
        _service.addProperty(_A_ARG_TYPE_Direction);

        PropertyDefinition _SinkProtocolInfo = new PropertyDefinition(PROPERTY_SinkProtocolInfo, DataType.STRING, true);
        _service.addProperty(_SinkProtocolInfo);

        PropertyDefinition _A_ARG_TYPE_RcsID = new PropertyDefinition(PROPERTY_A_ARG_TYPE_RcsID, DataType.I4, false);
        _service.addProperty(_A_ARG_TYPE_RcsID);

        PropertyDefinition _A_ARG_TYPE_ConnectionManager = new PropertyDefinition(PROPERTY_A_ARG_TYPE_ConnectionManager, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_ConnectionManager);

        PropertyDefinition _A_ARG_TYPE_ProtocolInfo = new PropertyDefinition(PROPERTY_A_ARG_TYPE_ProtocolInfo, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_ProtocolInfo);

        PropertyDefinition _SourceProtocolInfo = new PropertyDefinition(PROPERTY_SourceProtocolInfo, DataType.STRING, true);
        _service.addProperty(_SourceProtocolInfo);

        PropertyDefinition _A_ARG_TYPE_ConnectionID = new PropertyDefinition(PROPERTY_A_ARG_TYPE_ConnectionID, DataType.I4, false);
        _service.addProperty(_A_ARG_TYPE_ConnectionID);

        PropertyDefinition _A_ARG_TYPE_ConnectionStatus = new PropertyDefinition(PROPERTY_A_ARG_TYPE_ConnectionStatus, DataType.STRING, false);
        AllowedValueList _A_ARG_TYPE_ConnectionStatus_list = new AllowedValueList(DataType.STRING);
        _A_ARG_TYPE_ConnectionStatus_list.appendAllowedValue("OK");
        _A_ARG_TYPE_ConnectionStatus_list.appendAllowedValue("ContentFormatMismatch");
        _A_ARG_TYPE_ConnectionStatus_list.appendAllowedValue("InsufficientBandwidth");
        _A_ARG_TYPE_ConnectionStatus_list.appendAllowedValue("UnreliableChannel");
        _A_ARG_TYPE_ConnectionStatus_list.appendAllowedValue("Unknown");
        _A_ARG_TYPE_ConnectionStatus.setAllowedValueList(_A_ARG_TYPE_ConnectionStatus_list);
        _service.addProperty(_A_ARG_TYPE_ConnectionStatus);

        PropertyDefinition _CurrentConnectionIDs = new PropertyDefinition(PROPERTY_CurrentConnectionIDs, DataType.STRING, true);
        _service.addProperty(_CurrentConnectionIDs);

        PropertyDefinition _A_ARG_TYPE_AVTransportID = new PropertyDefinition(PROPERTY_A_ARG_TYPE_AVTransportID, DataType.I4, false);
        _service.addProperty(_A_ARG_TYPE_AVTransportID);

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

        if (info.getAction().getName().equals(ACTION_GetCurrentConnectionInfo)) {
            return handle_GetCurrentConnectionInfo(info);
        }

        if (info.getAction().getName().equals(ACTION_GetProtocolInfo)) {
            return handle_GetProtocolInfo(info);
        }

        if (info.getAction().getName().equals(ACTION_GetCurrentConnectionIDs)) {
            return handle_GetCurrentConnectionIDs(info);
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

    public void setSinkProtocolInfo(String theSinkProtocolInfo) {
         _service.setPropertyValue(PROPERTY_SinkProtocolInfo, theSinkProtocolInfo);
    }

    public void setSourceProtocolInfo(String theSourceProtocolInfo) {
         _service.setPropertyValue(PROPERTY_SourceProtocolInfo, theSourceProtocolInfo);
    }

    public void setCurrentConnectionIDs(String theCurrentConnectionIDs) {
         _service.setPropertyValue(PROPERTY_CurrentConnectionIDs, theCurrentConnectionIDs);
    }

}
