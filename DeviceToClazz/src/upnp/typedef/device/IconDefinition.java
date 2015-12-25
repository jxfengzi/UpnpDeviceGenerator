package upnp.typedef.device;

import upnp.typedef.property.DataType;
import upnp.typedef.property.PropertyDefinition;

public class IconDefinition {

    private static final String MIME_TYPE = "mimetype";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DEPTH = "depth";
    private static final String URL = "url";

    public static PropertyDefinition MimeType = new PropertyDefinition(MIME_TYPE, DataType.STRING);
    public static PropertyDefinition Width = new PropertyDefinition(WIDTH, DataType.INT);
    public static PropertyDefinition Height = new PropertyDefinition(HEIGHT, DataType.INT);
    public static PropertyDefinition Depth = new PropertyDefinition(DEPTH, DataType.INT);
    public static PropertyDefinition Url = new PropertyDefinition(URL, DataType.STRING);
}