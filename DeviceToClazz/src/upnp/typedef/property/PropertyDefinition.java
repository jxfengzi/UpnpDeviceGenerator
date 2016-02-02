
package upnp.typedef.property;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class PropertyDefinition implements Parcelable {

    private static final String TAG = PropertyDefinition.class.getSimpleName();

    private boolean sendEvents;
    private String name;
    private DataType dataType;
    private String defaultValue;
    private AllowedValueType allowedValueType = AllowedValueType.ANY;
    private AllowedValueList allowedValueList;
    private AllowedValueRange allowedValueRange;

    public PropertyDefinition(String n, DataType t) {
        this.setName(n);
        this.setDataType(t);
    }

    public boolean isSendEvents() {
        return sendEvents;
    }

    public void setSendEvents(String sendEvents) {
        this.sendEvents = DataType.BooleanValueOf(sendEvents);
    }

    public void setSendEvents(boolean sendEvents) {
        this.sendEvents = sendEvents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public AllowedValueType getAllowedValueType() {
        return allowedValueType;
    }

    public void setAllowedValueType(AllowedValueType allowedValueType) {
        this.allowedValueType = allowedValueType;
    }

    public AllowedValueList getAllowedValueList() {
        return allowedValueList;
    }

    public void setAllowedValueList(AllowedValueList allowedValueList) {
        this.allowedValueType = AllowedValueType.LIST;
        this.allowedValueList = allowedValueList;
    }

    public AllowedValueRange getAllowedValueRange() {
        return allowedValueRange;
    }

    public void setAllowedValueRange(AllowedValueRange allowedValueRange) {
        this.allowedValueType = AllowedValueType.RANGE;
        this.allowedValueRange = allowedValueRange;
    }

    public boolean validate(Object value) {
        if (value == null) {
            return false;
        }

        if (! dataType.getJavaDataType().equals(value.getClass())) {
            Log.e(TAG, String.format("dataType is %s, value type is %s, invalid!",
                    dataType.getJavaDataType().getSimpleName(),
                    value.getClass().getSimpleName()));
            return false;
        }

        if (allowedValueList != null) {
            return allowedValueList.isValid(value);
        }

        if (allowedValueRange != null) {
            return allowedValueRange.isValid(value);
        }

        return true;
    }

    public Object valueOf(String value) {
        return dataType.toObjectValue(value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        PropertyDefinition other = (PropertyDefinition) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
            
        } else if (!name.equals(other.name)) {
            return false;
        }

        return true;
    }

    public static final Creator<PropertyDefinition> CREATOR = new Parcelable.Creator<PropertyDefinition>() {

        @Override
        public PropertyDefinition createFromParcel(Parcel in) {
            return new PropertyDefinition(in);
        }

        @Override
        public PropertyDefinition[] newArray(int size) {
            return new PropertyDefinition[size];
        }
    };

    public PropertyDefinition() {
    }

    public PropertyDefinition(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        boolean a[] = new boolean[1];
        in.readBooleanArray(a);
        this.sendEvents = a[0];

        this.name = in.readString();
        this.dataType = DataType.valueOf(in.readString());
        this.defaultValue = in.readString();

        this.allowedValueType = AllowedValueType.retrieveType(in.readInt());
        switch (this.allowedValueType) {
            case ANY:
                break;

            case LIST:
                this.allowedValueList = in.readParcelable(AllowedValueList.class.getClassLoader());
                break;

            case RANGE:
                this.allowedValueRange = in.readParcelable(AllowedValueRange.class.getClassLoader());
                break;

            default:
                break;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        boolean a[] = new boolean[1];
        a[0] = this.sendEvents;
        out.writeBooleanArray(a);

        out.writeString(this.name);
        out.writeString(this.dataType.toString());
        out.writeString(this.defaultValue);

        out.writeInt(this.allowedValueType.toInt());
        switch (this.allowedValueType) {
            case ANY:
                break;

            case LIST:
                out.writeParcelable(this.allowedValueList, flags);
                break;

            case RANGE:
                out.writeParcelable(this.allowedValueRange, flags);
                break;

            default:
                break;
        }
    }
}