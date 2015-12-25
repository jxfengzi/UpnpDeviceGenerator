package upnp.typedef.device;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyChanged implements Parcelable {

    private String name;
    private String value;

    public PropertyChanged(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static final Creator<PropertyChanged> CREATOR = new Creator<PropertyChanged>() {
        public PropertyChanged createFromParcel(Parcel source) {
            return new PropertyChanged(source);
        }

        public PropertyChanged[] newArray(int size) {
            return new PropertyChanged[size];
        }
    };

    public PropertyChanged(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        name = in.readString();
        value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}