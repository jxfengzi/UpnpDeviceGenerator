
package upnp.typedef;

public class ErrorCode {
    public static final int OK = 0;

    // generic
    public static final int E_INVALID_PARAM = 1;
    public static final int E_EXCEPTION = 2;
    public static final int E_STARTED = 3;
    public static final int E_STOPPED = 4;
    public static final int E_INVALID = 5;
    public static final int E_ACTION_NOT_SUPPORT = 6;
    public static final int E_ARGUMENT_INVALID = 7;
    public static final int E_NOT_IMPLEMENTED = 8;
    public static final int E_NOT_INITIALIZED = 9;
    public static final int E_INTERRUPTED = 10;
    public static final int E_INTERNAL = 11;

    // service
    public static final int E_SERVICE = 2000;
    public static final int E_SERVICE_NOT_BOUND = E_SERVICE + 1;
    public static final int E_SERVICE_VERSION_NOT_SUPPORT = E_SERVICE + 2;

    // subscribe
    public static final int E_SUBSCRIBE = 4000;
    public static final int E_SUBSCRIBE_PROPERTY = (E_SUBSCRIBE + 1);

    // SSDP
    public static final int E_SSDP = 10000;

    // SOAP
    public static final int E_SOAP = 11000;

    // GENA
    public static final int E_GENA = 12000;
    public static final int E_GENA_SUBSCRIBED = (E_GENA + 1);
    public static final int E_GENA_UNSUBSCRIBED = (E_GENA + 2);
    public static final int E_GENA_SUBSCRIBE_FAILED = (E_GENA + 3);

    // Service
    public static final int E_EVENT = 20000;
    public static final int E_EVENT_SUBSCRIBED = E_EVENT + 1;
    public static final int E_EVENT_UNSUBSCRIBED = E_EVENT + 1;

    // Invoke
    public static final int E_INVOKE = 30000;

    // Register
    public static final int E_REGISTER = 40000;
    public static final int E_REGISTER_REGISTERED = E_REGISTER + 1;
    public static final int E_REGISTER_NOT_REGISTERED = E_REGISTER + 2;

    // AP
    public static final int E_WIFI = 9000;
    public static final int E_WIFI_SSID = E_WIFI + 1;
    public static final int E_WIFI_PASSWORD = E_WIFI + 2;
    public static final int E_WIFI_AP_ENABLED = E_WIFI + 3;
    public static final int E_WIFI_DISABLED = E_WIFI + 4;
    public static final int E_WIFI_SET = E_WIFI + 5;
    public static final int E_WIFI_CONNECT_FAILED = E_WIFI + 6;
    public static final int E_WIFI_CONFIG = E_WIFI + 7;
    public static final int E_WIFI_ADD_NETWORK = E_WIFI + 8;
    public static final int E_WIFI_ENABLE = E_WIFI + 9;

    // Session
    public static final int E_SESSION = 10000;
    public static final int E_SESSION_CREATE_FAILED = E_SESSION + 1;
    public static final int E_SESSION_DESTROY_FAILED = E_SESSION + 2;
    public static final int E_SESSION_ALREADY_CONNECTED = E_SESSION + 3;
    public static final int E_SESSION_ALREADY_DISCONNECTED = E_SESSION + 4;
    public static final int E_SESSION_CONNECT_FAILED = E_SESSION + 5;
}