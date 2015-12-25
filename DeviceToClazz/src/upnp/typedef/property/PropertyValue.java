
package upnp.typedef.property;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PropertyValue implements Parcelable {

    private static final String TAG = "PropertyValue";
    private boolean single = true;

    /**
     * for single value
     */
    private boolean isChanged = false;
    private boolean isOldValueAvailable = false;
    private volatile Object oldValue = null;
    private volatile Object currentValue = null;

    /**
     * for multiple value
     */
    private volatile List<Object> valueList = new ArrayList<Object>();

    public static PropertyValue create(Object value) {
        PropertyValue thiz = new PropertyValue();
        thiz.single = true;
        thiz.oldValue = value;
        thiz.isOldValueAvailable = true;
        return thiz;
    }

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }

    public boolean isMultiple() {
        return (! single);
    }

    public void setMultiple(boolean multiple) {
        this.single = (! multiple);
    }

    /**
     * for single value
     */
    public void update(Object value) {
        do {
            if (value == null) {
                Log.e(TAG, "value is null");
                break;
            }

            this.single = true;

            if (this.currentValue == null && value == null) {
                break;
            }

            if (this.oldValue != null && value != null) {
                if (!this.oldValue.getClass().equals(value.getClass())) {
                    Log.e(TAG, String.format("invalid: oldValue is %s, new value is %s (%s)",
                            oldValue.getClass().getSimpleName(),
                            value.getClass().getSimpleName(),
                            value.toString()));
                    break;
                }
            }

            if (this.currentValue == null) {
                this.currentValue = value;
                this.isChanged = true;
                break;
            }

            if (this.currentValue.equals(value)) {
                break;
            }

            this.oldValue = this.currentValue;
            this.isOldValueAvailable = true;
            this.currentValue = value;
            this.isChanged = true;
        } while (false);
    }

    public Object getValue() {
        if (this.currentValue != null) {
            return this.currentValue;
        }

        return this.oldValue;
    }

    public Object getCurrentValue() {
        return this.currentValue;
    }

    public boolean isChanged() {
        return this.isChanged;
    }

    public boolean isOldValueAvailable() {
        return this.isOldValueAvailable;
    }

    public Object getOldValue() {
        return this.oldValue;
    }

    public void cleanState() {
        this.isChanged = false;
    }

    /**
     * for multiple value
     */
    public List<Object> getValueList() {
        return valueList;
    }

    public void addMultipleValue(Object newValue) {
        this.single = false;
        this.valueList.add(newValue);
    }

    public static final Parcelable.Creator<PropertyValue> CREATOR = new Parcelable.Creator<PropertyValue>() {

        @Override
        public PropertyValue createFromParcel(Parcel in) {
            return new PropertyValue(in);
        }

        @Override
        public PropertyValue[] newArray(int size) {
            return new PropertyValue[size];
        }
    };

    public PropertyValue() {
    }

    public PropertyValue(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.single = (in.readInt() == 1);

        /**
         * for single value
         */
        if (this.single) {
            this.isChanged = (in.readInt() == 1);
            this.isOldValueAvailable = (in.readInt() == 1);

            if (in.readInt() == 1) {
                this.oldValue = in.readValue(Object.class.getClassLoader());
            }

            if (in.readInt() == 1) {
                this.currentValue = in.readValue(Object.class.getClassLoader());
            }
        }

        /**
         * for multiple value
         */
        else {
            int n = in.readInt();
            for (int i = 0; i < n; ++i) {
                Object v = in.readValue(Object.class.getClassLoader());
                valueList.add(v);
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(single ? 1 : 0);

        /**
         * for single value
         */
        if (single) {
            out.writeInt(this.isChanged ? 1 : 0);
            out.writeInt(this.isOldValueAvailable ? 1 : 0);

            if (this.oldValue == null) {
                out.writeInt(0);
            } else {
                out.writeInt(1);
                out.writeValue(this.oldValue);
            }

            if (this.currentValue == null) {
                out.writeInt(0);
            } else {
                out.writeInt(1);
                out.writeValue(this.currentValue);
            }
        }

        /**
         * for multiple value
         */
        else {
            int n = valueList.size();
            out.writeInt(n);

            for (int i = 0; i < n; ++i) {
                out.writeValue(valueList.get(i));
            }
        }
    }
}