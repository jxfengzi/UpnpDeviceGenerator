
package upnp.typedef.device.urn;

/**
 * urn:schemas-mi-com:device:AirCondition:1
 * urn:[domain]:[type][subType][version]
 * urn:schemas-wifialliance-org:device:WFADevice:1
 * type = device | service
 */
public class Urn {

    private static final String URN = "urn";
    private String domain;
    private Type type = Type.UNDEFINED;
    private String subType;
    private String version;

    public enum Type {
        UNDEFINED,
        DEVICE,
        SERVICE;

        private static final String STR_UNDEFINED = "undefined";
        private static final String STR_DEVICE = "device";
        private static final String STR_SERVICE = "service";

        public static Type retrieveType(String value) {
            if (value.equals(STR_UNDEFINED)) {
                return UNDEFINED;
            }

            if (value.equals(STR_DEVICE)) {
                return DEVICE;
            }

            if (value.equals(STR_SERVICE)) {
                return SERVICE;
            }

            return UNDEFINED;
        }

        @Override
        public String toString() {
            String value = null;
            switch (this) {
                case DEVICE:
                    value = STR_DEVICE;
                    break;

                case SERVICE:
                    value = STR_SERVICE;
                    break;

                default:
                    value = STR_UNDEFINED;
                    break;
            }

            return value;
        }
    }

    public static Urn create(String domain, Type type, String subType, String version) {
        Urn thiz = new Urn();
        thiz.domain = domain;
        thiz.type = type;
        thiz.subType = subType;
        thiz.version = version;

        return thiz;
    }

    public static Urn create(String domain, Type type, String subType, float version) {
        return create(domain, type, subType, String.valueOf(version));
    }

    public static Urn create(String domain, Type type, String subType, int version) {
        return create(domain, type, subType, String.valueOf(version));
    }

    public boolean parse(String string) {
        boolean ret = false;

        do {
            String[] a = string.split(":");
            if (a.length != 5) {
                break;
            }

            if (!a[0].equals(URN)) {
                break;
            }

            domain = a[1];
            type = Type.retrieveType(a[2]);
            subType = a[3];

            try {
                this.version = a[4];
            } catch (NumberFormatException e) {
                break;
            }

            ret = true;
        } while (false);

        return ret;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        String string = String.format("%s:%s:%s:%s:%s",
                URN,
                this.domain,
                this.type.toString(),
                this.subType,
                this.version);

        return string;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((domain == null) ? 0 : domain.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((subType == null) ? 0 : subType.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean ret = false;

        do {
            if (obj == null) {
                break;
            }

            if (!(obj instanceof Urn)) {
                break;
            }

            Urn other = (Urn) obj;
            if (domain == null) {
                if (other.domain != null) {
                    return false;
                }
            } else if (!domain.equals(other.domain)) {
                return false;
            }

            if (type != other.type) {
                return false;
            }

            if (subType == null) {
                if (other.subType != null) {
                    return false;
                }
            } else if (!subType.equals(other.subType)) {
                return false;
            }

            if (! version.equals(other.version)) {
                return false;
            }

            ret = true;
        } while (false);

        return ret;
    }
}
