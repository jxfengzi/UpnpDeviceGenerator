
package upnp.typedef.device;

import upnp.typedef.property.DataType;
import upnp.typedef.property.PropertyDefinition;

public class ServiceDefinition {

    /**
     *  UPNP
     */
    private static final String SERVICE_ID = "serviceId";
    private static final String CONTROL_URL = "controlURL";
    private static final String EVENT_SUB_URL = "eventSubURL";
    private static final String SCPDURL = "SCPDURL";
    private static final String SCPDFILE = "SCPDFILE";

    public static PropertyDefinition ServiceId = new PropertyDefinition(SERVICE_ID, DataType.STRING);
    public static PropertyDefinition ControlUrl = new PropertyDefinition(CONTROL_URL, DataType.STRING);
    public static PropertyDefinition EventSubUrl = new PropertyDefinition(EVENT_SUB_URL, DataType.STRING);
    public static PropertyDefinition ScpdUrl = new PropertyDefinition(SCPDURL, DataType.STRING);
    public static PropertyDefinition ScpdFile = new PropertyDefinition(SCPDFILE, DataType.STRING);

    /**
     *  Subscription
     */
    private static final String SUBSCRIBED = "subscribed";
    private static final String SUBSCRIPTION_ID = "subscriptionId";

    public static PropertyDefinition Subscribed = new PropertyDefinition(SUBSCRIBED, DataType.BOOLEAN);
    public static PropertyDefinition SubscriptionId = new PropertyDefinition(SUBSCRIPTION_ID, DataType.STRING);
}