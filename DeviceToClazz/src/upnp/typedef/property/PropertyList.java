
package upnp.typedef.property;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PropertyList implements Parcelable {

    private static final String TAG = PropertyList.class.getSimpleName();
    private List<Property> propertyList = new ArrayList<Property>();
    
    public boolean isChanged() {
        boolean changed = false;

        for (Property p : propertyList) {
            if (p.getPropertyValue().isChanged()) {
                changed = true;
                break;
            }
        }

        return changed;
    }

    public void cleanState() {
        for (Property p : propertyList) {
            p.getPropertyValue().cleanState();
        }
    }

    public List<Property> getList() {
        return this.propertyList;
    }

    public List<Property> getChangedPropertyList() {
        List<Property> list = null;
        
        do {
            for (Property p : propertyList) {
                if (p.getPropertyValue().isChanged()) {
                    if (list == null){
                        list = new ArrayList<Property>();
                    }

                    list.add(p);
                }
            }
        } while (false);

        return list;
    }

    public Property getProperty(String name) {
        Property property = null;
        
        for (Property p : propertyList) {
            if (p.getDefinition().getName().equals(name)) {
                property = p;
            }
        }

        return property;
    }
    
    public Property getProperty(PropertyDefinition definition) {
        Property property = null;

        for (Property p : propertyList) {
            if (p.getDefinition().equals(definition)) {
                property = p;
                break;
            }
        }

        return property;
    }

    public PropertyDefinition getPropertyDefinition(String name) {
        PropertyDefinition def = null;

        for (Property p : propertyList) {
            if (p.getDefinition().getName().equals(name)) {
                def = p.getDefinition();
                break;
            }
        }

        return def;
    }

    public PropertyValue getPropertyValue(PropertyDefinition definition) {
        PropertyValue value = null;

        for (Property p : propertyList) {
            if (p.getDefinition().equals(definition)) {
                value = p.getPropertyValue();
                break;
            }
        }
        
        return value;
    }

    public Object getPropertyDataValue(PropertyDefinition definition) {
        Object value = null;

        for (Property p : propertyList) {
            if (p.getDefinition().equals(definition)) {
                value = p.getCurrentValue();
                break;
            }
        }

        return value;
    }
    
    public Object getPropertyDataValue(String propertyName) {
        Object value = null;

        Property property = this.getProperty(propertyName);
        if (property != null) {
            value = property.getCurrentValue();
        }

        return value;
    }

    public void initProperty(PropertyDefinition definition, Object value) {
        Property property = new Property(definition, value);
        this.propertyList.add(property);
    }

    public void initProperty(Property property) {
        this.propertyList.add(property);
    }

    public boolean setPropertyDataValue(PropertyDefinition definition, Object value) {
        boolean ret = false;

        do {
            if (value == null) {
                break;
            }

            if (!definition.validate(value)) {
                Log.d(TAG, String.format("invalid value: %s", value));
                ret = false;
                break;
            }

            PropertyValue v = this.getPropertyValue(definition);
            if (v == null) {
                ret = false;
                break;
            }

            ret = true;
            v.update(value);
        } while (false);

        return ret;
    }

    public boolean setPropertyDataValue(String propertyName, Object value) {
        boolean ret = false;

        do {
            if (value == null) {
                break;
            }

            PropertyDefinition definition = getPropertyDefinition(propertyName);
            if (definition == null) {
                break;
            }

            ret = setPropertyDataValue(definition, value);
        } while (false);

        return ret;
    }

    public static final Parcelable.Creator<PropertyList> CREATOR = new Parcelable.Creator<PropertyList>() {

        @Override
        public PropertyList createFromParcel(Parcel in) {
            return new PropertyList(in);
        }

        @Override
        public PropertyList[] newArray(int size) {
            return new PropertyList[size];
        }
    };

    public PropertyList() {
    }

    public PropertyList(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        int n = in.readInt();
        for (int i = 0; i < n; i++) {
            Property p = in.readParcelable(Property.class.getClassLoader());
            propertyList.add(p);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(propertyList.size());
        for (Property p : propertyList) {
            out.writeParcelable(p, flags);
        }
    }
}
