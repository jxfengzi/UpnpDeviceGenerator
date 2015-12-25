package upnp.typedef.device.invocation;

import upnp.typedef.property.DataType;
import upnp.typedef.property.PropertyDefinition;

public class SubscriptionInfoDefinition {

    private static final String HOST_IP = "hostIp";
    private static final String HOST_PORT = "hostPort";
    private static final String DEVICE_ID = "deviceId";
    private static final String SERVICE_ID = "serviceId";
    private static final String EVENT_SUB_URL = "eventSubURL";
    private static final String SUBSCRIPTION_ID = "subscriptionId";
    private static final String SUBSCRIBED = "subscribed";
    private static final String TIMEOUT = "timeout";

    public static PropertyDefinition HostIp = new PropertyDefinition(HOST_IP, DataType.STRING);
    public static PropertyDefinition HostPort = new PropertyDefinition(HOST_PORT, DataType.INT);
    public static PropertyDefinition DeviceId = new PropertyDefinition(DEVICE_ID, DataType.STRING);
    public static PropertyDefinition ServiceId = new PropertyDefinition(SERVICE_ID, DataType.STRING);
    public static PropertyDefinition EventSubUrl = new PropertyDefinition(EVENT_SUB_URL, DataType.STRING);
    public static PropertyDefinition SubscriptionId = new PropertyDefinition(SUBSCRIPTION_ID, DataType.STRING);
    public static PropertyDefinition Subscribed = new PropertyDefinition(SUBSCRIBED, DataType.BOOLEAN);
    public static PropertyDefinition Timeout = new PropertyDefinition(TIMEOUT, DataType.INT);
}
