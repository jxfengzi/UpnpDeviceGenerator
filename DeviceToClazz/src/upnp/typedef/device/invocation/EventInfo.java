package upnp.typedef.device.invocation;

import android.os.Parcel;
import android.os.Parcelable;

import upnp.typedef.device.DiscoveryType;
import upnp.typedef.device.urn.ServiceType;
import upnp.typedef.property.Property;
import upnp.typedef.property.PropertyList;

import java.util.ArrayList;
import java.util.List;

public class EventInfo implements Parcelable {

    private DiscoveryType discoveryType = DiscoveryType.UNDEFINED;
    private ServiceType serviceType = new ServiceType();
    private PropertyList fields = new PropertyList();
    private List<Property> properties = new ArrayList<Property>();

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

    public String getDeviceId() {
        return (String) fields.getPropertyDataValue(EventInfoDefinition.DeviceId);
    }

    public boolean setDeviceId(String deviceId) {
        return fields.setPropertyDataValue(EventInfoDefinition.DeviceId, deviceId);
    }

    public String getServiceId() {
        return (String) fields.getPropertyDataValue(EventInfoDefinition.ServiceId);
    }

    public boolean setServiceId(String serviceId) {
        return fields.setPropertyDataValue(EventInfoDefinition.ServiceId, serviceId);
    }

    public List<Property> getProperties() {
        return properties;
    }

    public static final Parcelable.Creator<EventInfo> CREATOR = new Parcelable.Creator<EventInfo>() {

        @Override
        public EventInfo createFromParcel(Parcel in) {
            return new EventInfo(in);
        }

        @Override
        public EventInfo[] newArray(int size) {
            return new EventInfo[size];
        }
    };

    public EventInfo() {
        initialize();
    }

    public EventInfo(Parcel in) {
        initialize();
        readFromParcel(in);
    }

    private void initialize() {
        this.fields.initProperty(EventInfoDefinition.DeviceId, null);
        this.fields.initProperty(EventInfoDefinition.ServiceId, null);
    }

    public void readFromParcel(Parcel in) {
        discoveryType = DiscoveryType.retrieveType(in.readString());
        serviceType = ServiceType.create(in.readString());
        fields = in.readParcelable(PropertyList.class.getClassLoader());

        int n = in.readInt();
        for (int i = 0; i < n; i++) {
            Property property = in.readParcelable(Property.class.getClassLoader());
            properties.add(property);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(discoveryType.toString());
        out.writeString(serviceType.toString());
        out.writeParcelable(fields, flags);

        out.writeInt(properties.size());
        for (Property def : properties) {
            out.writeParcelable(def, flags);
        }
    }
}