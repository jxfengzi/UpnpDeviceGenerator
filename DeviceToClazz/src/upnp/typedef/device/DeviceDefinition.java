
package upnp.typedef.device;

import upnp.typedef.property.DataType;
import upnp.typedef.property.PropertyDefinition;

public class DeviceDefinition {

    // Basic Info
    private static final String LOCATION = "location";
    private static final String HOST_IP = "hostIp";
    private static final String HOST_PORT = "hostPort";
    private static final String LOCATION_FILE = "locationFile";

    public static PropertyDefinition Location = new PropertyDefinition(LOCATION, DataType.STRING);
    public static PropertyDefinition LocationFile = new PropertyDefinition(LOCATION_FILE, DataType.STRING);
    public static PropertyDefinition HostIp = new PropertyDefinition(HOST_IP, DataType.STRING);
    public static PropertyDefinition HostPort = new PropertyDefinition(HOST_PORT, DataType.INT);

    // Device Info
    private static final String UDN = "UDN";
    private static final String FRIENDLY_NAME = "friendlyName";
    private static final String MANUFACTURER = "manufacturer";
    private static final String MANUFACTURER_URL = "manufacturerURL";
    private static final String MODEL_DESCRIPTION = "modelDescription";
    private static final String MODEL_NAME = "modelName";
    private static final String MODEL_NUMBER = "modelNumber";
    private static final String MODEL_URL = "modelURL";
    private static final String SERIAL_NUMBER = "serialNumber";
    private static final String PRESENTATION_URL = "presentationURL";
    private static final String URLBASE = "URLBase";

    public static PropertyDefinition DeviceId = new PropertyDefinition(UDN, DataType.STRING);
    public static PropertyDefinition FriendlyName = new PropertyDefinition(FRIENDLY_NAME, DataType.STRING);
    public static PropertyDefinition Manufacturer = new PropertyDefinition(MANUFACTURER, DataType.STRING);
    public static PropertyDefinition ManufacturerUrl = new PropertyDefinition(MANUFACTURER_URL, DataType.STRING);
    public static PropertyDefinition ModelDescription = new PropertyDefinition(MODEL_DESCRIPTION, DataType.STRING);
    public static PropertyDefinition ModelName = new PropertyDefinition(MODEL_NAME, DataType.STRING);
    public static PropertyDefinition ModelNumber = new PropertyDefinition(MODEL_NUMBER, DataType.STRING);
    public static PropertyDefinition ModelUrl = new PropertyDefinition(MODEL_URL, DataType.STRING);
    public static PropertyDefinition SerialNumber = new PropertyDefinition(SERIAL_NUMBER, DataType.STRING);
    public static PropertyDefinition PresentationUrl = new PropertyDefinition(PRESENTATION_URL, DataType.STRING);
    public static PropertyDefinition UrlBase = new PropertyDefinition(URLBASE, DataType.STRING);

    // External Info
    private static final String UPC = "UPC";
    private static final String QPLAY_CAPABILITY = "qq:X_QPlay_SoftwareCapability";
    private static final String DLNA_DOC = "dlna:X_DLNADOC";
    private static final String DLNA_CAP = "dlna:X_DLNACAP";

    public static PropertyDefinition Upc = new PropertyDefinition(UPC, DataType.STRING);
    public static PropertyDefinition QplayCapability = new PropertyDefinition(QPLAY_CAPABILITY, DataType.STRING);
    public static PropertyDefinition DlnaDoc = new PropertyDefinition(DLNA_DOC, DataType.STRING);
    public static PropertyDefinition DlnaCap = new PropertyDefinition(DLNA_CAP, DataType.STRING);
}