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

public class ContentDirectory extends ServiceHandler {
    private static final String TAG = "ContentDirectory";
    private static final ServiceType SERVICE_TYPE =  new ServiceType("ContentDirectory", "1");

    //-------------------------------------------------------
    // Action Names (7)
    //-------------------------------------------------------
    public static final String ACTION_UpdateObject = "UpdateObject";
    public static final String _UpdateObject_ARG_ObjectID = "ObjectID";
    public static final String _UpdateObject_ARG_CurrentTagValue = "CurrentTagValue";
    public static final String _UpdateObject_ARG_NewTagValue = "NewTagValue";
    public static final String ACTION_GetSearchCapabilities = "GetSearchCapabilities";
    public static final String _GetSearchCapabilities_ARG_SearchCaps = "SearchCaps";
    public static final String ACTION_Search = "Search";
    public static final String _Search_ARG_ContainerID = "ContainerID";
    public static final String _Search_ARG_SearchCriteria = "SearchCriteria";
    public static final String _Search_ARG_Filter = "Filter";
    public static final String _Search_ARG_StartingIndex = "StartingIndex";
    public static final String _Search_ARG_RequestedCount = "RequestedCount";
    public static final String _Search_ARG_SortCriteria = "SortCriteria";
    public static final String _Search_ARG_Result = "Result";
    public static final String _Search_ARG_NumberReturned = "NumberReturned";
    public static final String _Search_ARG_TotalMatches = "TotalMatches";
    public static final String _Search_ARG_UpdateID = "UpdateID";
    public static final String ACTION_X_GetRemoteSharingStatus = "X_GetRemoteSharingStatus";
    public static final String _X_GetRemoteSharingStatus_ARG_Status = "Status";
    public static final String ACTION_GetSortCapabilities = "GetSortCapabilities";
    public static final String _GetSortCapabilities_ARG_SortCaps = "SortCaps";
    public static final String ACTION_GetSystemUpdateID = "GetSystemUpdateID";
    public static final String _GetSystemUpdateID_ARG_Id = "Id";
    public static final String ACTION_Browse = "Browse";
    public static final String _Browse_ARG_ObjectID = "ObjectID";
    public static final String _Browse_ARG_BrowseFlag = "BrowseFlag";
    public static final String _Browse_ARG_Filter = "Filter";
    public static final String _Browse_ARG_StartingIndex = "StartingIndex";
    public static final String _Browse_ARG_RequestedCount = "RequestedCount";
    public static final String _Browse_ARG_SortCriteria = "SortCriteria";
    public static final String _Browse_ARG_Result = "Result";
    public static final String _Browse_ARG_NumberReturned = "NumberReturned";
    public static final String _Browse_ARG_TotalMatches = "TotalMatches";
    public static final String _Browse_ARG_UpdateID = "UpdateID";

    //-------------------------------------------------------
    // Property Name (15)
    //-------------------------------------------------------
    public static final String PROPERTY_SystemUpdateID = "SystemUpdateID";
    public static final String PROPERTY_ContainerUpdateIDs = "ContainerUpdateIDs";
    public static final String PROPERTY_A_ARG_TYPE_BrowseFlag = "A_ARG_TYPE_BrowseFlag";
    public static final String PROPERTY_SearchCapabilities = "SearchCapabilities";
    public static final String PROPERTY_A_ARG_TYPE_Filter = "A_ARG_TYPE_Filter";
    public static final String PROPERTY_A_ARG_TYPE_TagValueList = "A_ARG_TYPE_TagValueList";
    public static final String PROPERTY_A_ARG_TYPE_ObjectID = "A_ARG_TYPE_ObjectID";
    public static final String PROPERTY_X_RemoteSharingEnabled = "X_RemoteSharingEnabled";
    public static final String PROPERTY_A_ARG_TYPE_Count = "A_ARG_TYPE_Count";
    public static final String PROPERTY_A_ARG_TYPE_Index = "A_ARG_TYPE_Index";
    public static final String PROPERTY_A_ARG_TYPE_SortCriteria = "A_ARG_TYPE_SortCriteria";
    public static final String PROPERTY_SortCapabilities = "SortCapabilities";
    public static final String PROPERTY_A_ARG_TYPE_UpdateID = "A_ARG_TYPE_UpdateID";
    public static final String PROPERTY_A_ARG_TYPE_SearchCriteria = "A_ARG_TYPE_SearchCriteria";
    public static final String PROPERTY_A_ARG_TYPE_Result = "A_ARG_TYPE_Result";

    //-------------------------------------------------------
    // Property value defined (1)
    //-------------------------------------------------------

    public enum A_ARG_TYPE_BrowseFlag {
        UNDEFINED,
        V_BrowseMetadata,
        V_BrowseDirectChildren;

        private static final String CONST_UNDEFINED = "UNDEFINED";
        private static final String CONST_V_BrowseMetadata = "BrowseMetadata";
        private static final String CONST_V_BrowseDirectChildren = "BrowseDirectChildren";

        public static A_ARG_TYPE_BrowseFlag retrieveType(String value) {
            if (value.equals(CONST_UNDEFINED)) {
                return UNDEFINED;
            }

            if (value.equals(CONST_V_BrowseMetadata)) {
                return V_BrowseMetadata;
            }

            if (value.equals(CONST_V_BrowseDirectChildren)) {
                return V_BrowseDirectChildren;
            }

            return UNDEFINED;
        }

        public String getValue() {
            String value = null;
            switch (this) {
                case V_BrowseMetadata:
                    value = CONST_V_BrowseMetadata;
                    break;

                case V_BrowseDirectChildren:
                    value = CONST_V_BrowseDirectChildren;
                    break;

                default:
                    break;
            }

            return value;
        }
    }

    //-------------------------------------------------------
    // Action Result (7)
    //-------------------------------------------------------

    // UpdateObject has no Result

    public class GetSearchCapabilities_Result {
        public String theSearchCaps;
    }

    public class Search_Result {
        public String theResult;
        public Long theNumberReturned;
        public Long theTotalMatches;
        public Long theUpdateID;
    }

    public class X_GetRemoteSharingStatus_Result {
        public Boolean theStatus;
    }

    public class GetSortCapabilities_Result {
        public String theSortCaps;
    }

    public class GetSystemUpdateID_Result {
        public Long theId;
    }

    public class Browse_Result {
        public String theResult;
        public Long theNumberReturned;
        public Long theTotalMatches;
        public Long theUpdateID;
    }


    //-------------------------------------------------------
    // Action Handler (7)
    //-------------------------------------------------------

    public interface Handler {
        UpnpError onUpdateObject(String theObjectID, String theCurrentTagValue, String theNewTagValue);
        UpnpError onGetSearchCapabilities(GetSearchCapabilities_Result result);
        UpnpError onSearch(String theContainerID, String theSearchCriteria, String theFilter, Long theStartingIndex, Long theRequestedCount, String theSortCriteria, Search_Result result);
        UpnpError onX_GetRemoteSharingStatus(X_GetRemoteSharingStatus_Result result);
        UpnpError onGetSortCapabilities(GetSortCapabilities_Result result);
        UpnpError onGetSystemUpdateID(GetSystemUpdateID_Result result);
        UpnpError onBrowse(String theObjectID, A_ARG_TYPE_BrowseFlag theBrowseFlag, String theFilter, Long theStartingIndex, Long theRequestedCount, String theSortCriteria, Browse_Result result);
    }

    private UpnpError handle_UpdateObject(ActionInfo info) {
        String theObjectID = (String)info.getArgumentValue(_UpdateObject_ARG_ObjectID);
        String theCurrentTagValue = (String)info.getArgumentValue(_UpdateObject_ARG_CurrentTagValue);
        String theNewTagValue = (String)info.getArgumentValue(_UpdateObject_ARG_NewTagValue);

        UpnpError error = _handler.onUpdateObject(theObjectID, theCurrentTagValue, theNewTagValue);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_GetSearchCapabilities(ActionInfo info) {
        GetSearchCapabilities_Result result = new GetSearchCapabilities_Result();

        UpnpError error = _handler.onGetSearchCapabilities(result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_GetSearchCapabilities_ARG_SearchCaps, result.theSearchCaps, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_Search(ActionInfo info) {
        String theContainerID = (String)info.getArgumentValue(_Search_ARG_ContainerID);
        String theSearchCriteria = (String)info.getArgumentValue(_Search_ARG_SearchCriteria);
        String theFilter = (String)info.getArgumentValue(_Search_ARG_Filter);
        Long theStartingIndex = (Long)info.getArgumentValue(_Search_ARG_StartingIndex);
        Long theRequestedCount = (Long)info.getArgumentValue(_Search_ARG_RequestedCount);
        String theSortCriteria = (String)info.getArgumentValue(_Search_ARG_SortCriteria);
        Search_Result result = new Search_Result();

        UpnpError error = _handler.onSearch(theContainerID, theSearchCriteria, theFilter, theStartingIndex, theRequestedCount, theSortCriteria, result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_Search_ARG_Result, result.theResult, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_Search_ARG_NumberReturned, result.theNumberReturned, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_Search_ARG_TotalMatches, result.theTotalMatches, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_Search_ARG_UpdateID, result.theUpdateID, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_X_GetRemoteSharingStatus(ActionInfo info) {
        X_GetRemoteSharingStatus_Result result = new X_GetRemoteSharingStatus_Result();

        UpnpError error = _handler.onX_GetRemoteSharingStatus(result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_X_GetRemoteSharingStatus_ARG_Status, result.theStatus, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_GetSortCapabilities(ActionInfo info) {
        GetSortCapabilities_Result result = new GetSortCapabilities_Result();

        UpnpError error = _handler.onGetSortCapabilities(result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_GetSortCapabilities_ARG_SortCaps, result.theSortCaps, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_GetSystemUpdateID(ActionInfo info) {
        GetSystemUpdateID_Result result = new GetSystemUpdateID_Result();

        UpnpError error = _handler.onGetSystemUpdateID(result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_GetSystemUpdateID_ARG_Id, result.theId, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        return UpnpError.OK;
    }

    private UpnpError handle_Browse(ActionInfo info) {
        String theObjectID = (String)info.getArgumentValue(_Browse_ARG_ObjectID);
        A_ARG_TYPE_BrowseFlag theBrowseFlag = A_ARG_TYPE_BrowseFlag.retrieveType((String)info.getArgumentValue(_Browse_ARG_BrowseFlag));
        String theFilter = (String)info.getArgumentValue(_Browse_ARG_Filter);
        Long theStartingIndex = (Long)info.getArgumentValue(_Browse_ARG_StartingIndex);
        Long theRequestedCount = (Long)info.getArgumentValue(_Browse_ARG_RequestedCount);
        String theSortCriteria = (String)info.getArgumentValue(_Browse_ARG_SortCriteria);
        Browse_Result result = new Browse_Result();

        UpnpError error = _handler.onBrowse(theObjectID, theBrowseFlag, theFilter, theStartingIndex, theRequestedCount, theSortCriteria, result);
        if (! error.equals(UpnpError.OK)) {
            return error;
        }

        if (! info.setArgumentValue(_Browse_ARG_Result, result.theResult, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_Browse_ARG_NumberReturned, result.theNumberReturned, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_Browse_ARG_TotalMatches, result.theTotalMatches, Argument.Direction.OUT)) {
            Log.d(TAG, "setArgumentValue: false");
            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
        }

        if (! info.setArgumentValue(_Browse_ARG_UpdateID, result.theUpdateID, Argument.Direction.OUT)) {
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

    public ContentDirectory(Device device) {
        _service = new Service(SERVICE_TYPE);
        _service.setServiceId(toServiceId(SERVICE_TYPE));
        _service.setScpdUrl(toScpdUrl(device.getDeviceId(), SERVICE_TYPE));
        _service.setControlUrl(toCtrlUrl(device.getDeviceId(), SERVICE_TYPE));
        _service.setEventSubUrl(toEventUrl(device.getDeviceId(), SERVICE_TYPE));

        Action _UpdateObject = new Action(ACTION_UpdateObject);
        _UpdateObject.addArgument(new Argument(_UpdateObject_ARG_ObjectID, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_ObjectID));
        _UpdateObject.addArgument(new Argument(_UpdateObject_ARG_CurrentTagValue, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_TagValueList));
        _UpdateObject.addArgument(new Argument(_UpdateObject_ARG_NewTagValue, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_TagValueList));
        _service.addAction(_UpdateObject);

        Action _GetSearchCapabilities = new Action(ACTION_GetSearchCapabilities);
        _GetSearchCapabilities.addArgument(new Argument(_GetSearchCapabilities_ARG_SearchCaps, Argument.Direction.OUT, PROPERTY_SearchCapabilities));
        _service.addAction(_GetSearchCapabilities);

        Action _Search = new Action(ACTION_Search);
        _Search.addArgument(new Argument(_Search_ARG_ContainerID, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_ObjectID));
        _Search.addArgument(new Argument(_Search_ARG_SearchCriteria, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_SearchCriteria));
        _Search.addArgument(new Argument(_Search_ARG_Filter, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_Filter));
        _Search.addArgument(new Argument(_Search_ARG_StartingIndex, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_Index));
        _Search.addArgument(new Argument(_Search_ARG_RequestedCount, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_Count));
        _Search.addArgument(new Argument(_Search_ARG_SortCriteria, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_SortCriteria));
        _Search.addArgument(new Argument(_Search_ARG_Result, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_Result));
        _Search.addArgument(new Argument(_Search_ARG_NumberReturned, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_Count));
        _Search.addArgument(new Argument(_Search_ARG_TotalMatches, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_Count));
        _Search.addArgument(new Argument(_Search_ARG_UpdateID, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_UpdateID));
        _service.addAction(_Search);

        Action _X_GetRemoteSharingStatus = new Action(ACTION_X_GetRemoteSharingStatus);
        _X_GetRemoteSharingStatus.addArgument(new Argument(_X_GetRemoteSharingStatus_ARG_Status, Argument.Direction.OUT, PROPERTY_X_RemoteSharingEnabled));
        _service.addAction(_X_GetRemoteSharingStatus);

        Action _GetSortCapabilities = new Action(ACTION_GetSortCapabilities);
        _GetSortCapabilities.addArgument(new Argument(_GetSortCapabilities_ARG_SortCaps, Argument.Direction.OUT, PROPERTY_SortCapabilities));
        _service.addAction(_GetSortCapabilities);

        Action _GetSystemUpdateID = new Action(ACTION_GetSystemUpdateID);
        _GetSystemUpdateID.addArgument(new Argument(_GetSystemUpdateID_ARG_Id, Argument.Direction.OUT, PROPERTY_SystemUpdateID));
        _service.addAction(_GetSystemUpdateID);

        Action _Browse = new Action(ACTION_Browse);
        _Browse.addArgument(new Argument(_Browse_ARG_ObjectID, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_ObjectID));
        _Browse.addArgument(new Argument(_Browse_ARG_BrowseFlag, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_BrowseFlag));
        _Browse.addArgument(new Argument(_Browse_ARG_Filter, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_Filter));
        _Browse.addArgument(new Argument(_Browse_ARG_StartingIndex, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_Index));
        _Browse.addArgument(new Argument(_Browse_ARG_RequestedCount, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_Count));
        _Browse.addArgument(new Argument(_Browse_ARG_SortCriteria, Argument.Direction.IN, PROPERTY_A_ARG_TYPE_SortCriteria));
        _Browse.addArgument(new Argument(_Browse_ARG_Result, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_Result));
        _Browse.addArgument(new Argument(_Browse_ARG_NumberReturned, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_Count));
        _Browse.addArgument(new Argument(_Browse_ARG_TotalMatches, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_Count));
        _Browse.addArgument(new Argument(_Browse_ARG_UpdateID, Argument.Direction.OUT, PROPERTY_A_ARG_TYPE_UpdateID));
        _service.addAction(_Browse);

        PropertyDefinition _SystemUpdateID = new PropertyDefinition(PROPERTY_SystemUpdateID, DataType.UI4, true);
        _service.addProperty(_SystemUpdateID);

        PropertyDefinition _ContainerUpdateIDs = new PropertyDefinition(PROPERTY_ContainerUpdateIDs, DataType.STRING, true);
        _service.addProperty(_ContainerUpdateIDs);

        PropertyDefinition _A_ARG_TYPE_BrowseFlag = new PropertyDefinition(PROPERTY_A_ARG_TYPE_BrowseFlag, DataType.STRING, false);
        AllowedValueList _A_ARG_TYPE_BrowseFlag_list = new AllowedValueList(DataType.STRING);
        _A_ARG_TYPE_BrowseFlag_list.appendAllowedValue("BrowseMetadata");
        _A_ARG_TYPE_BrowseFlag_list.appendAllowedValue("BrowseDirectChildren");
        _A_ARG_TYPE_BrowseFlag.setAllowedValueList(_A_ARG_TYPE_BrowseFlag_list);
        _service.addProperty(_A_ARG_TYPE_BrowseFlag);

        PropertyDefinition _SearchCapabilities = new PropertyDefinition(PROPERTY_SearchCapabilities, DataType.STRING, false);
        _service.addProperty(_SearchCapabilities);

        PropertyDefinition _A_ARG_TYPE_Filter = new PropertyDefinition(PROPERTY_A_ARG_TYPE_Filter, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_Filter);

        PropertyDefinition _A_ARG_TYPE_TagValueList = new PropertyDefinition(PROPERTY_A_ARG_TYPE_TagValueList, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_TagValueList);

        PropertyDefinition _A_ARG_TYPE_ObjectID = new PropertyDefinition(PROPERTY_A_ARG_TYPE_ObjectID, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_ObjectID);

        PropertyDefinition _X_RemoteSharingEnabled = new PropertyDefinition(PROPERTY_X_RemoteSharingEnabled, DataType.BOOLEAN, true);
        _service.addProperty(_X_RemoteSharingEnabled);

        PropertyDefinition _A_ARG_TYPE_Count = new PropertyDefinition(PROPERTY_A_ARG_TYPE_Count, DataType.UI4, false);
        _service.addProperty(_A_ARG_TYPE_Count);

        PropertyDefinition _A_ARG_TYPE_Index = new PropertyDefinition(PROPERTY_A_ARG_TYPE_Index, DataType.UI4, false);
        _service.addProperty(_A_ARG_TYPE_Index);

        PropertyDefinition _A_ARG_TYPE_SortCriteria = new PropertyDefinition(PROPERTY_A_ARG_TYPE_SortCriteria, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_SortCriteria);

        PropertyDefinition _SortCapabilities = new PropertyDefinition(PROPERTY_SortCapabilities, DataType.STRING, false);
        _service.addProperty(_SortCapabilities);

        PropertyDefinition _A_ARG_TYPE_UpdateID = new PropertyDefinition(PROPERTY_A_ARG_TYPE_UpdateID, DataType.UI4, false);
        _service.addProperty(_A_ARG_TYPE_UpdateID);

        PropertyDefinition _A_ARG_TYPE_SearchCriteria = new PropertyDefinition(PROPERTY_A_ARG_TYPE_SearchCriteria, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_SearchCriteria);

        PropertyDefinition _A_ARG_TYPE_Result = new PropertyDefinition(PROPERTY_A_ARG_TYPE_Result, DataType.STRING, false);
        _service.addProperty(_A_ARG_TYPE_Result);

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

        if (info.getAction().getName().equals(ACTION_UpdateObject)) {
            return handle_UpdateObject(info);
        }

        if (info.getAction().getName().equals(ACTION_GetSearchCapabilities)) {
            return handle_GetSearchCapabilities(info);
        }

        if (info.getAction().getName().equals(ACTION_Search)) {
            return handle_Search(info);
        }

        if (info.getAction().getName().equals(ACTION_X_GetRemoteSharingStatus)) {
            return handle_X_GetRemoteSharingStatus(info);
        }

        if (info.getAction().getName().equals(ACTION_GetSortCapabilities)) {
            return handle_GetSortCapabilities(info);
        }

        if (info.getAction().getName().equals(ACTION_GetSystemUpdateID)) {
            return handle_GetSystemUpdateID(info);
        }

        if (info.getAction().getName().equals(ACTION_Browse)) {
            return handle_Browse(info);
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

    public void setSystemUpdateID(Long theSystemUpdateID) {
         _service.setPropertyValue(PROPERTY_SystemUpdateID, theSystemUpdateID);
    }

    public void setContainerUpdateIDs(String theContainerUpdateIDs) {
         _service.setPropertyValue(PROPERTY_ContainerUpdateIDs, theContainerUpdateIDs);
    }

    public void setX_RemoteSharingEnabled(Boolean theX_RemoteSharingEnabled) {
         _service.setPropertyValue(PROPERTY_X_RemoteSharingEnabled, theX_RemoteSharingEnabled);
    }

}
