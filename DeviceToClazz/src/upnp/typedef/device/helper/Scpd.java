package upnp.typedef.device.helper;

public class Scpd {

    public class Root {
        public static final String NAME = "scpd";
        public static final String XMLNS = "urn:schemas-upnp-org:service-1-0";
    }

    public class SpecVersion {
        public static final String NAME = "specVersion";

        public class Names {
            public static final String MAJOR = "major";
            public static final String MINOR = "minor";
        }

        public class Values {
            public static final int MAJOR_1 = 1;
            public static final int MINOR_0 = 0;
            public static final int MINOR_1 = 1;
        }
    }

    // <actionList>
    public static final String ACTIONS = "actionList";
    public static final String ACTION = "action";
    public static final String ACTION_NAME = "name";

    // <argumentList>
    public static final String ARGS = "argumentList";
    public static final String ARGS_ARG = "argument";
    public static final String ARGS_ARG_NAME = "name";
    public static final String ARGS_ARG_DIRECTION = "direction";
    public static final String ARGS_ARG_RELATEDPROPERTY = "relatedStateVariable";

    // <serviceStateTable>
    public static final String STATES = "serviceStateTable";
    public static final String STATE = "stateVariable";
    public static final String STATE_ATTR_SENDEVENTS = "sendEvents";
    public static final String STATE_NAME = "name";
    public static final String STATE_DATATYPE = "dataType";
    public static final String STATE_DEFAULT_VALUE = "defaultValue";

    // <allowedValueList>
    public static final String ALLOWED_VALUES = "allowedValueList";
    public static final String ALLOWED_VALUE = "allowedValue";

    // <allowedValueRange>
    public static final String ALLOWED_VALURANGE = "allowedValueRange";
    public static final String ALLOWED_VALURANGE_MIN = "minimum";
    public static final String ALLOWED_VALURANGE_MAX = "maximum";
    public static final String ALLOWED_VALURANGE_STEP = "step";
}