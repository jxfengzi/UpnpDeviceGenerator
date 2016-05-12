package upnp.typedef.property;

import android.util.Log;

import upnp.typedef.exception.InvalidDataTypeException;

public enum DataType {
    UNKNOWN,
    BIN_BASE64,
    BIN_HEX,
    BOOLEAN,
    CHAR,
    DATE,
    DATETIME,
    DATETIME_TZ,
    FIXED_14_4,
    FLOAT,
    I1,
    I2,
    I4,
    INT,
    NUMBER,
    R4,
    R8,
    STRING,
    TIME,
    TIME_TZ,
    UI1,
    UI2,
    UI4,
    URI,
    UUID;

    private static final String TAG = DataType.class.getSimpleName();

    public boolean validate(Object min, Object max) {
        Class<?> clazz = this.getJavaDataType();

        if (clazz == String.class) {
            return false;
        }

        if (clazz == Boolean.class) {
            return false;
        }

        if (min.getClass() != max.getClass()) {
            return false;
        }

        if (min.getClass() != clazz) {
            return false;
        }

        if (clazz == Integer.class) {
            return (Integer)min <= (Integer)max;
        }

        if (clazz == Long.class) {
            return (Long)min <= (Long)max;
        }

        if (clazz == Float.class) {
            return (Float)min <= (Float)max;
        }

        if (clazz == Double.class) {
            return (Double)min <= (Double)max;
        }

        return false;
    }

    public boolean validate(Object min, Object value, Object max) {
        Class<?> clazz = this.getJavaDataType();

        if (clazz == String.class) {
            return false;
        }

        if (clazz == Boolean.class) {
            return false;
        }

        if (clazz != value.getClass()) {
            Log.d(TAG, "dataType invalid");
            return false;
        }

        if (clazz == Integer.class) {
            return ((Integer) min <= (Integer) value)
                    && ((Integer) value <= (Integer) max);
        }

        if (clazz == Long.class) {
            return ((Long) min <= (Long) value)
                    && ((Long) value <= (Long) max);
        }

        if (clazz == Float.class) {
            return Float.compare((Float) value, (Float) min) >= 0
                    && Float.compare((Float) max, (Float) value) >= 0;
        }

        if (clazz == Double.class) {
            return Double.compare((Double) value, (Double) min) >= 0
                    && Double.compare((Double) max, (Double) value) >= 0;
        }

        return false;
    }

    public Class<?> getJavaDataType() {
        switch (this) {
            case BIN_BASE64:
            case BIN_HEX:
            case STRING:
            case TIME:
            case TIME_TZ:
            case DATE:
            case DATETIME:
            case DATETIME_TZ:
            case FIXED_14_4:
            case URI:
            case UUID:
            case CHAR:
                return String.class;

            case I1:
            case I2:
            case INT:
            case NUMBER:
            case UI1:
            case UI2:
            case I4:
                return Integer.class;

            case UI4:
                return Long.class;

            case FLOAT:
            case R4:
                return Float.class;

            case R8:
                return Double.class;

            case BOOLEAN:
                return Boolean.class;
        }

        return null;
    }

    public String getCDataType() {
        switch (this) {
            case BIN_BASE64:
            case BIN_HEX:
            case STRING:
            case TIME:
            case TIME_TZ:
            case DATE:
            case DATETIME:
            case DATETIME_TZ:
            case FIXED_14_4:
            case URI:
            case UUID:
            case CHAR:
                return "char *";

            case I1:
            case I2:
            case INT:
            case NUMBER:
            case UI1:
            case UI2:
            case I4:
                return "int";

            case UI4:
                return "long";

            case FLOAT:
            case R4:
                return "float";

            case R8:
                return "double";

            case BOOLEAN:
                return "bool";
        }

        return null;
    }

    public String getCInternalValueName() {
        switch (this) {
            case BIN_BASE64:
            case BIN_HEX:
            case STRING:
            case TIME:
            case TIME_TZ:
            case DATE:
            case DATETIME:
            case DATETIME_TZ:
            case FIXED_14_4:
            case URI:
            case UUID:
            case CHAR:
                return "stringValue";

            case I1:
            case I2:
            case INT:
            case NUMBER:
            case UI1:
            case UI2:
            case I4:
                return "integerValue";

            case UI4:
                return "longValue";

            case FLOAT:
            case R4:
                return "floatValue";

            case R8:
                return "doubleValue";

            case BOOLEAN:
                return "boolValue";
        }

        return null;
    }

    public String getCDataTypeTransformFuncName() {
        switch (this) {
            case BIN_BASE64:
            case BIN_HEX:
            case STRING:
            case TIME:
            case TIME_TZ:
            case DATE:
            case DATETIME:
            case DATETIME_TZ:
            case FIXED_14_4:
            case URI:
            case UUID:
            case CHAR:
                //String data type do not transform
                return "Default";

            case I1:
            case I2:
            case INT:
            case NUMBER:
            case UI1:
            case UI2:
            case I4:
                return "DataType_StringToInt";

            case UI4:
                return "DataType_StringToLong";

            case FLOAT:
            case R4:
                return "DataType_StringToFloat";

            case R8:
                return "DataType_StringToDouble";

            case BOOLEAN:
                return "DataType_StringToBoolean";
        }

        return null;
    }

    public static DataType create(String type) throws InvalidDataTypeException {
        if (type.equals("bin.base64")) {
            return BIN_BASE64;
        }

        if (type.equals("bin.hex")) {
            return BIN_HEX;
        }

        if (type.equals("string")) {
            return STRING;
        }

        if (type.equals("time")) {
            return TIME;
        }

        if (type.equals("time.tz")) {
            return TIME_TZ;
        }

        if (type.equals("date")) {
            return DATE;
        }

        if (type.equals("dateTime")) {
            return DATETIME;
        }

        if (type.equals("dateTime.tz")) {
            return DATETIME_TZ;
        }

        if (type.equals("fixed.14.4")) {
            return FIXED_14_4;
        }

        if (type.equals("uri")) {
            return URI;
        }

        if (type.equals("uuid")) {
            return UUID;
        }

        if (type.equals("i1")) {
            return I1;
        }

        if (type.equals("i2")) {
            return I2;
        }

        if (type.equals("int")) {
            return INT;
        }

        if (type.equals("number")) {
            return NUMBER;
        }

        if (type.equals("ui1")) {
            return UI1;
        }

        if (type.equals("ui2")) {
            return UI2;
        }

        if (type.equals("i4")) {
            return I4;
        }

        if (type.equals("ui4")) {
            return UI4;
        }

        if (type.equals("float")) {
            return FLOAT;
        }

        if (type.equals("r4")) {
            return R4;
        }

        if (type.equals("r8")) {
            return R8;
        }

        if (type.equals("char")) {
            return CHAR;
        }

        if (type.equals("boolean")) {
            return BOOLEAN;
        }

        throw new InvalidDataTypeException(type);
    }

    public String getStringType() {
        switch (this) {
            case BIN_BASE64:
                return "bin.base64";

            case BIN_HEX:
                return "bin.hex";

            case STRING:
                return "string";

            case TIME:
                return "time";

            case TIME_TZ:
                return "time.tz";

            case DATE:
                return "date";

            case DATETIME:
                return "dateTime";

            case DATETIME_TZ:
                return "dateTime.tz";

            case FIXED_14_4:
                return "fixed.14.4";

            case URI:
                return "uri";

            case UUID:
                return "uuid";

            case I1:
                return "i1";

            case I2:
                return "i2";

            case INT:
                return "int";

            case NUMBER:
                return "number";

            case UI1:
                return "ui1";

            case UI2:
                return "ui2";

            case I4:
                return "i4";

            case UI4:
                return "ui4";

            case FLOAT:
                return "float";

            case R4:
                return "r4";

            case R8:
                return "r8";

            case CHAR:
                return "char";

            case BOOLEAN:
                return "boolean";
        }

        return null;
    }

    public String toStringValue(Object value) {
        Class<?> clazz = this.getJavaDataType();

        if (clazz == String.class) {
            return value.toString();
        }

        if (clazz == Integer.class) {
            return value.toString();
        }

        if (clazz == Long.class) {
            return value.toString();
        }

        if (clazz == Float.class) {
            return value.toString();
        }

        if (clazz == Double.class) {
            return value.toString();
        }

        if (clazz == Boolean.class) {
            return BooleanValueToString((Boolean) value);
        }

        return null;
    }

    public Object toObjectValue(String string) {
        Class<?> clazz = this.getJavaDataType();

        try {
            if (clazz == String.class) {
                return string;
            }

            if (clazz == Integer.class) {
                return Integer.valueOf(string);
            }

            if (clazz == Long.class) {
                return Long.valueOf(string);
            }

            if (clazz == Float.class) {
                return Float.valueOf(string);
            }

            if (clazz == Double.class) {
                return Double.valueOf(string);
            }

            if (clazz == Boolean.class) {
                return DataType.BooleanValueOf(string);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object createObjectValue() {
        Class<?> clazz = this.getJavaDataType();

        try {
            if (clazz == String.class) {
                return "";
            }

            if (clazz == Integer.class) {
                return 0;
            }

            if (clazz == Long.class) {
                return 0L;
            }

            if (clazz == Float.class) {
                return Float.valueOf(0.0f);
            }

            if (clazz == Double.class) {
                return 0.0;
            }

            if (clazz == Boolean.class) {
                return Boolean.FALSE;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Boolean BooleanValueOf(String string) {
        if (string == null) {
            return false;
        }

        String v = string.toUpperCase();

        if (v.equals("1") || v.equals("YES") || v.equals("TRUE")) {
            return true;
        }

        if (v.equals("0") || v.equals("NO") || v.equals("FALSE")) {
            return false;
        }

        Log.e(TAG, "invalid value: " + string);

        return false;
    }

    public static String BooleanValueToString(boolean b) {
        return b ? "1" : "0";
    }
}