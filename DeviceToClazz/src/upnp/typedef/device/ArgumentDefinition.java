
package upnp.typedef.device;

import upnp.typedef.property.DataType;
import upnp.typedef.property.PropertyDefinition;

public class ArgumentDefinition {

    private static final String NAME = "name";
    private static final String DIRECTION = "direction";
    private static final String RELATED_PROPERTY = "relatedStateVariable";

    public static PropertyDefinition Name = new PropertyDefinition(NAME, DataType.STRING);
    public static PropertyDefinition Direction = new PropertyDefinition(DIRECTION, DataType.STRING);
    public static PropertyDefinition RelatedProperty = new PropertyDefinition(RELATED_PROPERTY, DataType.STRING);
}