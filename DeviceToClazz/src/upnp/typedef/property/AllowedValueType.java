package upnp.typedef.property;

/**
 * Created by ouyang on 15-9-14.
 */
public enum AllowedValueType {
    ANY,
    LIST,
    RANGE;

    public static AllowedValueType retrieveType(int value) {
        switch (value) {
            case 0:
                return ANY;

            case 1:
                return LIST;

            case 2:
                return RANGE;
        }

        return ANY;
    }

    public int toInt() {
        switch (this) {
            case ANY:
                return 0;

            case LIST:
                return 1;

            case RANGE:
                return 2;
        }

        return 0;
    }
}
