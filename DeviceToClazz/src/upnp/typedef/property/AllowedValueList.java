
package upnp.typedef.property;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class AllowedValueList implements Parcelable {

    private static final String TAG = AllowedValueList.class.getSimpleName();

    private DataType dataType;
    private ArrayList<Object> allowedValues = new ArrayList<Object>();

    public AllowedValueList(DataType t) {
        this.dataType = t;
    }

    public ArrayList<Object> getAllowedValues() {
        return allowedValues;
    }

    public boolean appendAllowedValue(Object value) {
        boolean append = false;

        do {
            if (!this.dataType.getJavaDataType().isInstance(value)) {
                Log.d(TAG, "append dataType invalid");
                break;
            }

            allowedValues.add(value);
            append = true;
        } while (false);

        return append;
    }

    public boolean isValid(Object value) {
        boolean valid = false;

        do {
            if (!this.dataType.getJavaDataType().isInstance(value)) {
                Log.d(TAG, "dataType invalid");
                break;
            }

            valid = allowedValues.contains(value);
            if (! valid) {
                // value is "NONE"
                if (value instanceof String) {
                    if (((String) value).equalsIgnoreCase("NONE")) {
                        Log.e(TAG, "value is \"NONE\", As a legal value to process!");
                        valid = true;
                        break;
                    }
                }

                // LOG
                StringBuilder builder = new StringBuilder(1024);
                builder.append(valid);
                builder.append("invalid, ");
                builder.append("value must be one of these: ");
                for (Object v : allowedValues) {
                    builder.append(v);
                    builder.append(",");
                }
                Log.e(TAG, builder.toString());
            }
        } while (false);

        return valid;
    }

    public static final Creator<AllowedValueList> CREATOR = new Parcelable.Creator<AllowedValueList>() {

        @Override
        public AllowedValueList createFromParcel(Parcel in) {
            return new AllowedValueList(in);
        }

        @Override
        public AllowedValueList[] newArray(int size) {
            return new AllowedValueList[size];
        }
    };

    private AllowedValueList() {
    }

    public AllowedValueList(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.dataType = DataType.valueOf(in.readString());
        in.readList(allowedValues, Object.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.dataType.toString());
        out.writeList(allowedValues);
    }
}
