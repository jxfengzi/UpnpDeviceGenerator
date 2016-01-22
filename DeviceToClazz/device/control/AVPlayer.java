/* Automatic generated by DeviceToClazz */

package upnps.api.ctrlpoint.device.avplayer;

import android.os.Parcel;
import android.util.Log;

import upnp.typedef.device.urn.DeviceType;
import upnps.manager.ctrlpoint.device.AbstractDevice;
import upnp.typedef.device.Device;
import upnp.typedef.device.Service;

public class AVPlayer extends AbstractDevice {

    private static final String TAG = AVPlayer.class.getSimpleName();

    /**
     * deviceType & serviceType
     */
    public static final DeviceType DEVICE_TYPE = new DeviceType("AVPlayer", "1");

    /**
     * serviceId
     */
    public static final String ID_AVTransport = "urn:upnp-org:serviceId:AVTransport";
    public static final String ID_RenderingControl = "urn:upnp-org:serviceId:RenderingControl";
    public static final String ID_SessionManager = "urn:upnp-org:serviceId:SessionManager";

    /**
     * services
     */

    private AVTransport _AVTransport;
    private RenderingControl _RenderingControl;
    private SessionManager _SessionManager;

    public AVTransport getAVTransport() {
        return _AVTransport;
    }
    public RenderingControl getRenderingControl() {
        return _RenderingControl;
    }
    public SessionManager getSessionManager() {
        return _SessionManager;
    }

    private static final Object classLock = AVPlayer.class;

    public static AVPlayer create(Device device) {
        Log.d(TAG, "create");

        synchronized (classLock) {
            AVPlayer thiz = new AVPlayer(device);

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

    private AVPlayer(Device device) {
        this.device = device;
    }

    private boolean initialize() {
        boolean ret = false;

        do {
            Service theAVTransport = device.getService(ID_AVTransport);
            if (theAVTransport == null) {
                Log.d(TAG, "service not found: " + ID_AVTransport);
                break;
            }

            Service theRenderingControl = device.getService(ID_RenderingControl);
            if (theRenderingControl == null) {
                Log.d(TAG, "service not found: " + ID_RenderingControl);
                break;
            }

            Service theSessionManager = device.getService(ID_SessionManager);
            if (theSessionManager == null) {
                Log.d(TAG, "service not found: " + ID_SessionManager);
                break;
            }

            _AVTransport = new AVTransport(theAVTransport);
            _RenderingControl = new RenderingControl(theRenderingControl);
            _SessionManager = new SessionManager(theSessionManager);

            ret = true;
        } while (false);

        return ret;
    }

    public static final Creator<AVPlayer> CREATOR = new Creator<AVPlayer>() {

        @Override
        public AVPlayer createFromParcel(Parcel in) {
            return new AVPlayer(in);
        }

        @Override
        public AVPlayer[] newArray(int size) {
            return new AVPlayer[size];
         }
    };

    private AVPlayer(Parcel in) {
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
