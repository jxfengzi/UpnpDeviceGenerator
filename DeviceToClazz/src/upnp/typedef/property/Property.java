
package upnp.typedef.property;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Property implements Parcelable {

    private static final String TAG = Property.class.getSimpleName();
    private PropertyDefinition definition;
    private volatile PropertyValue value;

    public Property() {
    }

    public Property(PropertyDefinition definition, Object value) {
        PropertyValue v = PropertyValueUtil.createByType(definition.getDataType().getJavaDataType(), value);
        this.init(definition, v);
    }

    private void init(PropertyDefinition definition, PropertyValue value) {
        if (value == null) {
            value = PropertyValueUtil.createByType(definition.getDataType().getJavaDataType());
        }

        this.definition = definition;
        this.value = value;
    }

    public PropertyDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(PropertyDefinition definition) {
        this.definition = definition;
        this.value = PropertyValueUtil.createByType(definition.getDataType().getJavaDataType());
    }

    public PropertyValue getPropertyValue() {
        return value;
    }

    public Object getOldValue() {
        return this.value.getOldValue();
    }

    public Object getCurrentValue() {
        return this.value.getValue();
    }

    public String getOldStringValue() {
        return definition.getDataType().toStringValue(this.value.getOldValue());
    }

    public String getCurrentStringValue() {
        return definition.getDataType().toStringValue(this.value.getValue());
    }

    private boolean isMultipleValue(Object value) {
        boolean multiple = false;

        if (this.definition.getAllowedValueType() == AllowedValueType.LIST) {
            if (value instanceof String) {
                String data = (String)value;
                String a[] = data.split(",");
                if (a.length > 1) {
                    multiple = true;
                }
            }
        }

        return multiple;
    }

    public boolean setDataValue(Object newValue) {
        boolean ret = true;

        do {
            if (newValue == null) {
                Log.e(TAG, "newValue is null");
                ret = false;
                break;
            }

            /**
             * for multiple value
             */
            boolean multiple = false;
            if (this.definition.getAllowedValueType() == AllowedValueType.LIST) {
                if (newValue instanceof String) {
                    String string = (String)newValue;
                    String a[] = string.split(",");
                    if (a.length > 1) {
                        multiple = true;
                        Log.d(TAG, "multiple value: " + string);

                        for (String v : a) {
                            if (!this.definition.validate(v)) {
                                Log.e(TAG, String.format("invalid value: %s, skip it!", v));
                                continue;
                            }

                            value.addMultipleValue(v);
                        }
                    }
                }
            }

            if (multiple) {
                break;
            }

            /**
             * for single value
             */
            if (!definition.validate(newValue)) {
                Log.e(TAG, String.format("invalid value: %s", newValue));
                ret = false;
                break;
            }

            value.update(newValue);
        } while (false);

        return ret;
    }

    public boolean setDataValueByString(String string, boolean nullValueAllowed) {
//        Log.d(TAG, String.format("setDataValueByString: %s", string));

        boolean ret = false;

        do {
            Object value = definition.getDataType().toObjectValue(string);
            if (value == null) {
                if (nullValueAllowed) {
                    ret = true;
                    break;
                }

                Log.d(TAG, "value is null");
                ret = false;
                break;
            }

            if (! this.setDataValue(value)) {
                Log.e(TAG, String.format("%s setDataValue failed: %s(%s), dataType is: %s",
                        definition.getName(),
                        string,
                        value.getClass().getSimpleName(),
                        definition.getDataType().getStringType()));
                break;
            }

            ret = true;
        } while (false);

        return ret;
    }

    public static final Parcelable.Creator<Property> CREATOR = new Parcelable.Creator<Property>() {

        @Override
        public Property createFromParcel(Parcel in) {
            return new Property(in);
        }

        @Override
        public Property[] newArray(int size) {
            return new Property[size];
        }
    };

    public Property(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.definition = in.readParcelable(PropertyDefinition.class.getClassLoader());
        this.value = in.readParcelable(PropertyValue.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.definition, flags);
        out.writeParcelable(this.value, flags);
    }
}
