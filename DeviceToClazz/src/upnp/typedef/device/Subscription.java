package upnp.typedef.device;

import android.os.Parcel;
import android.os.Parcelable;

import upnp.typedef.property.PropertyList;

public class Subscription implements Parcelable {

    private Service service;
    private PropertyList fields = new PropertyList();

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getCallbackUrl() {
        return (String) fields.getPropertyDataValue(SubscriptionDefinition.CallbackUrl);
    }

    public boolean setCallbackUrl(String url) {
        return fields.setPropertyDataValue(SubscriptionDefinition.CallbackUrl, url);
    }

    public String getSubscriptionId() {
        return (String) fields.getPropertyDataValue(SubscriptionDefinition.SubscriptionId);
    }

    public boolean setSubscriptionId(String id) {
        return fields.setPropertyDataValue(SubscriptionDefinition.SubscriptionId, id);
    }

    public int getTimeout() {
        return (Integer) fields.getPropertyDataValue(SubscriptionDefinition.Timeout);
    }

    public boolean setTimeout(int timeout) {
        return fields.setPropertyDataValue(SubscriptionDefinition.Timeout, timeout);
    }

    public static final Parcelable.Creator<Subscription> CREATOR = new Parcelable.Creator<Subscription>() {

        @Override
        public Subscription createFromParcel(Parcel in) {
            return new Subscription(in);
        }

        @Override
        public Subscription[] newArray(int size) {
            return new Subscription[size];
        }
    };

    public Subscription() {
        initialize();
    }

    private void initialize() {
        this.fields.initProperty(SubscriptionDefinition.CallbackUrl, null);
        this.fields.initProperty(SubscriptionDefinition.SubscriptionId, null);
        this.fields.initProperty(SubscriptionDefinition.Timeout, null);
    }

    public Subscription(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.fields = in.readParcelable(PropertyList.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.fields, flags);
    }
}