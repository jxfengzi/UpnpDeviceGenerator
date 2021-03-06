/* Automatic generated by DeviceToClazz */

package upnps.api.ctrlpoint.device.robot;

import android.os.Parcel;
import android.util.Log;

import miui.upnp.typedef.device.urn.DeviceType;
import miui.upnp.manager.ctrlpoint.AbstractDevice;
import miui.upnp.typedef.device.Device;
import miui.upnp.typedef.device.Service;

public class Robot extends AbstractDevice {

    private static final String TAG = Robot.class.getSimpleName();

    /**
     * deviceType & serviceType
     */
    public static final DeviceType DEVICE_TYPE = new DeviceType("Robot", "0.1");

    /**
     * serviceId
     */
    public static final String ID_RobotBind = "urn:upnp-org:serviceId:RobotBind";
    public static final String ID_RobotVoice = "urn:upnp-org:serviceId:RobotVoice";

    /**
     * services
     */

    private RobotBind _RobotBind;
    private RobotVoice _RobotVoice;

    public RobotBind getRobotBind() {
        return _RobotBind;
    }
    public RobotVoice getRobotVoice() {
        return _RobotVoice;
    }

    private static final Object classLock = Robot.class;

    public static Robot create(Device device) {
        Log.d(TAG, "create");

        synchronized (classLock) {
            Robot thiz = new Robot(device);

            do {
                if (! DEVICE_TYPE.equals(device.getDeviceType())) {
                    Log.d(TAG, "deviceType invalid: " + device.getDeviceType());
                    thiz = null;
                    break;
                }

                if (! thiz.initialize()) {
                    Log.d(TAG, "initialize failed");
                    thiz = null;
                    break;
                }
            } while (false);

            return thiz;
        }
    }

    private Robot(Device device) {
        this.device = device;
    }

    private boolean initialize() {
        boolean ret = false;

        do {
            Service theRobotBind = device.getService(ID_RobotBind);
            if (theRobotBind == null) {
                Log.d(TAG, "service not found: " + ID_RobotBind);
                break;
            }

            Service theRobotVoice = device.getService(ID_RobotVoice);
            if (theRobotVoice == null) {
                Log.d(TAG, "service not found: " + ID_RobotVoice);
                break;
            }

            _RobotBind = new RobotBind(theRobotBind);
            _RobotVoice = new RobotVoice(theRobotVoice);

            ret = true;
        } while (false);

        return ret;
    }

    public static final Creator<Robot> CREATOR = new Creator<Robot>() {

        @Override
        public Robot createFromParcel(Parcel in) {
            return new Robot(in);
        }

        @Override
        public Robot[] newArray(int size) {
            return new Robot[size];
         }
    };

    private Robot(Parcel in) {
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
