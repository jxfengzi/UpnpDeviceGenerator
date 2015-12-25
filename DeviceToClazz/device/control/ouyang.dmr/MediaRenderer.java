/* Automatic generated by DeviceToClazz */

package upnps.api.ctrlpoint.device.mediarenderer;

import android.os.Parcel;

import upnps.api.manager.ctrlpoint.device.AbstractDevice;
import upnp.typedef.device.Device;
import upnp.typedef.device.Service;

public class MediaRenderer extends AbstractDevice {

    private AVTransport _AVTransport;
    private ConnectionManager _ConnectionManager;
    private RenderingControl _RenderingControl;

    public AVTransport getAVTransport() {
        return _AVTransport;
    }
    public ConnectionManager getConnectionManager() {
        return _ConnectionManager;
    }
    public RenderingControl getRenderingControl() {
        return _RenderingControl;
    }

    private static final Object classLock = MediaRenderer.class;
    private static final String DEVICE_TYPE = "MediaRenderer";
    private static final String ID_AVTransport = "urn:upnp-org:serviceId:AVTransport";
    private static final String ID_ConnectionManager = "urn:upnp-org:serviceId:ConnectionManager";
    private static final String ID_RenderingControl = "urn:upnp-org:serviceId:RenderingControl";

    public static MediaRenderer create(Device device) {
        synchronized (classLock) {
            MediaRenderer thiz = new MediaRenderer(device);
                
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

    private MediaRenderer(Device device) {
        this.device = device;
    }

    private boolean initialize() {
        boolean ret = false;

        do {
            Service theAVTransport = device.getService(ID_AVTransport);
            if (theAVTransport == null) {
                break;
            }

            Service theConnectionManager = device.getService(ID_ConnectionManager);
            if (theConnectionManager == null) {
                break;
            }

            Service theRenderingControl = device.getService(ID_RenderingControl);
            if (theRenderingControl == null) {
                break;
            }

            _AVTransport = new AVTransport(theAVTransport);
            _ConnectionManager = new ConnectionManager(theConnectionManager);
            _RenderingControl = new RenderingControl(theRenderingControl);

            ret = true;
        } while (false);

        return ret;
    }

    public static final Creator<MediaRenderer> CREATOR = new Creator<MediaRenderer>() {

        @Override
        public MediaRenderer createFromParcel(Parcel in) {
            return new MediaRenderer(in);
        }

        @Override
        public MediaRenderer[] newArray(int size) {
            return new MediaRenderer[size];
         }
    };

    private MediaRenderer(Parcel in) {
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