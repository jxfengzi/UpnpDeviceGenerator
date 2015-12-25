package upnp.typedef.deviceclass;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import upnp.typedef.device.urn.DeviceType;

public class DeviceClass implements Parcelable, Serializable {

    private DeviceType deviceType;
    private Class<?> clazz;

    public DeviceClass(DeviceType type, Class<?> clazz) {
        this.deviceType = type;
        this.clazz = clazz;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceClass)) {
            return false;
        }

        DeviceClass other = (DeviceClass) obj;
        return deviceType.equals(other.deviceType);
    }

    @Override
    public int hashCode() {
        return deviceType.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.deviceType.toString());
        dest.writeSerializable(this.clazz);
    }

    private void readFromParcel(Parcel in) {
        deviceType = DeviceType.create(in.readString());
        clazz = (Class<?>) in.readSerializable();
    }

    private DeviceClass(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<DeviceClass> CREATOR = new Creator<DeviceClass>() {
        public DeviceClass createFromParcel(Parcel source) {
            return new DeviceClass(source);
        }
        public DeviceClass[] newArray(int size) {
            return new DeviceClass[size];
        }
    };
}
