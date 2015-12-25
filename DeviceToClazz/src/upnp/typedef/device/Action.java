
package upnp.typedef.device;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Action implements Parcelable {

    private Service service;
    private String name;
    private List<Argument> arguments = new ArrayList<Argument>();

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Argument> getArguments() {
        return this.arguments;
    }

    public void addArgument(Argument argument) {
        this.arguments.add(argument);
    }

    public static final Parcelable.Creator<Action> CREATOR = new Parcelable.Creator<Action>() {

        @Override
        public Action createFromParcel(Parcel in) {
            return new Action(in);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };

    public Action() {
    }

    public Action(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.name = in.readString();

        int n = in.readInt();
        for (int i = 0; i < n; i++) {
            String name = in.readString();
            String direction = in.readString();
            String releatedStateVariable = in.readString();
            Argument a = new Argument(name, direction, releatedStateVariable);
            this.arguments.add(a);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.name);

        out.writeInt(this.arguments.size());
        for (Argument a : this.arguments) {
            out.writeString(a.getName());
            out.writeString(a.getDirection().toString());
            out.writeString(a.getRelatedProperty());
        }
    }
}
