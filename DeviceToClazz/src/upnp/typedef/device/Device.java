
package upnp.typedef.device;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import upnp.typedef.device.urn.DeviceType;
import upnp.typedef.property.PropertyList;

public class Device implements Parcelable {

    private DeviceType deviceType = new DeviceType();
    private DiscoveryType discoveryType = DiscoveryType.UNDEFINED;
    private PropertyList fields = new PropertyList();
    private Map<String, Service> services = new HashMap<String, Service>();
    private List<Icon> icons = new ArrayList<Icon>();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        String deviceId = (String) this.fields.getPropertyDataValue(DeviceDefinition.DeviceId);
        result = prime * result + ((deviceId == null) ? 0 : deviceId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }

        Device other = (Device) obj;
        if (this == other) {
            return true;
        }

        String thizDeviceId = (String) this.fields.getPropertyDataValue(DeviceDefinition.DeviceId);
        String otherDeviceId = (String) other.fields.getPropertyDataValue(DeviceDefinition.DeviceId);

        return thizDeviceId.equals(otherDeviceId);
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType type) {
        this.deviceType = type;
    }

    public DiscoveryType getDiscoveryType() {
        return discoveryType;
    }

    public void setDiscoveryType(DiscoveryType discoveryType) {
        this.discoveryType = discoveryType;
    }

    public String getLocation() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.Location);
    }

    public boolean setLocation(String location) {
        return fields.setPropertyDataValue(DeviceDefinition.Location, location);
    }

    public String getLocationFile() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.LocationFile);
    }

    public boolean setLocationFile(String file) {
        return fields.setPropertyDataValue(DeviceDefinition.LocationFile, file);
    }

    public String getHostIp() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.HostIp);
    }

    public boolean setHostIp(String ip) {
        return fields.setPropertyDataValue(DeviceDefinition.HostIp, ip);
    }

    public int getHostPort() {
        return (Integer) fields.getPropertyDataValue(DeviceDefinition.HostPort);
    }

    public boolean setHostPort(int port) {
        return fields.setPropertyDataValue(DeviceDefinition.HostPort, port);
    }

    public String getDeviceId() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.DeviceId);
    }

    public boolean setDeviceId(String deviceId) {
        return fields.setPropertyDataValue(DeviceDefinition.DeviceId, deviceId);
    }

    public String getFriendlyName() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.FriendlyName);
    }

    public boolean setFriendlyName(String name) {
        return fields.setPropertyDataValue(DeviceDefinition.FriendlyName, name);
    }

    public String getManufacturer() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.Manufacturer);
    }

    public boolean setManufacturer(String manufacturer) {
        return fields.setPropertyDataValue(DeviceDefinition.Manufacturer, manufacturer);
    }

    public String getManufacturerUrl() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.ManufacturerUrl);
    }

    public boolean setManufacturerUrl(String url) {
        return fields.setPropertyDataValue(DeviceDefinition.ManufacturerUrl, url);
    }

    public String getModelDescription() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.ModelDescription);
    }

    public boolean setModelDescription(String description) {
        return fields.setPropertyDataValue(DeviceDefinition.ModelDescription, description);
    }

    public String getModelName() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.ModelName);
    }

    public boolean setModelName(String name) {
        return fields.setPropertyDataValue(DeviceDefinition.ModelName, name);
    }

    public String getModelNumber() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.ModelNumber);
    }

    public boolean setModelNumber(String number) {
        return fields.setPropertyDataValue(DeviceDefinition.ModelNumber, number);
    }

    public String getModelUrl() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.ModelUrl);
    }

    public boolean setModelUrl(String url) {
        return fields.setPropertyDataValue(DeviceDefinition.ModelUrl, url);
    }

    public String getSerialNumber() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.SerialNumber);
    }

    public boolean setSerialNumber(String number) {
        return fields.setPropertyDataValue(DeviceDefinition.SerialNumber, number);
    }

    public String getPresentationUrl() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.PresentationUrl);
    }

    public boolean setPresentationUrl(String url) {
        return fields.setPropertyDataValue(DeviceDefinition.PresentationUrl, url);
    }

    public String getUrlBase() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.UrlBase);
    }

    public boolean setUrlBase(String url) {
        return fields.setPropertyDataValue(DeviceDefinition.UrlBase, url);
    }

    public String getUpc() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.Upc);
    }

    public boolean setUpc(String upc) {
        return fields.setPropertyDataValue(DeviceDefinition.Upc, upc);
    }

    public String getQplayCapability() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.QplayCapability);
    }

    public boolean setQplayCapability(String capability) {
        return fields.setPropertyDataValue(DeviceDefinition.QplayCapability, capability);
    }

    public String getDlnaDoc() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.DlnaDoc);
    }

    public boolean setDlnaDoc(String doc) {
        return fields.setPropertyDataValue(DeviceDefinition.DlnaDoc, doc);
    }

    public String getDlnaCap() {
        return (String) fields.getPropertyDataValue(DeviceDefinition.DlnaCap);
    }

    public boolean setDlnaCap(String cap) {
        return fields.setPropertyDataValue(DeviceDefinition.DlnaCap, cap);
    }

    public List<Icon> getIcons() {
        return icons;
    }

    public void addIcon(Icon icon) {
        icons.add(icon);
    }

    public Map<String, Service> getServices() {
        return this.services;
    }

    public void addService(Service service) {
        service.setDevice(this);
        services.put(service.getServiceId(), service);
    }

    public Service getService(String serviceId) {
        return this.services.get(serviceId);
    }

    public static final Parcelable.Creator<Device> CREATOR = new Parcelable.Creator<Device>() {

        @Override
        public Device createFromParcel(Parcel in) {
            return new Device(in);
        }

        @Override
        public Device[] newArray(int size) {
            return new Device[size];
        }
    };

    public Device() {
        this.initialize();
    }

    public Device(String deviceType, String version) {
        this.initialize();
        this.setDeviceType(new DeviceType(deviceType, version));
    }

    private void initialize() {
        this.fields.initProperty(DeviceDefinition.Location, null);
        this.fields.initProperty(DeviceDefinition.LocationFile, null);
        this.fields.initProperty(DeviceDefinition.HostIp, null);
        this.fields.initProperty(DeviceDefinition.HostPort, null);

        this.fields.initProperty(DeviceDefinition.DeviceId, null);
        this.fields.initProperty(DeviceDefinition.FriendlyName, null);
        this.fields.initProperty(DeviceDefinition.Manufacturer, null);
        this.fields.initProperty(DeviceDefinition.ManufacturerUrl, null);
        this.fields.initProperty(DeviceDefinition.ModelDescription, null);
        this.fields.initProperty(DeviceDefinition.ModelName, null);
        this.fields.initProperty(DeviceDefinition.ModelNumber, null);
        this.fields.initProperty(DeviceDefinition.ModelUrl, null);
        this.fields.initProperty(DeviceDefinition.SerialNumber, null);
        this.fields.initProperty(DeviceDefinition.PresentationUrl, null);
        this.fields.initProperty(DeviceDefinition.UrlBase, null);

        this.fields.initProperty(DeviceDefinition.Upc, null);
        this.fields.initProperty(DeviceDefinition.QplayCapability, null);
        this.fields.initProperty(DeviceDefinition.DlnaDoc, null);
        this.fields.initProperty(DeviceDefinition.DlnaCap, null);
    }

    public Device(Parcel in) {
        this.initialize();
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.deviceType = DeviceType.create(in.readString());
        this.discoveryType = DiscoveryType.retrieveType(in.readString());
        this.fields = in.readParcelable(PropertyList.class.getClassLoader());

        int n = in.readInt();
        for (int i = 0; i < n; i++) {
            Icon icon = in.readParcelable(Icon.class.getClassLoader());
            this.icons.add(icon);
        }

        n = in.readInt();
        for (int i = 0; i < n; i++) {
            Service service = in.readParcelable(Service.class.getClassLoader());
            this.addService(service);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.deviceType.toString());
        out.writeString(this.discoveryType.toString());
        out.writeParcelable(this.fields, flags);

        out.writeInt(this.icons.size());
        for (Icon icon : this.icons) {
            out.writeParcelable(icon, flags);
        }

        out.writeInt(this.services.size());
        for (Service service : this.services.values()) {
            out.writeParcelable(service, flags);
        }
    }
}