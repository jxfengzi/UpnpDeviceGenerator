/* Automatic generated by DeviceToClazz */

package upnps.api.host.device.mitv;

import android.util.Log;

import miui.upnp.typedef.datatype.DataType;
import miui.upnp.typedef.device.Action;
import miui.upnp.typedef.device.Device;
import miui.upnp.typedef.device.urn.ServiceType;
import miui.upnp.typedef.error.UpnpError;
import miui.upnp.typedef.device.Argument;
import miui.upnp.typedef.device.Service;
import miui.upnp.typedef.device.invocation.ActionInfo;
import miui.upnp.typedef.device.invocation.EventInfo;
import miui.upnp.typedef.device.invocation.EventInfoCreator;
import miui.upnp.typedef.exception.UpnpException;

import miui.upnp.typedef.property.AllowedValueList;
import miui.upnp.typedef.property.AllowedValueRange;
import miui.upnp.typedef.property.PropertyDefinition;
import miui.upnp.manager.UpnpManager;
import miui.upnp.manager.host.ServiceHandler;

public class BleOperation extends ServiceHandler {
    private static final String TAG = "BleOperation";
    private static final ServiceType SERVICE_TYPE =  new ServiceType("BleOperation", "0.1");

    //-------------------------------------------------------
    // Action Names (10)
    //-------------------------------------------------------
    public static final String ACTION_BW_EnableCharacteristicNotification = "BW_EnableCharacteristicNotification";
    public static final String _BW_EnableCharacteristicNotification_ARG_BleDevice = "BleDevice";
    public static final String _BW_EnableCharacteristicNotification_ARG_BleService = "BleService";
    public static final String _BW_EnableCharacteristicNotification_ARG_BleCharacteristic = "BleCharacteristic";
    public static final String ACTION_BW_GetConnectedDevices = "BW_GetConnectedDevices";
    public static final String _BW_GetConnectedDevices_ARG_RetConnectedDevices = "RetConnectedDevices";
    public static final String ACTION_BW_StartScan = "BW_StartScan";
    public static final String _BW_StartScan_ARG_Filter = "Filter";
    public static final String ACTION_BW_KeepAlive = "BW_KeepAlive";
    public static final String _BW_KeepAlive_ARG_BleDevice = "BleDevice";
    public static final String _BW_KeepAlive_ARG_IsAlive = "IsAlive";
    public static final String ACTION_BW_WriteCharacteristic = "BW_WriteCharacteristic";
    public static final String _BW_WriteCharacteristic_ARG_BleDevice = "BleDevice";
    public static final String _BW_WriteCharacteristic_ARG_BleService = "BleService";
    public static final String _BW_WriteCharacteristic_ARG_BleCharacteristic = "BleCharacteristic";
    public static final String _BW_WriteCharacteristic_ARG_Data = "Data";
    public static final String ACTION_BW_DisableCharacteristicNotification = "BW_DisableCharacteristicNotification";
    public static final String _BW_DisableCharacteristicNotification_ARG_BleDevice = "BleDevice";
    public static final String _BW_DisableCharacteristicNotification_ARG_BleService = "BleService";
    public static final String _BW_DisableCharacteristicNotification_ARG_BleCharacteristic = "BleCharacteristic";
    public static final String ACTION_BW_ReadCharacteristic = "BW_ReadCharacteristic";
    public static final String _BW_ReadCharacteristic_ARG_BleDevice = "BleDevice";
    public static final String _BW_ReadCharacteristic_ARG_BleService = "BleService";
    public static final String _BW_ReadCharacteristic_ARG_BleCharacteristic = "BleCharacteristic";
    public static final String ACTION_BW_DiscoverServices = "BW_DiscoverServices";
    public static final String _BW_DiscoverServices_ARG_BleDevice = "BleDevice";
    public static final String ACTION_BW_GetAvaliableDevices = "BW_GetAvaliableDevices";
    public static final String _BW_GetAvaliableDevices_ARG_RetAvaliableDevices = "RetAvaliableDevices";
    public static final String ACTION_BW_StopScan = "BW_StopScan";

    //-------------------------------------------------------
    // Property Name (13)
    //-------------------------------------------------------
    public static final String PROPERTY_A_ARG_TYPE_BleService = "A_ARG_TYPE_BleService";
    public static final String PROPERTY_A_ARG_TYPE_IsAlive = "A_ARG_TYPE_IsAlive";
    public static final String PROPERTY_A_ARG_TYPE_Filter = "A_ARG_TYPE_Filter";
    public static final String PROPERTY_BW_CharacteristicReaded = "BW_CharacteristicReaded";
    public static final String PROPERTY_A_ARG_TYPE_Data = "A_ARG_TYPE_Data";
    public static final String PROPERTY_BW_ScanResult = "BW_ScanResult";
    public static final String PROPERTY_BW_DiscoveredServices = "BW_DiscoveredServices";
    public static final String PROPERTY_A_ARG_TYPE_BleCharacteristic = "A_ARG_TYPE_BleCharacteristic";
    public static final String PROPERTY_BW_CharacteristicWrited = "BW_CharacteristicWrited";
    public static final String PROPERTY_A_ARG_TYPE_BleDevices = "A_ARG_TYPE_BleDevices";
    public static final String PROPERTY_BW_ConnectionState = "BW_ConnectionState";
    public static final String PROPERTY_A_ARG_TYPE_BleDevice = "A_ARG_TYPE_BleDevice";
    public static final String PROPERTY_BW_CharacteristicNotified = "BW_CharacteristicNotified";

    //-------------------------------------------------------
    // Property value defined (0)
    //-------------------------------------------------------

    //-------------------------------------------------------
    // Action Result (10)
    //-------------------------------------------------------

    // BW_EnableCharacteristicNotification has no Result

    public class BW_GetConnectedDevices_Result {
        public String theRetConnectedDevices;
    }

    // BW_StartScan has no Result

    // BW_KeepAlive has no Result

    // BW_WriteCharacteristic has no Result

    // BW_DisableCharacteristicNotification has no Result

    // BW_ReadCharacteristic has no Result

    // BW_DiscoverServices has no Result

    public class BW_GetAvaliableDevices_Result {
        public String theRetAvaliableDevices;
    }

    // BW_StopScan has no Result


    //-------------------------------------------------------
    // Action Handler (10)
    //-------------------------------------------------------

    public interface Handler {
        UpnpError onBW_EnableCharacteristicNotification(String theBleDevice, String theBleService, String theBleCharacteristic);
        UpnpError onBW_GetConnectedDevices(BW_GetConnectedDevices_Result result);
        UpnpError onBW_StartScan(String theFilter);
        UpnpError onBW_KeepAlive(String theBleDevice, Boolean theIsAlive);
        UpnpError onBW_WriteCharacteristic(String theBleDevice, String theBleService, String theBleCharacteristic, String theData);
        UpnpError onBW_DisableCharacteristicNotification(String theBleDevice, String theBleService, String theBleCharacteristic);
        UpnpError onBW_ReadCharacteristic(String theBleDevice, String theBleService, String theBleCharacteristic);
        UpnpError onBW_DiscoverServices(String theBleDevice);
        UpnpError onBW_GetAvaliableDevices(BW_GetAvaliableDevices_Result result);
        UpnpError onBW_StopScan();
    }

    private UpnpError handle_BW_EnableCharacteristicNotification(ActionInfo info) {
        String theBleDevice = (String)info.getArgumentValue(_BW_EnableCharacteristicNotification_ARG_BleDevice);
        String theBleService = (String)info.getArgumentValue(_BW_EnableCharacteristicNotification_ARG_BleService);
        String theBleCharacteristic = (String)info.getArgumentValue(_BW_EnableCharacteristicNotification_ARG_BleCharacteristic);

        UpnpError error = _handler.onBW_EnableCharacteristicNotification(theBleDevice, theBleService, theBleCharacteristic);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_BW_GetConnectedDevices(ActionInfo info) {
        BW_GetConnectedDevices_Result result = new BW_GetConnectedDevices_Result();

        UpnpError error = _handler.onBW_GetConnectedDevices(result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_BW_GetConnectedDevices_ARG_RetConnectedDevices, result.theRetConnectedDevices, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_BW_StartScan(ActionInfo info) {
        String theFilter = (String)info.getArgumentValue(_BW_StartScan_ARG_Filter);

        UpnpError error = _handler.onBW_StartScan(theFilter);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_BW_KeepAlive(ActionInfo info) {
        String theBleDevice = (String)info.getArgumentValue(_BW_KeepAlive_ARG_BleDevice);
        Boolean theIsAlive = (Boolean)info.getArgumentValue(_BW_KeepAlive_ARG_IsAlive);

        UpnpError error = _handler.onBW_KeepAlive(theBleDevice, theIsAlive);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_BW_WriteCharacteristic(ActionInfo info) {
        String theBleDevice = (String)info.getArgumentValue(_BW_WriteCharacteristic_ARG_BleDevice);
        String theBleService = (String)info.getArgumentValue(_BW_WriteCharacteristic_ARG_BleService);
        String theBleCharacteristic = (String)info.getArgumentValue(_BW_WriteCharacteristic_ARG_BleCharacteristic);
        String theData = (String)info.getArgumentValue(_BW_WriteCharacteristic_ARG_Data);

        UpnpError error = _handler.onBW_WriteCharacteristic(theBleDevice, theBleService, theBleCharacteristic, theData);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_BW_DisableCharacteristicNotification(ActionInfo info) {
        String theBleDevice = (String)info.getArgumentValue(_BW_DisableCharacteristicNotification_ARG_BleDevice);
        String theBleService = (String)info.getArgumentValue(_BW_DisableCharacteristicNotification_ARG_BleService);
        String theBleCharacteristic = (String)info.getArgumentValue(_BW_DisableCharacteristicNotification_ARG_BleCharacteristic);

        UpnpError error = _handler.onBW_DisableCharacteristicNotification(theBleDevice, theBleService, theBleCharacteristic);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_BW_ReadCharacteristic(ActionInfo info) {
        String theBleDevice = (String)info.getArgumentValue(_BW_ReadCharacteristic_ARG_BleDevice);
        String theBleService = (String)info.getArgumentValue(_BW_ReadCharacteristic_ARG_BleService);
        String theBleCharacteristic = (String)info.getArgumentValue(_BW_ReadCharacteristic_ARG_BleCharacteristic);

        UpnpError error = _handler.onBW_ReadCharacteristic(theBleDevice, theBleService, theBleCharacteristic);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_BW_DiscoverServices(ActionInfo info) {
        String theBleDevice = (String)info.getArgumentValue(_BW_DiscoverServices_ARG_BleDevice);

        UpnpError error = _handler.onBW_DiscoverServices(theBleDevice);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_BW_GetAvaliableDevices(ActionInfo info) {
        BW_GetAvaliableDevices_Result result = new BW_GetAvaliableDevices_Result();

        UpnpError error = _handler.onBW_GetAvaliableDevices(result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_BW_GetAvaliableDevices_ARG_RetAvaliableDevices, result.theRetAvaliableDevices, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_BW_StopScan(ActionInfo info) {

        UpnpError error = _handler.onBW_StopScan();
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

    public BleOperation(Device device) throws UpnpException {
        _service = new Service(SERVICE_TYPE);
        _service.setServiceId(toServiceId(SERVICE_TYPE));
        _service.setScpdUrl(toScpdUrl(device.getDeviceId(), SERVICE_TYPE));
        _service.setControlUrl(toCtrlUrl(device.getDeviceId(), SERVICE_TYPE));
        _service.setEventSubUrl(toEventUrl(device.getDeviceId(), SERVICE_TYPE));

        Action _BW_EnableCharacteristicNotification = new Action(ACTION_BW_EnableCharacteristicNotification);
        _BW_EnableCharacteristicNotification.addArgument(new Argument(_BW_EnableCharacteristicNotification_ARG_BleDevice, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleDevice));
        _BW_EnableCharacteristicNotification.addArgument(new Argument(_BW_EnableCharacteristicNotification_ARG_BleService, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleService));
        _BW_EnableCharacteristicNotification.addArgument(new Argument(_BW_EnableCharacteristicNotification_ARG_BleCharacteristic, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleCharacteristic));
        _service.addAction(_BW_EnableCharacteristicNotification);

        Action _BW_GetConnectedDevices = new Action(ACTION_BW_GetConnectedDevices);
        _BW_GetConnectedDevices.addArgument(new Argument(_BW_GetConnectedDevices_ARG_RetConnectedDevices, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_BleDevices));
        _service.addAction(_BW_GetConnectedDevices);

        Action _BW_StartScan = new Action(ACTION_BW_StartScan);
        _BW_StartScan.addArgument(new Argument(_BW_StartScan_ARG_Filter, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_Filter));
        _service.addAction(_BW_StartScan);

        Action _BW_KeepAlive = new Action(ACTION_BW_KeepAlive);
        _BW_KeepAlive.addArgument(new Argument(_BW_KeepAlive_ARG_BleDevice, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleDevice));
        _BW_KeepAlive.addArgument(new Argument(_BW_KeepAlive_ARG_IsAlive, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_IsAlive));
        _service.addAction(_BW_KeepAlive);

        Action _BW_WriteCharacteristic = new Action(ACTION_BW_WriteCharacteristic);
        _BW_WriteCharacteristic.addArgument(new Argument(_BW_WriteCharacteristic_ARG_BleDevice, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleDevice));
        _BW_WriteCharacteristic.addArgument(new Argument(_BW_WriteCharacteristic_ARG_BleService, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleService));
        _BW_WriteCharacteristic.addArgument(new Argument(_BW_WriteCharacteristic_ARG_BleCharacteristic, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleCharacteristic));
        _BW_WriteCharacteristic.addArgument(new Argument(_BW_WriteCharacteristic_ARG_Data, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_Data));
        _service.addAction(_BW_WriteCharacteristic);

        Action _BW_DisableCharacteristicNotification = new Action(ACTION_BW_DisableCharacteristicNotification);
        _BW_DisableCharacteristicNotification.addArgument(new Argument(_BW_DisableCharacteristicNotification_ARG_BleDevice, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleDevice));
        _BW_DisableCharacteristicNotification.addArgument(new Argument(_BW_DisableCharacteristicNotification_ARG_BleService, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleService));
        _BW_DisableCharacteristicNotification.addArgument(new Argument(_BW_DisableCharacteristicNotification_ARG_BleCharacteristic, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleCharacteristic));
        _service.addAction(_BW_DisableCharacteristicNotification);

        Action _BW_ReadCharacteristic = new Action(ACTION_BW_ReadCharacteristic);
        _BW_ReadCharacteristic.addArgument(new Argument(_BW_ReadCharacteristic_ARG_BleDevice, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleDevice));
        _BW_ReadCharacteristic.addArgument(new Argument(_BW_ReadCharacteristic_ARG_BleService, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleService));
        _BW_ReadCharacteristic.addArgument(new Argument(_BW_ReadCharacteristic_ARG_BleCharacteristic, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleCharacteristic));
        _service.addAction(_BW_ReadCharacteristic);

        Action _BW_DiscoverServices = new Action(ACTION_BW_DiscoverServices);
        _BW_DiscoverServices.addArgument(new Argument(_BW_DiscoverServices_ARG_BleDevice, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BleDevice));
        _service.addAction(_BW_DiscoverServices);

        Action _BW_GetAvaliableDevices = new Action(ACTION_BW_GetAvaliableDevices);
        _BW_GetAvaliableDevices.addArgument(new Argument(_BW_GetAvaliableDevices_ARG_RetAvaliableDevices, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_BleDevices));
        _service.addAction(_BW_GetAvaliableDevices);

        Action _BW_StopScan = new Action(ACTION_BW_StopScan);
        _service.addAction(_BW_StopScan);

        PropertyDefinition _A_ARG_TYPE_BleService = new PropertyDefinition(PROPERTY_A_ARG_TYPE_BleService, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_BleService);

        PropertyDefinition _A_ARG_TYPE_IsAlive = new PropertyDefinition(PROPERTY_A_ARG_TYPE_IsAlive, DataType.BOOLEAN, false);
        _service.addProperty(_A_ARG_TYPE_IsAlive);

        PropertyDefinition _A_ARG_TYPE_Filter = new PropertyDefinition(PROPERTY_A_ARG_TYPE_Filter, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_Filter);

        PropertyDefinition _BW_CharacteristicReaded = new PropertyDefinition(PROPERTY_BW_CharacteristicReaded, DataType.STRING, true);
        _service.addProperty(_BW_CharacteristicReaded);

        PropertyDefinition _A_ARG_TYPE_Data = new PropertyDefinition(PROPERTY_A_ARG_TYPE_Data, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_Data);

        PropertyDefinition _BW_ScanResult = new PropertyDefinition(PROPERTY_BW_ScanResult, DataType.STRING, true);
        _service.addProperty(_BW_ScanResult);

        PropertyDefinition _BW_DiscoveredServices = new PropertyDefinition(PROPERTY_BW_DiscoveredServices, DataType.STRING, true);
        _service.addProperty(_BW_DiscoveredServices);

        PropertyDefinition _A_ARG_TYPE_BleCharacteristic = new PropertyDefinition(PROPERTY_A_ARG_TYPE_BleCharacteristic, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_BleCharacteristic);

        PropertyDefinition _BW_CharacteristicWrited = new PropertyDefinition(PROPERTY_BW_CharacteristicWrited, DataType.STRING, true);
        _service.addProperty(_BW_CharacteristicWrited);

        PropertyDefinition _A_ARG_TYPE_BleDevices = new PropertyDefinition(PROPERTY_A_ARG_TYPE_BleDevices, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_BleDevices);

        PropertyDefinition _BW_ConnectionState = new PropertyDefinition(PROPERTY_BW_ConnectionState, DataType.STRING, true);
        _service.addProperty(_BW_ConnectionState);

        PropertyDefinition _A_ARG_TYPE_BleDevice = new PropertyDefinition(PROPERTY_A_ARG_TYPE_BleDevice, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_BleDevice);

        PropertyDefinition _BW_CharacteristicNotified = new PropertyDefinition(PROPERTY_BW_CharacteristicNotified, DataType.STRING, true);
        _service.addProperty(_BW_CharacteristicNotified);

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

        if (info.getAction().getName().equals(ACTION_BW_EnableCharacteristicNotification)) {
            return handle_BW_EnableCharacteristicNotification(info);
        }

        if (info.getAction().getName().equals(ACTION_BW_GetConnectedDevices)) {
            return handle_BW_GetConnectedDevices(info);
        }

        if (info.getAction().getName().equals(ACTION_BW_StartScan)) {
            return handle_BW_StartScan(info);
        }

        if (info.getAction().getName().equals(ACTION_BW_KeepAlive)) {
            return handle_BW_KeepAlive(info);
        }

        if (info.getAction().getName().equals(ACTION_BW_WriteCharacteristic)) {
            return handle_BW_WriteCharacteristic(info);
        }

        if (info.getAction().getName().equals(ACTION_BW_DisableCharacteristicNotification)) {
            return handle_BW_DisableCharacteristicNotification(info);
        }

        if (info.getAction().getName().equals(ACTION_BW_ReadCharacteristic)) {
            return handle_BW_ReadCharacteristic(info);
        }

        if (info.getAction().getName().equals(ACTION_BW_DiscoverServices)) {
            return handle_BW_DiscoverServices(info);
        }

        if (info.getAction().getName().equals(ACTION_BW_GetAvaliableDevices)) {
            return handle_BW_GetAvaliableDevices(info);
        }

        if (info.getAction().getName().equals(ACTION_BW_StopScan)) {
            return handle_BW_StopScan(info);
        }

        return UpnpError.UPNP_ACTION_NOT_IMPLEMENTED;
    }

    public void sendEvents() {
        if (! _service.isPropertyChanged()) {
            return;
        }

        EventInfo info = EventInfoCreator.create(_service);

        try {
            UpnpManager.getInstance().getHost().sendEvents(info);
        } catch (UpnpException e) {
            e.printStackTrace();
        }

        _service.cleanPropertyState();
    }

    public void setA_ARG_TYPE_BleService(String theA_ARG_TYPE_BleService) {
         _service.setPropertyValue(PROPERTY_A_ARG_TYPE_BleService, theA_ARG_TYPE_BleService);
    }

    public String getA_ARG_TYPE_BleService() {
         return (String)_service.getPropertyValue(PROPERTY_A_ARG_TYPE_BleService);
    }

    public void setA_ARG_TYPE_IsAlive(Boolean theA_ARG_TYPE_IsAlive) {
         _service.setPropertyValue(PROPERTY_A_ARG_TYPE_IsAlive, theA_ARG_TYPE_IsAlive);
    }

    public Boolean getA_ARG_TYPE_IsAlive() {
         return (Boolean)_service.getPropertyValue(PROPERTY_A_ARG_TYPE_IsAlive);
    }

    public void setA_ARG_TYPE_Filter(String theA_ARG_TYPE_Filter) {
         _service.setPropertyValue(PROPERTY_A_ARG_TYPE_Filter, theA_ARG_TYPE_Filter);
    }

    public String getA_ARG_TYPE_Filter() {
         return (String)_service.getPropertyValue(PROPERTY_A_ARG_TYPE_Filter);
    }

    public void setBW_CharacteristicReaded(String theBW_CharacteristicReaded) {
         _service.setPropertyValue(PROPERTY_BW_CharacteristicReaded, theBW_CharacteristicReaded);
    }

    public String getBW_CharacteristicReaded() {
         return (String)_service.getPropertyValue(PROPERTY_BW_CharacteristicReaded);
    }

    public void setA_ARG_TYPE_Data(String theA_ARG_TYPE_Data) {
         _service.setPropertyValue(PROPERTY_A_ARG_TYPE_Data, theA_ARG_TYPE_Data);
    }

    public String getA_ARG_TYPE_Data() {
         return (String)_service.getPropertyValue(PROPERTY_A_ARG_TYPE_Data);
    }

    public void setBW_ScanResult(String theBW_ScanResult) {
         _service.setPropertyValue(PROPERTY_BW_ScanResult, theBW_ScanResult);
    }

    public String getBW_ScanResult() {
         return (String)_service.getPropertyValue(PROPERTY_BW_ScanResult);
    }

    public void setBW_DiscoveredServices(String theBW_DiscoveredServices) {
         _service.setPropertyValue(PROPERTY_BW_DiscoveredServices, theBW_DiscoveredServices);
    }

    public String getBW_DiscoveredServices() {
         return (String)_service.getPropertyValue(PROPERTY_BW_DiscoveredServices);
    }

    public void setA_ARG_TYPE_BleCharacteristic(String theA_ARG_TYPE_BleCharacteristic) {
         _service.setPropertyValue(PROPERTY_A_ARG_TYPE_BleCharacteristic, theA_ARG_TYPE_BleCharacteristic);
    }

    public String getA_ARG_TYPE_BleCharacteristic() {
         return (String)_service.getPropertyValue(PROPERTY_A_ARG_TYPE_BleCharacteristic);
    }

    public void setBW_CharacteristicWrited(String theBW_CharacteristicWrited) {
         _service.setPropertyValue(PROPERTY_BW_CharacteristicWrited, theBW_CharacteristicWrited);
    }

    public String getBW_CharacteristicWrited() {
         return (String)_service.getPropertyValue(PROPERTY_BW_CharacteristicWrited);
    }

    public void setA_ARG_TYPE_BleDevices(String theA_ARG_TYPE_BleDevices) {
         _service.setPropertyValue(PROPERTY_A_ARG_TYPE_BleDevices, theA_ARG_TYPE_BleDevices);
    }

    public String getA_ARG_TYPE_BleDevices() {
         return (String)_service.getPropertyValue(PROPERTY_A_ARG_TYPE_BleDevices);
    }

    public void setBW_ConnectionState(String theBW_ConnectionState) {
         _service.setPropertyValue(PROPERTY_BW_ConnectionState, theBW_ConnectionState);
    }

    public String getBW_ConnectionState() {
         return (String)_service.getPropertyValue(PROPERTY_BW_ConnectionState);
    }

    public void setA_ARG_TYPE_BleDevice(String theA_ARG_TYPE_BleDevice) {
         _service.setPropertyValue(PROPERTY_A_ARG_TYPE_BleDevice, theA_ARG_TYPE_BleDevice);
    }

    public String getA_ARG_TYPE_BleDevice() {
         return (String)_service.getPropertyValue(PROPERTY_A_ARG_TYPE_BleDevice);
    }

    public void setBW_CharacteristicNotified(String theBW_CharacteristicNotified) {
         _service.setPropertyValue(PROPERTY_BW_CharacteristicNotified, theBW_CharacteristicNotified);
    }

    public String getBW_CharacteristicNotified() {
         return (String)_service.getPropertyValue(PROPERTY_BW_CharacteristicNotified);
    }

}