package upnp.typedef.device.invocation;

import android.os.Parcel;
import android.os.Parcelable;

import upnp.typedef.device.DiscoveryType;
import upnp.typedef.property.PropertyList;

public class SubscriptionInfo implements Parcelable {

    private DiscoveryType discoveryType = DiscoveryType.UNDEFINED;
    private PropertyList fields = new PropertyList();

    public DiscoveryType getDiscoveryType() {
        return discoveryType;
    }

    public void setDiscoveryType(DiscoveryType discoveryType) {
        this.discoveryType = discoveryType;
    }

    public String getHostIp() {
        return (String) fields.getPropertyDataValue(SubscriptionInfoDefinition.HostIp);
    }

    public boolean setHostIp(String ip) {
        return fields.setPropertyDataValue(SubscriptionInfoDefinition.HostIp, ip);
    }

    public int getHostPort() {
        return (Integer) fields.getPropertyDataValue(SubscriptionInfoDefinition.HostPort);
    }

    public boolean setHostPort(int port) {
        return fields.setPropertyDataValue(SubscriptionInfoDefinition.HostPort, port);
    }

    public String getDeviceId() {
        return (String) fields.getPropertyDataValue(SubscriptionInfoDefinition.DeviceId);
    }

    public boolean setDeviceId(String deviceId) {
        return fields.setPropertyDataValue(SubscriptionInfoDefinition.DeviceId, deviceId);
    }

    public String getServiceId() {
        return (String) fields.getPropertyDataValue(SubscriptionInfoDefinition.ServiceId);
    }

    public boolean setServiceId(String id) {
        return fields.setPropertyDataValue(SubscriptionInfoDefinition.ServiceId, id);
    }

    public String getEventSubUrl() {
        return (String) fields.getPropertyDataValue(SubscriptionInfoDefinition.EventSubUrl);
    }

    public boolean setEventSubUrl(String id) {
        return fields.setPropertyDataValue(SubscriptionInfoDefinition.EventSubUrl, id);
    }

    public String getSubscriptionId() {
        return (String) fields.getPropertyDataValue(SubscriptionInfoDefinition.SubscriptionId);
    }

    public boolean setSubscriptionId(String id) {
        return fields.setPropertyDataValue(SubscriptionInfoDefinition.SubscriptionId, id);
    }

    public boolean isSubscribed() {
        return (Boolean) fields.getPropertyDataValue(SubscriptionInfoDefinition.Subscribed);
    }

    public boolean setSubscribed(boolean subscribed) {
        return fields.setPropertyDataValue(SubscriptionInfoDefinition.Subscribed, subscribed);
    }

    public int getTimeout() {
        return (Integer) fields.getPropertyDataValue(SubscriptionInfoDefinition.Timeout);
    }

    public boolean setTimeout(int timeout) {
        return fields.setPropertyDataValue(SubscriptionInfoDefinition.Timeout, timeout);
    }

    public static final Parcelable.Creator<SubscriptionInfo> CREATOR = new Parcelable.Creator<SubscriptionInfo>() {

        @Override
        public SubscriptionInfo createFromParcel(Parcel in) {
            return new SubscriptionInfo(in);
        }

        @Override
        public SubscriptionInfo[] newArray(int size) {
            return new SubscriptionInfo[size];
        }
    };

    public SubscriptionInfo() {
        this.initialize();
    }

    private void initialize() {
        this.fields.initProperty(SubscriptionInfoDefinition.HostIp, null);
        this.fields.initProperty(SubscriptionInfoDefinition.HostPort, null);
        this.fields.initProperty(SubscriptionInfoDefinition.DeviceId, null);
        this.fields.initProperty(SubscriptionInfoDefinition.ServiceId, null);
        this.fields.initProperty(SubscriptionInfoDefinition.EventSubUrl, null);
        this.fields.initProperty(SubscriptionInfoDefinition.SubscriptionId, null);
        this.fields.initProperty(SubscriptionInfoDefinition.Subscribed, null);
        this.fields.initProperty(SubscriptionInfoDefinition.Timeout, null);
    }

    public SubscriptionInfo(Parcel in) {
        this.initialize();

        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        discoveryType = DiscoveryType.retrieveType(in.readString());
        fields = in.readParcelable(PropertyList.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(discoveryType.toString());
        out.writeParcelable(fields, flags);
    }
}