
package upnp.typedef.device;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import upnp.typedef.device.urn.ServiceType;
import upnp.typedef.property.Property;
import upnp.typedef.property.PropertyDefinition;
import upnp.typedef.property.PropertyList;

public class Service implements Parcelable {

    private static final String TAG = "Service";

    private Device device;
    private ServiceType type = new ServiceType();
    private PropertyList fields = new PropertyList();
    private Map<String, Action> actions = new HashMap<String, Action>();
    private volatile Map<String, Property> properties = new HashMap<String, Property>();
    private Map<String, Subscription> subscribers = new HashMap<String, Subscription>();

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public String getServiceId() {
        return (String) fields.getPropertyDataValue(ServiceDefinition.ServiceId);
    }

    public boolean setServiceId(String id) {
        return fields.setPropertyDataValue(ServiceDefinition.ServiceId, id);
    }

    public String getControlUrl() {
        return (String) fields.getPropertyDataValue(ServiceDefinition.ControlUrl);
    }

    public boolean setControlUrl(String url) {
        return fields.setPropertyDataValue(ServiceDefinition.ControlUrl, url);
    }

    public String getEventSubUrl() {
        return (String) fields.getPropertyDataValue(ServiceDefinition.EventSubUrl);
    }

    public boolean setEventSubUrl(String url) {
        return fields.setPropertyDataValue(ServiceDefinition.EventSubUrl, url);
    }

    public String getScpdUrl() {
        return (String) fields.getPropertyDataValue(ServiceDefinition.ScpdUrl);
    }

    public boolean setScpdUrl(String url) {
        return fields.setPropertyDataValue(ServiceDefinition.ScpdUrl, url);
    }

    public String getScpdFile() {
        return (String) fields.getPropertyDataValue(ServiceDefinition.ScpdFile);
    }

    public boolean setScpdFile(String file) {
        return fields.setPropertyDataValue(ServiceDefinition.ScpdFile, file);
    }

    public boolean isSubscribed() {
        return (Boolean) fields.getPropertyDataValue(ServiceDefinition.Subscribed);
    }

    public boolean setSubscribed(boolean subscribed) {
        return fields.setPropertyDataValue(ServiceDefinition.Subscribed, subscribed);
    }

    public String getSubscriptionId() {
        return (String) fields.getPropertyDataValue(ServiceDefinition.SubscriptionId);
    }

    public boolean setSubscriptionId(String id) {
        return fields.setPropertyDataValue(ServiceDefinition.SubscriptionId, id);
    }

    public Collection<Property> getProperties() {
        return this.properties.values();
    }

    public Property getProperty(String name) {
        return this.properties.get(name);
    }

    public Object getPropertyValue(String name) {
        Object value = null;

        do {
            Property property = this.properties.get(name);
            if (property == null) {
                break;
            }

            value = property.getCurrentValue();

        } while (false);

        Log.d(TAG, "getPropertyValue name:" + name + " value:" + value);
        return value;
    }

    public boolean setPropertyValue(String name, Object value) {
        boolean ret = false;

        do {
            Property property = this.properties.get(name);
            if (property == null) {
                break;
            }

            ret = property.setDataValue(value);
        } while (false);

        Log.d(TAG, "setPropertyValue name:" + name + " value:" + value + " ret:" + ret);
        return ret;
    }

    public PropertyDefinition getPropertyDefinition(String name) {
        PropertyDefinition def = null;

        do {
            Property p = this.properties.get(name);
            if (p == null) {
                break;
            }

            def = p.getDefinition();
        } while (false);

        return def;
    }

    public void addProperty(PropertyDefinition def) {
        this.properties.put(def.getName(), new Property(def, null));
    }

    public void addProperty(Property property) {
        this.properties.put(property.getDefinition().getName(), property);
    }

    public Map<String, Action> getActions() {
        return this.actions;
    }

    public void addAction(Action action) {
        action.setService(this);
        this.actions.put(action.getName(), action);
    }

    public Map<String, Subscription> getSubscribers() {
        return subscribers;
    }

    public void addSubscription(Subscription subscriber) {
        subscriber.setService(this);
        this.subscribers.put(subscriber.getCallbackUrl(), subscriber);
    }

    public void removeSubscription(String callback) {
        this.subscribers.remove(callback);
    }

    public Service() {
        this.initialize();
    }

    public Service(String serviceType, String version) {
        this.initialize();
        this.setType(new ServiceType(serviceType, version));
    }

    private void initialize() {
        this.fields.initProperty(ServiceDefinition.ServiceId, null);
        this.fields.initProperty(ServiceDefinition.ControlUrl, null);
        this.fields.initProperty(ServiceDefinition.EventSubUrl, null);
        this.fields.initProperty(ServiceDefinition.ScpdUrl, null);
        this.fields.initProperty(ServiceDefinition.ScpdFile, null);

        this.fields.initProperty(ServiceDefinition.Subscribed, null);
        this.fields.initProperty(ServiceDefinition.SubscriptionId, null);
    }

    public Service(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.type = ServiceType.create(in.readString());
        this.fields = in.readParcelable(PropertyList.class.getClassLoader());

        int n = in.readInt();
        for (int i = 0; i < n; i++) {
            Action action = in.readParcelable(Action.class.getClassLoader());
            this.addAction(action);
        }

        n = in.readInt();
        for (int i = 0; i < n; i++) {
            Property property = in.readParcelable(Property.class.getClassLoader());
            this.addProperty(property);
        }

        n = in.readInt();
        for (int i = 0; i < n; i++) {
            Subscription sub = in.readParcelable(Subscription.class.getClassLoader());
            this.addSubscription(sub);
        }
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.type.toString());
        out.writeParcelable(this.fields, flags);

        out.writeInt(this.actions.size());
        for (Action action : this.actions.values()) {
            out.writeParcelable(action, flags);
        }

        out.writeInt(this.properties.size());
        for (Property def : this.properties.values()) {
            out.writeParcelable(def, flags);
        }

        out.writeInt(this.subscribers.size());
        for (Subscription sub : this.subscribers.values()) {
            out.writeParcelable(sub, flags);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Service> CREATOR = new Creator<Service>() {
        public Service createFromParcel(Parcel source) {
            return new Service(source);
        }

        public Service[] newArray(int size) {
            return new Service[size];
        }
    };
}