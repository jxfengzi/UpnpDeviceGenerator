package upnp.typedef.device;

import android.os.Parcel;
import android.os.Parcelable;

import upnp.typedef.property.PropertyList;

public class Icon implements Parcelable {

    private static final String TAG = "Icon";
    private PropertyList fields = new PropertyList();

    public boolean setMimeType(String mimeType) {
        return fields.setPropertyDataValue(IconDefinition.MimeType, mimeType);
    }

    public String getMimeType() {
        return (String) fields.getPropertyDataValue(IconDefinition.MimeType);
    }

    public boolean setWidth(int width) {
        return fields.setPropertyDataValue(IconDefinition.Width, width);
    }

    public int getWidth() {
        return (Integer) fields.getPropertyDataValue(IconDefinition.Width);
    }

    public boolean setHeight(int height) {
        return fields.setPropertyDataValue(IconDefinition.Height, height);
    }

    public int getHeight() {
        return (Integer) fields.getPropertyDataValue(IconDefinition.Height);
    }

    public boolean setDepth(int depth) {
        return fields.setPropertyDataValue(IconDefinition.Depth, depth);
    }

    public int getDepth() {
        return (Integer) fields.getPropertyDataValue(IconDefinition.Depth);
    }

    public boolean setUrl(String url) {
        return fields.setPropertyDataValue(IconDefinition.Url, url);
    }

    public String getUrl() {
        return (String) fields.getPropertyDataValue(IconDefinition.Url);
    }

    public Icon() {
        this.initialize();
    }

    private void initialize() {
        this.fields.initProperty(IconDefinition.MimeType, null);
        this.fields.initProperty(IconDefinition.Width, null);
        this.fields.initProperty(IconDefinition.Height, null);
        this.fields.initProperty(IconDefinition.Depth, null);
        this.fields.initProperty(IconDefinition.Url, null);
    }

    public Icon(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.fields = in.readParcelable(PropertyList.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.fields, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Icon> CREATOR = new Creator<Icon>() {
        public Icon createFromParcel(Parcel source) {
            return new Icon(source);
        }

        public Icon[] newArray(int size) {
            return new Icon[size];
        }
    };
}