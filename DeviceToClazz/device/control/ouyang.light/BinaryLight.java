/* Automatic generated by DeviceToClazz */

package upnps.api.ctrlpoint.device.binarylight;

import android.os.Parcel;

import upnps.api.manager.ctrlpoint.device.AbstractDevice;
import upnp.typedef.device.Device;
import upnp.typedef.device.Service;

public class BinaryLight extends AbstractDevice {

    private SwitchPower _SwitchPower;

    public SwitchPower getSwitchPower() {
        return _SwitchPower;
    }

    private static final Object classLock = BinaryLight.class;
    private static final String DEVICE_TYPE = "BinaryLight";
    private static final String ID_SwitchPower = "urn:upnp-org:serviceId:SwitchPower:1";

    public static BinaryLight create(Device device) {
        synchronized (classLock) {
            BinaryLight thiz = new BinaryLight(device);
                
            do {
                if (! DEVICE_TYPE.equals(device.getDeviceType().getName())) {
                    thiz = null;
                    break;
                }

                if (! thiz.initialize()) {
                    thiz = null;
                    break;
                }
            } while (false);

            return thiz;
        }
    }

    private BinaryLight(Device device) {
        this.device = device;
    }

    private boolean initialize() {
        boolean ret = false;

        do {
            Service theSwitchPower = device.getService(ID_SwitchPower);
            if (theSwitchPower == null) {
                break;
            }

            _SwitchPower = new SwitchPower(theSwitchPower);

            ret = true;
        } while (false);

        return ret;
    }

    public static final Creator<BinaryLight> CREATOR = new Creator<BinaryLight>() {

        @Override
        public BinaryLight createFromParcel(Parcel in) {
            return new BinaryLight(in);
        }

        @Override
        public BinaryLight[] newArray(int size) {
            return new BinaryLight[size];
         }
    };

    private BinaryLight(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        device = in.readParcelable(Device.class.getClassLoader());
        initialize();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(device, flags);
    }
}