package upnp.typedef.device.helper;

public class Ddd {

    public class Root {
        public static final String NAME = "root";
        public static final String XMLNS = "urn:schemas-upnp-org:device-1-0";
        public static final String DEVICE = "device";

        public class Device {
            public static final String UDN = "UDN";
            public static final String DEVICE_TYPE = "deviceType";
            public static final String FRIENDLY_NAME = "friendlyName";
            public static final String MANUFACTURER = "manufacturer";
            public static final String MANUFACTURERURL = "manufacturerURL";
            public static final String MODELDESCRIPTION = "modelDescription";
            public static final String MODELNAME = "modelName";
            public static final String MODELNUMBER = "modelNumber";
            public static final String MODELURL = "modelURL";
            public static final String SERIALNUMBER = "serialNumber";
            public static final String PRESENTATIONURL = "presentationURL";
            public static final String UPC = "UPC";
            public static final String DLNA_DOC = "dlna:X_DLNADOC";
            public static final String DLNA_CAP = "dlna:X_DLNACAP";
            public static final String QQ_CAP = "qq:X_QPlay_SoftwareCapability";
            public static final String ICON_LIST = "iconList";
            public static final String SERVICE_LIST = "serviceList";

            public class IconList {
                public static final String ICON = "icon";

                public class Icon {
                    public static final String MIMETYPE = "mimetype";
                    public static final String WIDTH = "width";
                    public static final String HEIGHT = "height";
                    public static final String DEPTH = "depth";
                    public static final String URL = "url";
                }
            }

            public class ServiceList {
                public static final String SERVICE = "service";

                public class Service {
                    public static final String SERVICE_TYPE = "serviceType";
                    public static final String SERVICE_ID = "serviceId";
                    public static final String CONTROL_URL = "controlURL";
                    public static final String EVENT_SUB_URL = "eventSubURL";
                    public static final String SCPD_URL = "SCPDURL";
                }
            }
        }
    }
}