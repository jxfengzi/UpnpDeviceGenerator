
package upnp.typedef.device.invocation;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import upnp.typedef.device.Action;
import upnp.typedef.device.Argument;
import upnp.typedef.device.DiscoveryType;
import upnp.typedef.device.urn.ServiceType;
import upnp.typedef.property.Property;
import upnp.typedef.property.PropertyList;

public class ActionInfo implements Parcelable {

    private static final String TAG = "ActionInfo";
    private Action action;
    private DiscoveryType discoveryType = DiscoveryType.UNDEFINED;
    private ServiceType serviceType = new ServiceType();
    private PropertyList fields = new PropertyList();
    private Map<String, Property> properties = new HashMap<String, Property>();

    public DiscoveryType getDiscoveryType() {
        return discoveryType;
    }

    public void setDiscoveryType(DiscoveryType discoveryType) {
        this.discoveryType = discoveryType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getHostIp() {
        return (String) fields.getPropertyDataValue(ActionInfoDefinition.HostIp);
    }

    public boolean setHostIp(String ip) {
        return fields.setPropertyDataValue(ActionInfoDefinition.HostIp, ip);
    }

    public int getHostPort() {
        return (Integer) fields.getPropertyDataValue(ActionInfoDefinition.HostPort);
    }

    public boolean setHostPort(int port) {
        return fields.setPropertyDataValue(ActionInfoDefinition.HostPort, port);
    }

    public String getDeviceId() {
        return (String) fields.getPropertyDataValue(ActionInfoDefinition.DeviceId);
    }

    public boolean setDeviceId(String deviceId) {
        return fields.setPropertyDataValue(ActionInfoDefinition.DeviceId, deviceId);
    }

    public String getControlUrl() {
        return (String) fields.getPropertyDataValue(ActionInfoDefinition.ControlUrl);
    }

    public boolean setControlUrl(String id) {
        return fields.setPropertyDataValue(ActionInfoDefinition.ControlUrl, id);
    }

    public Map<String, Property> getProperties() {
        return properties;
    }

    public boolean setArgumentValue(String name, Object value, Argument.Direction direction) {
        boolean ret = false;

        do {
            Property p = getRelatedProperty(name, direction);
            if (p == null) {
                Log.d(TAG, "relatedProperty not found: " + name);
                break;
            }

            ret = p.setDataValue(value);
        } while (false);

        return ret;
    }

    public boolean setArgumentValueByString(String name, String value, boolean nullValueAllowed, Argument.Direction direction) {
        boolean ret = false;

        do {
            Property p = getRelatedProperty(name, direction);
            if (p == null) {
                Log.d(TAG, "relatedProperty not found: " + name);
                break;
            }

            ret = p.setDataValueByString(value, nullValueAllowed);
        } while (false);

        return ret;
    }

    public Object getArgumentValue(String name) {
        Property property = getArgument(name);
        if (property != null) {
            return property.getCurrentValue();
        }

        return null;
    }

    public Property getArgument(String name) {
        return getRelatedProperty(name, Argument.Direction.IN);
    }

    public Property getResult(String name) {
        return getRelatedProperty(name, Argument.Direction.OUT);
    }

    private Property getRelatedProperty(String name, Argument.Direction direction) {
        Property p = null;

        do {
            Argument argument = null;

            for (Argument a: action.getArguments()) {
                if (a.getDirection() != direction) {
                    continue;
                }

                if (a.getName().equals(name)) {
                    argument = a;
                    break;
                }
            }

            if (argument == null) {
                Log.d(TAG, "argument not found: " + name);
                break;
            }

            p = properties.get(argument.getRelatedProperty());
            if (p == null) {
                Log.d(TAG, "Property not found: " + argument.getRelatedProperty());
                break;
            }
        } while (false);

        return p;
    }

    public static final Parcelable.Creator<ActionInfo> CREATOR = new Parcelable.Creator<ActionInfo>() {

        @Override
        public ActionInfo createFromParcel(Parcel in) {
            return new ActionInfo(in);
        }

        @Override
        public ActionInfo[] newArray(int size) {
            return new ActionInfo[size];
        }
    };

    public ActionInfo() {
        this.initialize();
    }

    private void initialize() {
        this.fields.initProperty(ActionInfoDefinition.HostIp, null);
        this.fields.initProperty(ActionInfoDefinition.HostPort, null);
        this.fields.initProperty(ActionInfoDefinition.ControlUrl, null);
        this.fields.initProperty(ActionInfoDefinition.DeviceId, null);
    }

    public ActionInfo(Parcel in) {
        this.initialize();

        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
//        Log.e(TAG, "readFromParcel");

        action = in.readParcelable(Action.class.getClassLoader());
        discoveryType = DiscoveryType.retrieveType(in.readString());
        serviceType = ServiceType.create(in.readString());
        fields = in.readParcelable(PropertyList.class.getClassLoader());

//        Log.e(TAG, "readFromParcel 1 -> " + action.getName());

        int n = in.readInt();
        for (int i = 0; i < n; i++) {
            Property property = in.readParcelable(Property.class.getClassLoader());
            properties.put(property.getDefinition().getName(), property);
        }

//        Log.e(TAG, "readFromParcel ok");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
//        Log.e(TAG, "writeToParcel: " + action.getName());

        out.writeParcelable(action, flags);
        out.writeString(discoveryType.toString());
        out.writeString(serviceType.toString());
        out.writeParcelable(fields, flags);

//        Log.e(TAG, "writeToParcel 1");

        out.writeInt(properties.size());
        for (Property def : properties.values()) {
            out.writeParcelable(def, flags);
        }

//        Log.e(TAG, "writeToParcel ok");
    }
}
