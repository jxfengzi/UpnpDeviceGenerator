package upnp.typedef.device;

import upnp.typedef.property.DataType;
import upnp.typedef.property.PropertyDefinition;

public class SubscriptionDefinition {

    private static final String CALLBACK_URL = "callbackUrl";
    private static final String SUBSCRIPTION_ID = "subscriptionId";
    private static final String TIMEOUT = "timeout";

    public static PropertyDefinition CallbackUrl = new PropertyDefinition(CALLBACK_URL, DataType.STRING);
    public static PropertyDefinition SubscriptionId = new PropertyDefinition(SUBSCRIPTION_ID, DataType.STRING);
    public static PropertyDefinition Timeout = new PropertyDefinition(TIMEOUT, DataType.INT);
}