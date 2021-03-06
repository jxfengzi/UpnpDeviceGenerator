/* Automatic generated by DeviceToClazz */

package upnps.api.host.device.mediarenderer;

import android.util.Log;

import upnp.typedef.device.Argument;
import upnp.typedef.device.Service;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.device.invocation.EventInfo;
import upnp.typedef.device.invocation.EventInfoCreator;
import upnp.typedef.exception.UpnpException;
import upnp.typedef.property.Property;

import upnps.api.manager.UpnpManager;
import upnps.api.manager.host.ServiceActionHandler;

public class RenderingControl implements ServiceActionHandler {
    private static final String TAG = "RenderingControl";

    //-------------------------------------------------------
    // Action Names (6)
    //-------------------------------------------------------
    public static final String ACTION_ListPresets = "ListPresets";
    public static final String _ListPresets_ARG_InstanceID = "InstanceID";
    public static final String _ListPresets_ARG_CurrentPresetNameList = "CurrentPresetNameList";
    public static final String ACTION_SetVolume = "SetVolume";
    public static final String _SetVolume_ARG_InstanceID = "InstanceID";
    public static final String _SetVolume_ARG_Channel = "Channel";
    public static final String _SetVolume_ARG_DesiredVolume = "DesiredVolume";
    public static final String ACTION_SetMute = "SetMute";
    public static final String _SetMute_ARG_InstanceID = "InstanceID";
    public static final String _SetMute_ARG_Channel = "Channel";
    public static final String _SetMute_ARG_DesiredMute = "DesiredMute";
    public static final String ACTION_GetVolume = "GetVolume";
    public static final String _GetVolume_ARG_InstanceID = "InstanceID";
    public static final String _GetVolume_ARG_Channel = "Channel";
    public static final String _GetVolume_ARG_CurrentVolume = "CurrentVolume";
    public static final String ACTION_GetMute = "GetMute";
    public static final String _GetMute_ARG_InstanceID = "InstanceID";
    public static final String _GetMute_ARG_Channel = "Channel";
    public static final String _GetMute_ARG_CurrentMute = "CurrentMute";
    public static final String ACTION_SelectPreset = "SelectPreset";
    public static final String _SelectPreset_ARG_InstanceID = "InstanceID";
    public static final String _SelectPreset_ARG_PresetName = "PresetName";

    //-------------------------------------------------------
    // Property Name (7)
    //-------------------------------------------------------
    public static final String PROPERTY_PresetNameList = "PresetNameList";
    public static final String PROPERTY_Volume = "Volume";
    public static final String PROPERTY_Mute = "Mute";
    public static final String PROPERTY_A_ARG_TYPE_PresetName = "A_ARG_TYPE_PresetName";
    public static final String PROPERTY_A_ARG_TYPE_InstanceID = "A_ARG_TYPE_InstanceID";
    public static final String PROPERTY_A_ARG_TYPE_Channel = "A_ARG_TYPE_Channel";
    public static final String PROPERTY_LastChange = "LastChange";

    //-------------------------------------------------------
    // Property value defined (3)
    //-------------------------------------------------------

    public enum PresetNameList {
        UNDEFINED,
        V_FactoryDefaults;

        private static final String CONST_UNDEFINED = "UNDEFINED";
        private static final String CONST_V_FactoryDefaults = "FactoryDefaults";

        public static PresetNameList retrieveType(String value) {
            if (value.equals(CONST_UNDEFINED)) {
                return UNDEFINED;
            }

            if (value.equals(CONST_V_FactoryDefaults)) {
                return V_FactoryDefaults;
            }

            return UNDEFINED;
        }

        public String getValue() {
            String value = null;
            switch (this) {
                case V_FactoryDefaults:
                    value = CONST_V_FactoryDefaults;
                    break;

                default:
                    break;
            }

            return value;
        }
    }

    public enum A_ARG_TYPE_PresetName {
        UNDEFINED,
        V_FactoryDefaults;

        private static final String CONST_UNDEFINED = "UNDEFINED";
        private static final String CONST_V_FactoryDefaults = "FactoryDefaults";

        public static A_ARG_TYPE_PresetName retrieveType(String value) {
            if (value.equals(CONST_UNDEFINED)) {
                return UNDEFINED;
            }

            if (value.equals(CONST_V_FactoryDefaults)) {
                return V_FactoryDefaults;
            }

            return UNDEFINED;
        }

        public String getValue() {
            String value = null;
            switch (this) {
                case V_FactoryDefaults:
                    value = CONST_V_FactoryDefaults;
                    break;

                default:
                    break;
            }

            return value;
        }
    }

    public enum A_ARG_TYPE_Channel {
        UNDEFINED,
        V_Master;

        private static final String CONST_UNDEFINED = "UNDEFINED";
        private static final String CONST_V_Master = "Master";

        public static A_ARG_TYPE_Channel retrieveType(String value) {
            if (value.equals(CONST_UNDEFINED)) {
                return UNDEFINED;
            }

            if (value.equals(CONST_V_Master)) {
                return V_Master;
            }

            return UNDEFINED;
        }

        public String getValue() {
            String value = null;
            switch (this) {
                case V_Master:
                    value = CONST_V_Master;
                    break;

                default:
                    break;
            }

            return value;
        }
    }

    //-------------------------------------------------------
    // Action Result (6)
    //-------------------------------------------------------

    public class ListPresets_Result {
        public PresetNameList theCurrentPresetNameList;
    }

    // SetVolume has no Result

    // SetMute has no Result

    public class GetVolume_Result {
        public Integer theCurrentVolume;
    }

    public class GetMute_Result {
        public Boolean theCurrentMute;
    }

    // SelectPreset has no Result


    //-------------------------------------------------------
    // Action Handler (6)
    //-------------------------------------------------------

    public interface Handler {
        boolean onListPresets(Long theInstanceID, ListPresets_Result result);
        boolean onSetVolume(Long theInstanceID, A_ARG_TYPE_Channel theChannel, Integer theDesiredVolume);
        boolean onSetMute(Long theInstanceID, A_ARG_TYPE_Channel theChannel, Boolean theDesiredMute);
        boolean onGetVolume(Long theInstanceID, A_ARG_TYPE_Channel theChannel, GetVolume_Result result);
        boolean onGetMute(Long theInstanceID, A_ARG_TYPE_Channel theChannel, GetMute_Result result);
        boolean onSelectPreset(Long theInstanceID, A_ARG_TYPE_PresetName thePresetName);
    }

    private boolean handle_ListPresets(ActionInfo info) {
        Long theInstanceID = (Long)info.getArgumentValue(_ListPresets_ARG_InstanceID);
        ListPresets_Result result = new ListPresets_Result();

        if (_handler.onListPresets(theInstanceID, result)) {
            if (! info.setArgumentValue(_ListPresets_ARG_CurrentPresetNameList, result.theCurrentPresetNameList.getValue(), Argument.Direction.OUT)) {
                Log.d(TAG, "setArgumentValue: false");
                return false;
            }

            return true;
        }

        return false;
    }

    private boolean handle_SetVolume(ActionInfo info) {
        Long theInstanceID = (Long)info.getArgumentValue(_SetVolume_ARG_InstanceID);
        A_ARG_TYPE_Channel theChannel = A_ARG_TYPE_Channel.retrieveType((String)info.getArgumentValue(_SetVolume_ARG_Channel));
        Integer theDesiredVolume = (Integer)info.getArgumentValue(_SetVolume_ARG_DesiredVolume);

        if (_handler.onSetVolume(theInstanceID, theChannel, theDesiredVolume)) {
            return true;
        }

        return false;
    }

    private boolean handle_SetMute(ActionInfo info) {
        Long theInstanceID = (Long)info.getArgumentValue(_SetMute_ARG_InstanceID);
        A_ARG_TYPE_Channel theChannel = A_ARG_TYPE_Channel.retrieveType((String)info.getArgumentValue(_SetMute_ARG_Channel));
        Boolean theDesiredMute = (Boolean)info.getArgumentValue(_SetMute_ARG_DesiredMute);

        if (_handler.onSetMute(theInstanceID, theChannel, theDesiredMute)) {
            return true;
        }

        return false;
    }

    private boolean handle_GetVolume(ActionInfo info) {
        Long theInstanceID = (Long)info.getArgumentValue(_GetVolume_ARG_InstanceID);
        A_ARG_TYPE_Channel theChannel = A_ARG_TYPE_Channel.retrieveType((String)info.getArgumentValue(_GetVolume_ARG_Channel));
        GetVolume_Result result = new GetVolume_Result();

        if (_handler.onGetVolume(theInstanceID, theChannel, result)) {
            if (! info.setArgumentValue(_GetVolume_ARG_CurrentVolume, result.theCurrentVolume, Argument.Direction.OUT)) {
                Log.d(TAG, "setArgumentValue: false");
                return false;
            }

            return true;
        }

        return false;
    }

    private boolean handle_GetMute(ActionInfo info) {
        Long theInstanceID = (Long)info.getArgumentValue(_GetMute_ARG_InstanceID);
        A_ARG_TYPE_Channel theChannel = A_ARG_TYPE_Channel.retrieveType((String)info.getArgumentValue(_GetMute_ARG_Channel));
        GetMute_Result result = new GetMute_Result();

        if (_handler.onGetMute(theInstanceID, theChannel, result)) {
            if (! info.setArgumentValue(_GetMute_ARG_CurrentMute, result.theCurrentMute, Argument.Direction.OUT)) {
                Log.d(TAG, "setArgumentValue: false");
                return false;
            }

            return true;
        }

        return false;
    }

    private boolean handle_SelectPreset(ActionInfo info) {
        Long theInstanceID = (Long)info.getArgumentValue(_SelectPreset_ARG_InstanceID);
        A_ARG_TYPE_PresetName thePresetName = A_ARG_TYPE_PresetName.retrieveType((String)info.getArgumentValue(_SelectPreset_ARG_PresetName));

        if (_handler.onSelectPreset(theInstanceID, thePresetName)) {
            return true;
        }

        return false;
    }

    //-------------------------------------------------------
    // Method
    //-------------------------------------------------------

    private Service _service;
    private Handler _handler;

    public RenderingControl(Service service) {
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

        if (info.getAction().getName().equals(ACTION_ListPresets)) {
            return handle_ListPresets(info);
        }

        if (info.getAction().getName().equals(ACTION_SetVolume)) {
            return handle_SetVolume(info);
        }

        if (info.getAction().getName().equals(ACTION_SetMute)) {
            return handle_SetMute(info);
        }

        if (info.getAction().getName().equals(ACTION_GetVolume)) {
            return handle_GetVolume(info);
        }

        if (info.getAction().getName().equals(ACTION_GetMute)) {
            return handle_GetMute(info);
        }

        if (info.getAction().getName().equals(ACTION_SelectPreset)) {
            return handle_SelectPreset(info);
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

    public void setLastChange(String theLastChange) {
         _service.setPropertyValue(PROPERTY_LastChange, theLastChange);
    }

}
