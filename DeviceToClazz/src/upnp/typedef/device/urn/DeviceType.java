
package upnp.typedef.device.urn;

import android.os.Parcel;
import android.os.Parcelable;

import upnp.typedef.device.urn.schemas.Schemas;

public class DeviceType extends Urn implements Parcelable {

    public DeviceType(String subType, String version) {
        super.setType(Type.DEVICE);
        super.setDomain(Schemas.UPNP);
        super.setSubType(subType);
        super.setVersion(version);
    }

    public static DeviceType create(String string) {
        DeviceType thiz = new DeviceType();
        if (!thiz.parse(string)) {
            thiz = null;
        }

        return thiz;
    }

    @Override
    public boolean parse(String string) {
        boolean ret;

        do {
            ret = super.parse(string);
            if (!ret) {
                break;
            }

            if (this.getType() != Type.DEVICE) {
                break;
            }

            ret = true;
        } while (false);

        return ret;
    }

    public String getName() {
        return this.getSubType();
    }

    public static final Parcelable.Creator<DeviceType> CREATOR = new Parcelable.Creator<DeviceType>() {

        @Override
        public DeviceType createFromParcel(Parcel in) {
            return new DeviceType(in);
        }

        @Override
        public DeviceType[] newArray(int size) {
            return new DeviceType[size];
        }
    };

    public DeviceType() {
    }

    public DeviceType(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.parse(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.toString());
    }
}