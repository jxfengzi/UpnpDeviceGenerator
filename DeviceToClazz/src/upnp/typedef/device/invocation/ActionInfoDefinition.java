
package upnp.typedef.device.invocation;

import upnp.typedef.property.DataType;
import upnp.typedef.property.PropertyDefinition;

public class ActionInfoDefinition {

    private static final String HOST_IP = "hostIp";
    private static final String HOST_PORT = "hostPort";
    private static final String CONTROL_URL = "controlURL";
    private static final String DEVICE_ID = "deviceId";

    public static PropertyDefinition HostIp = new PropertyDefinition(HOST_IP, DataType.STRING);
    public static PropertyDefinition HostPort = new PropertyDefinition(HOST_PORT, DataType.INT);
    public static PropertyDefinition ControlUrl = new PropertyDefinition(CONTROL_URL, DataType.STRING);
    public static PropertyDefinition DeviceId = new PropertyDefinition(DEVICE_ID, DataType.STRING);
}