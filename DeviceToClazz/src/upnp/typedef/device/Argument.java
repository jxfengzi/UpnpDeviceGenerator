package upnp.typedef.device;

import android.os.Parcel;
import android.os.Parcelable;

import upnp.typedef.property.PropertyList;

public class Argument implements Parcelable {

    private PropertyList fields = new PropertyList();

    public enum Direction {

        UNDEFINED,
        IN,
        OUT;

        private static final String STR_undefined = "undefined";
        private static final String STR_in = "in";
        private static final String STR_out = "out";

        public static Direction retrieveType(String value) {
            if (value.equals(STR_undefined)) {
                return UNDEFINED;
            }

            if (value.equals(STR_in)) {
                return IN;
            }

            if (value.equals(STR_out)) {
                return OUT;
            }

            return UNDEFINED;
        }

        @Override
        public String toString() {
            String value = null;

            switch (this) {
                case IN:
                    value = STR_in;
                    break;

                case OUT:
                    value = STR_out;
                    break;

                default:
                    value = STR_undefined;
                    break;
            }

            return value;
        }
    }

    public Argument(String name, String direction, String relatedStateVariable) {
        this.initialize();
        this.setName(name);
        this.setDirection(direction);
        this.setRelatedProperty(relatedStateVariable);
    }

    public String getName() {
        return (String) fields.getPropertyDataValue(ArgumentDefinition.Name);
    }

    public boolean setName(String name) {
        return fields.setPropertyDataValue(ArgumentDefinition.Name, name);
    }

    public Direction getDirection() {
        return Direction.retrieveType((String) fields.getPropertyDataValue(ArgumentDefinition.Direction));
    }

    public boolean setDirection(Direction direction) {
        return this.setDirection(direction.toString());
    }

    public boolean setDirection(String direction) {
        return fields.setPropertyDataValue(ArgumentDefinition.Direction, direction);
    }

    public String getRelatedProperty() {
        return (String) fields.getPropertyDataValue(ArgumentDefinition.RelatedProperty);
    }

    public boolean setRelatedProperty(String relatedStateVariable) {
        return fields.setPropertyDataValue(ArgumentDefinition.RelatedProperty, relatedStateVariable);
    }

    public static final Parcelable.Creator<Argument> CREATOR = new Parcelable.Creator<Argument>() {

        @Override
        public Argument createFromParcel(Parcel in) {
            return new Argument(in);
        }

        @Override
        public Argument[] newArray(int size) {
            return new Argument[size];
        }
    };

    public Argument() {
        this.initialize();
    }

    private void initialize() {
        this.fields.initProperty(ArgumentDefinition.Name, null);
        this.fields.initProperty(ArgumentDefinition.Direction, null);
        this.fields.initProperty(ArgumentDefinition.RelatedProperty, null);
    }

    public Argument(Parcel in) {
        this.initialize();

        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.fields = in.readParcelable(PropertyList.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.fields, flags);
    }
}