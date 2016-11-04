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

public class MiTVSettings extends AbstractService {

    private static final String TAG = "MiTVSettings";
    private static final ServiceType SERVICE_TYPE =  new ServiceType("MiTVSettings", "0.1");

    public MiTVSettings(Service service) {
        super(service);
    }

    //-------------------------------------------------------
    // Action Names (18)
    //-------------------------------------------------------
    public static final String ACTION_SetPowerState = "SetPowerState";
    public static final String _SetPowerState_ARG_PowerState = "PowerState";
    public static final String ACTION_SetInputSource = "SetInputSource";
    public static final String _SetInputSource_ARG_inInputSource = "inInputSource";
    public static final String ACTION_CancelNotification = "CancelNotification";
    public static final String _CancelNotification_ARG_InNotification = "InNotification";
    public static final String ACTION_SetNetwork = "SetNetwork";
    public static final String _SetNetwork_ARG_InNetworking = "InNetworking";
    public static final String _SetNetwork_ARG_InPassword = "InPassword";
    public static final String ACTION_SetResolution = "SetResolution";
    public static final String _SetResolution_ARG_InResolution = "InResolution";
    public static final String ACTION_GetVersion = "GetVersion";
    public static final String _GetVersion_ARG_RetVersion = "RetVersion";
    public static final String ACTION_GetNetworkSpeed = "GetNetworkSpeed";
    public static final String _GetNetworkSpeed_ARG_RetSpeed = "RetSpeed";
    public static final String ACTION_GetResolution = "GetResolution";
    public static final String _GetResolution_ARG_RetResolution = "RetResolution";
    public static final String ACTION_SendNotification = "SendNotification";
    public static final String _SendNotification_ARG_InNotification = "InNotification";
    public static final String ACTION_SetVolume = "SetVolume";
    public static final String _SetVolume_ARG_InVolume = "InVolume";
    public static final String ACTION_GetNetworking = "GetNetworking";
    public static final String _GetNetworking_ARG_RetNetworking = "RetNetworking";
    public static final String ACTION_GetDeviceName = "GetDeviceName";
    public static final String _GetDeviceName_ARG_RetDeviceName = "RetDeviceName";
    public static final String ACTION_GetVolume = "GetVolume";
    public static final String _GetVolume_ARG_RetVolume = "RetVolume";
    public static final String ACTION_UpgradeSystem = "UpgradeSystem";
    public static final String _UpgradeSystem_ARG_InVersion = "InVersion";
    public static final String ACTION_GetPowerState = "GetPowerState";
    public static final String _GetPowerState_ARG_PowerState = "PowerState";
    public static final String ACTION_GetScannedWifiList = "GetScannedWifiList";
    public static final String _GetScannedWifiList_ARG_RetScannedWifiList = "RetScannedWifiList";
    public static final String ACTION_GetInputSource = "GetInputSource";
    public static final String _GetInputSource_ARG_RetInputSource = "RetInputSource";
    public static final String ACTION_SetDeviceName = "SetDeviceName";
    public static final String _SetDeviceName_ARG_InDeviceName = "InDeviceName";

    //-------------------------------------------------------
    // Property Name (11)
    //-------------------------------------------------------
    public static final String PROPERTY_Networking = "Networking";
    public static final String PROPERTY_DeviceName = "DeviceName";
    public static final String PROPERTY_Resolution = "Resolution";
    public static final String PROPERTY_Volume = "Volume";
    public static final String PROPERTY_Power = "Power";
    public static final String PROPERTY_Password = "Password";
    public static final String PROPERTY_InputSource = "InputSource";
    public static final String PROPERTY_NetworkSpeed = "NetworkSpeed";
    public static final String PROPERTY_Version = "Version";
    public static final String PROPERTY_Notification = "Notification";
    public static final String PROPERTY_ScannedWifiList = "ScannedWifiList";

    //-------------------------------------------------------
    // Property value defined (2)
    //-------------------------------------------------------

    public enum Resolution {
        UNDEFINED,
        V_E4K2K30HZ,
        V_E4K2KP60HZ,
        V_E4K2KP50HZ,
        V_E1080P60HZ,
        V_E1080P50HZ,
        V_E720P60HZ,
        V_E720P50HZ;

        private static final String CONST_UNDEFINED = "UNDEFINED";
        private static final String CONST_V_E4K2K30HZ = "E4K2K30HZ";
        private static final String CONST_V_E4K2KP60HZ = "E4K2KP60HZ";
        private static final String CONST_V_E4K2KP50HZ = "E4K2KP50HZ";
        private static final String CONST_V_E1080P60HZ = "E1080P60HZ";
        private static final String CONST_V_E1080P50HZ = "E1080P50HZ";
        private static final String CONST_V_E720P60HZ = "E720P60HZ";
        private static final String CONST_V_E720P50HZ = "E720P50HZ";

        public static Resolution retrieveType(String value) {
            if (value.equals(CONST_UNDEFINED)) {
                return UNDEFINED;
            }

            if (value.equals(CONST_V_E4K2K30HZ)) {
                return V_E4K2K30HZ;
            }

            if (value.equals(CONST_V_E4K2KP60HZ)) {
                return V_E4K2KP60HZ;
            }

            if (value.equals(CONST_V_E4K2KP50HZ)) {
                return V_E4K2KP50HZ;
            }

            if (value.equals(CONST_V_E1080P60HZ)) {
                return V_E1080P60HZ;
            }

            if (value.equals(CONST_V_E1080P50HZ)) {
                return V_E1080P50HZ;
            }

            if (value.equals(CONST_V_E720P60HZ)) {
                return V_E720P60HZ;
            }

            if (value.equals(CONST_V_E720P50HZ)) {
                return V_E720P50HZ;
            }

            return UNDEFINED;
        }

        public String getValue() {
            String value = null;
            switch (this) {
                case V_E4K2K30HZ:
                    value = CONST_V_E4K2K30HZ;
                    break;

                case V_E4K2KP60HZ:
                    value = CONST_V_E4K2KP60HZ;
                    break;

                case V_E4K2KP50HZ:
                    value = CONST_V_E4K2KP50HZ;
                    break;

                case V_E1080P60HZ:
                    value = CONST_V_E1080P60HZ;
                    break;

                case V_E1080P50HZ:
                    value = CONST_V_E1080P50HZ;
                    break;

                case V_E720P60HZ:
                    value = CONST_V_E720P60HZ;
                    break;

                case V_E720P50HZ:
                    value = CONST_V_E720P50HZ;
                    break;

                default:
                    break;
            }

            return value;
        }
    }

    public enum Power {
        UNDEFINED,
        V_SLEEP,
        V_RUNNING;

        private static final String CONST_UNDEFINED = "UNDEFINED";
        private static final String CONST_V_SLEEP = "SLEEP";
        private static final String CONST_V_RUNNING = "RUNNING";

        public static Power retrieveType(String value) {
            if (value.equals(CONST_UNDEFINED)) {
                return UNDEFINED;
            }

            if (value.equals(CONST_V_SLEEP)) {
                return V_SLEEP;
            }

            if (value.equals(CONST_V_RUNNING)) {
                return V_RUNNING;
            }

            return UNDEFINED;
        }

        public String getValue() {
            String value = null;
            switch (this) {
                case V_SLEEP:
                    value = CONST_V_SLEEP;
                    break;

                case V_RUNNING:
                    value = CONST_V_RUNNING;
                    break;

                default:
                    break;
            }

            return value;
        }
    }

    //-------------------------------------------------------
    // ActionList (18)
    //-------------------------------------------------------

    public interface SetPowerState_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void SetPowerState(Power PowerState, final SetPowerState_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_SetPowerState);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_SetPowerState_ARG_PowerState, PowerState.getValue(), Argument.Direction.IN)) {
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

    public interface SetInputSource_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void SetInputSource(String inInputSource, final SetInputSource_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_SetInputSource);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_SetInputSource_ARG_inInputSource, inInputSource, Argument.Direction.IN)) {
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

    public interface CancelNotification_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void CancelNotification(String InNotification, final CancelNotification_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_CancelNotification);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_CancelNotification_ARG_InNotification, InNotification, Argument.Direction.IN)) {
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

    public interface SetNetwork_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void SetNetwork(String InNetworking, String InPassword, final SetNetwork_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_SetNetwork);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_SetNetwork_ARG_InNetworking, InNetworking, Argument.Direction.IN)) {
            throw new UpnpException(UpnpError.INVALID_ARGUMENT);
        }

        if (!action.setArgumentValue(_SetNetwork_ARG_InPassword, InPassword, Argument.Direction.IN)) {
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

    public interface SetResolution_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void SetResolution(Resolution InResolution, final SetResolution_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_SetResolution);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_SetResolution_ARG_InResolution, InResolution.getValue(), Argument.Direction.IN)) {
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

    public interface GetVersion_CompletedHandler {
        void onSucceed(String theRetVersion);
        void onFailed(UpnpError error);
    }

    public void GetVersion(final GetVersion_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_GetVersion);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                do {
                    Property pRetVersion = invocation.getResult(_GetVersion_ARG_RetVersion);
                    if (pRetVersion == null) {
                        Log.d(TAG, String.format("%s not found", _GetVersion_ARG_RetVersion));
                        break;
                    }

                    String theRetVersion = (String) pRetVersion.getCurrentValue();

                    handler.onSucceed(theRetVersion);
                } while (false);
            }

            @Override
            public void onFailed(UpnpError error) {
                handler.onFailed(error);
            }
        });
    }

    public interface GetNetworkSpeed_CompletedHandler {
        void onSucceed(Long theRetSpeed);
        void onFailed(UpnpError error);
    }

    public void GetNetworkSpeed(final GetNetworkSpeed_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_GetNetworkSpeed);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                do {
                    Property pRetSpeed = invocation.getResult(_GetNetworkSpeed_ARG_RetSpeed);
                    if (pRetSpeed == null) {
                        Log.d(TAG, String.format("%s not found", _GetNetworkSpeed_ARG_RetSpeed));
                        break;
                    }

                    Long theRetSpeed = (Long) pRetSpeed.getCurrentValue();

                    handler.onSucceed(theRetSpeed);
                } while (false);
            }

            @Override
            public void onFailed(UpnpError error) {
                handler.onFailed(error);
            }
        });
    }

    public interface GetResolution_CompletedHandler {
        void onSucceed(Resolution theRetResolution);
        void onFailed(UpnpError error);
    }

    public void GetResolution(final GetResolution_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_GetResolution);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                do {
                    Property pRetResolution = invocation.getResult(_GetResolution_ARG_RetResolution);
                    if (pRetResolution == null) {
                        Log.d(TAG, String.format("%s not found", _GetResolution_ARG_RetResolution));
                        break;
                    }

                    Resolution theRetResolution = Resolution.retrieveType(pRetResolution.getCurrentValue().toString());

                    handler.onSucceed(theRetResolution);
                } while (false);
            }

            @Override
            public void onFailed(UpnpError error) {
                handler.onFailed(error);
            }
        });
    }

    public interface SendNotification_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void SendNotification(String InNotification, final SendNotification_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_SendNotification);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_SendNotification_ARG_InNotification, InNotification, Argument.Direction.IN)) {
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

    public interface SetVolume_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void SetVolume(Integer InVolume, final SetVolume_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_SetVolume);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_SetVolume_ARG_InVolume, InVolume, Argument.Direction.IN)) {
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

    public interface GetNetworking_CompletedHandler {
        void onSucceed(String theRetNetworking);
        void onFailed(UpnpError error);
    }

    public void GetNetworking(final GetNetworking_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_GetNetworking);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                do {
                    Property pRetNetworking = invocation.getResult(_GetNetworking_ARG_RetNetworking);
                    if (pRetNetworking == null) {
                        Log.d(TAG, String.format("%s not found", _GetNetworking_ARG_RetNetworking));
                        break;
                    }

                    String theRetNetworking = (String) pRetNetworking.getCurrentValue();

                    handler.onSucceed(theRetNetworking);
                } while (false);
            }

            @Override
            public void onFailed(UpnpError error) {
                handler.onFailed(error);
            }
        });
    }

    public interface GetDeviceName_CompletedHandler {
        void onSucceed(String theRetDeviceName);
        void onFailed(UpnpError error);
    }

    public void GetDeviceName(final GetDeviceName_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_GetDeviceName);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                do {
                    Property pRetDeviceName = invocation.getResult(_GetDeviceName_ARG_RetDeviceName);
                    if (pRetDeviceName == null) {
                        Log.d(TAG, String.format("%s not found", _GetDeviceName_ARG_RetDeviceName));
                        break;
                    }

                    String theRetDeviceName = (String) pRetDeviceName.getCurrentValue();

                    handler.onSucceed(theRetDeviceName);
                } while (false);
            }

            @Override
            public void onFailed(UpnpError error) {
                handler.onFailed(error);
            }
        });
    }

    public interface GetVolume_CompletedHandler {
        void onSucceed(Integer theRetVolume);
        void onFailed(UpnpError error);
    }

    public void GetVolume(final GetVolume_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_GetVolume);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                do {
                    Property pRetVolume = invocation.getResult(_GetVolume_ARG_RetVolume);
                    if (pRetVolume == null) {
                        Log.d(TAG, String.format("%s not found", _GetVolume_ARG_RetVolume));
                        break;
                    }

                    Integer theRetVolume = (Integer) pRetVolume.getCurrentValue();

                    handler.onSucceed(theRetVolume);
                } while (false);
            }

            @Override
            public void onFailed(UpnpError error) {
                handler.onFailed(error);
            }
        });
    }

    public interface UpgradeSystem_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void UpgradeSystem(String InVersion, final UpgradeSystem_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_UpgradeSystem);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_UpgradeSystem_ARG_InVersion, InVersion, Argument.Direction.IN)) {
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

    public interface GetPowerState_CompletedHandler {
        void onSucceed(Power thePowerState);
        void onFailed(UpnpError error);
    }

    public void GetPowerState(final GetPowerState_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_GetPowerState);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                do {
                    Property pPowerState = invocation.getResult(_GetPowerState_ARG_PowerState);
                    if (pPowerState == null) {
                        Log.d(TAG, String.format("%s not found", _GetPowerState_ARG_PowerState));
                        break;
                    }

                    Power thePowerState = Power.retrieveType(pPowerState.getCurrentValue().toString());

                    handler.onSucceed(thePowerState);
                } while (false);
            }

            @Override
            public void onFailed(UpnpError error) {
                handler.onFailed(error);
            }
        });
    }

    public interface GetScannedWifiList_CompletedHandler {
        void onSucceed(String theRetScannedWifiList);
        void onFailed(UpnpError error);
    }

    public void GetScannedWifiList(final GetScannedWifiList_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_GetScannedWifiList);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                do {
                    Property pRetScannedWifiList = invocation.getResult(_GetScannedWifiList_ARG_RetScannedWifiList);
                    if (pRetScannedWifiList == null) {
                        Log.d(TAG, String.format("%s not found", _GetScannedWifiList_ARG_RetScannedWifiList));
                        break;
                    }

                    String theRetScannedWifiList = (String) pRetScannedWifiList.getCurrentValue();

                    handler.onSucceed(theRetScannedWifiList);
                } while (false);
            }

            @Override
            public void onFailed(UpnpError error) {
                handler.onFailed(error);
            }
        });
    }

    public interface GetInputSource_CompletedHandler {
        void onSucceed(String theRetInputSource);
        void onFailed(UpnpError error);
    }

    public void GetInputSource(final GetInputSource_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_GetInputSource);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        UpnpManager.getInstance().getControlPoint().invoke(action, new UpnpInvokeCompletionHandler() {

            @Override
            public void onSucceed(ActionInfo invocation) {
                do {
                    Property pRetInputSource = invocation.getResult(_GetInputSource_ARG_RetInputSource);
                    if (pRetInputSource == null) {
                        Log.d(TAG, String.format("%s not found", _GetInputSource_ARG_RetInputSource));
                        break;
                    }

                    String theRetInputSource = (String) pRetInputSource.getCurrentValue();

                    handler.onSucceed(theRetInputSource);
                } while (false);
            }

            @Override
            public void onFailed(UpnpError error) {
                handler.onFailed(error);
            }
        });
    }

    public interface SetDeviceName_CompletedHandler {
        void onSucceed();
        void onFailed(UpnpError error);
    }

    public void SetDeviceName(String InDeviceName, final SetDeviceName_CompletedHandler handler) throws UpnpException {
        ActionInfo action = ActionInfoCreator.create(service, ACTION_SetDeviceName);
        if (action == null) {
            throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found");
        }

        if (!action.setArgumentValue(_SetDeviceName_ARG_InDeviceName, InDeviceName, Argument.Direction.IN)) {
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
        void onNetworkingChanged(String currentValue);
        void onDeviceNameChanged(String currentValue);
        void onPowerChanged(Power currentValue);
        void onInputSourceChanged(String currentValue);
        void onVersionChanged(String currentValue);
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
                        if (c.getName().equals(PROPERTY_Networking)) {
                            PropertyDefinition def = service.getPropertyDefinition(PROPERTY_Networking);
                            listener.onNetworkingChanged((String)def.getDataType().toObjectValue(c.getValue()));
                        }
                        if (c.getName().equals(PROPERTY_DeviceName)) {
                            PropertyDefinition def = service.getPropertyDefinition(PROPERTY_DeviceName);
                            listener.onDeviceNameChanged((String)def.getDataType().toObjectValue(c.getValue()));
                        }
                        if (c.getName().equals(PROPERTY_Power)) {
                            PropertyDefinition def = service.getPropertyDefinition(PROPERTY_Power);
                            listener.onPowerChanged(Power.retrieveType((String)def.getDataType().toObjectValue(c.getValue())));
                        }
                        if (c.getName().equals(PROPERTY_InputSource)) {
                            PropertyDefinition def = service.getPropertyDefinition(PROPERTY_InputSource);
                            listener.onInputSourceChanged((String)def.getDataType().toObjectValue(c.getValue()));
                        }
                        if (c.getName().equals(PROPERTY_Version)) {
                            PropertyDefinition def = service.getPropertyDefinition(PROPERTY_Version);
                            listener.onVersionChanged((String)def.getDataType().toObjectValue(c.getValue()));
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
