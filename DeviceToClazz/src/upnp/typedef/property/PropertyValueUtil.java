package upnp.typedef.property;

import android.util.Log;

import java.lang.reflect.Constructor;

/**
 * Created by ouyang on 15-9-17.
 */
public class PropertyValueUtil {

    private static final String TAG = "PropertyValueUtil";

    public static PropertyValue createByType(Class<?> type, Object value) {
        PropertyValue v = null;

        do {
            if (value == null) {
                v = createByType(type);
                break;
            }

            if (type.equals(value.getClass())) {
                v = PropertyValue.create(value);
                break;
            }

            Log.e(TAG, String.format("invalid: type is %s, init value is %s (%s)",
                    type.getSimpleName(),
                    value.getClass().getSimpleName(),
                    value.toString()));

            v = createByType(type);
        } while (false);

        return v;
    }

    public static PropertyValue createByType(Class<?> type) {
        Object value = null;

        try {
            Constructor<?>[] constructors = type.getConstructors();
            for (Constructor<?> c : constructors) {
                if (c.getParameterTypes().length == 0) {
                    value = type.newInstance();
                    break;
                }

                if (c.getParameterTypes().length == 1) {
                    if (type == Boolean.class) {
                        value = Boolean.valueOf(false);
                    } else if (type == Long.class) {
                        value = Long.valueOf(0);
                    } else if (type == Integer.class) {
                        value = Integer.valueOf(0);
                    } else if (type == Float.class) {
                        value = Float.valueOf(0.0f);
                    } else if (type == Double.class) {
                        value = Double.valueOf(0.0f);
                    }
                    break;
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return PropertyValue.create(value);
    }
}
