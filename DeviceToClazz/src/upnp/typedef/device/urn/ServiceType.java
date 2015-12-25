
package upnp.typedef.device.urn;

import android.os.Parcel;
import android.os.Parcelable;

import upnp.typedef.device.urn.schemas.Schemas;

public class ServiceType extends Urn implements Parcelable {

    public ServiceType(String subType, String version) {
        super.setType(Type.SERVICE);
        super.setDomain(Schemas.UPNP);
        super.setSubType(subType);
        super.setVersion(version);
    }

    public static ServiceType create(String string) {
        ServiceType thiz = new ServiceType();
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

            if (this.getType() != Type.SERVICE) {
                break;
            }

            ret = true;
        } while (false);

        return ret;
    }

    public String getName() {
        return this.getSubType();
    }

    public static final Parcelable.Creator<ServiceType> CREATOR = new Parcelable.Creator<ServiceType>() {

        @Override
        public ServiceType createFromParcel(Parcel in) {
            return new ServiceType(in);
        }

        @Override
        public ServiceType[] newArray(int size) {
            return new ServiceType[size];
        }
    };

    public ServiceType() {
    }

    public ServiceType(Parcel in) {
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