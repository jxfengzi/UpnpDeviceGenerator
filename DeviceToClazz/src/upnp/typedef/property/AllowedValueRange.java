
package upnp.typedef.property;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class AllowedValueRange implements Parcelable {

    private static final String TAG = AllowedValueRange.class.getSimpleName();

    private DataType dataType;
    private Object minValue;
    private Object maxValue;

    public static AllowedValueRange create(DataType type, Object min, Object max) {
        AllowedValueRange thiz = null;

        do {
            boolean valid = false;

            if (!type.getJavaDataType().isInstance(min)) {
                Log.d(TAG, "create failed, min dataType invalid");
                break;
            }

            if (!type.getJavaDataType().isInstance(max)) {
                Log.d(TAG, "create failed, max dataType invalid");
                break;
            }

            if (! type.validate(min, max)) {
                Log.d(TAG, "create failed, min >= max");
                break;
            }

            thiz = new AllowedValueRange();
            thiz.dataType = type;
            thiz.minValue = min;
            thiz.maxValue = max;
        } while (false);

        return thiz;
    }

    public Object getMinValue() {
        return minValue;
    }

    public Object getMaxValue() {
        return maxValue;
    }

    public boolean isValid(Object value) {
        return dataType.validate(minValue, value, maxValue);
    }

    public static final Creator<AllowedValueRange> CREATOR = new Parcelable.Creator<AllowedValueRange>() {

        @Override
        public AllowedValueRange createFromParcel(Parcel in) {
            return new AllowedValueRange(in);
        }

        @Override
        public AllowedValueRange[] newArray(int size) {
            return new AllowedValueRange[size];
        }
    };

    private AllowedValueRange() {
    }

    public AllowedValueRange(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        dataType = DataType.valueOf(in.readString());
        minValue = dataType.toObjectValue(in.readString());
        maxValue = dataType.toObjectValue(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(dataType.toString());
        out.writeString(dataType.toStringValue(minValue));
        out.writeString(dataType.toStringValue(maxValue));
   }
}