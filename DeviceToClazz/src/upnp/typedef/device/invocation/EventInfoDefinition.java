package upnp.typedef.device.invocation;

import upnp.typedef.property.DataType;
import upnp.typedef.property.PropertyDefinition;

public class EventInfoDefinition {

    private static final String DEVICE_ID = "deviceId";
    private static final String SERVICE_ID = "serviceId";

    public static PropertyDefinition DeviceId = new PropertyDefinition(DEVICE_ID, DataType.STRING);
    public static PropertyDefinition ServiceId = new PropertyDefinition(SERVICE_ID, DataType.STRING);
}