package upnp.typedef.device;

public enum DiscoveryType {

    UNDEFINED,
    UPNP,
    AIRTUNES;

    private static final String STR_UNDEFINED = "undefined";
    private static final String STR_UPNP = "upnp";
    private static final String STR_AIRTUNES = "airtunes";

    public static DiscoveryType retrieveType(String value) {
        if (value.equals(STR_UNDEFINED)) {
            return UNDEFINED;
        }

        if (value.equals(STR_UPNP)) {
            return UPNP;
        }

        if (value.equals(STR_AIRTUNES)) {
            return AIRTUNES;
        }

        return UNDEFINED;
    }

    @Override
    public String toString() {
        String value;

        switch (this) {
            case UPNP:
                value = STR_UPNP;
                break;

            case AIRTUNES:
                value = STR_AIRTUNES;
                break;

            default:
                value = STR_UNDEFINED;
                break;
        }

        return value;
    }
}