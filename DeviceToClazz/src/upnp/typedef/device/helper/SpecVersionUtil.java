package upnp.typedef.device.helper;

import android.util.Log;

import org.w3c.dom.Element;

import upnp.utils.XmlUtil;

public class SpecVersionUtil {

    private static final String TAG = "SpecVersionUtil";

    public static boolean parse(Element root) {
        boolean ret = false;

        do {
            Element element = XmlUtil.getChild(root, SpecVersion.NAME);
            if (element == null) {
                Log.d(TAG, String.format("<%s> not found", SpecVersion.NAME));
                break;
            }

            String strMajor = XmlUtil.getValue(element, SpecVersion.Names.MAJOR);
            if (strMajor == null) {
                Log.d(TAG, String.format("<%s> not found", SpecVersion.Names.MAJOR));
                break;
            }

            String strMinor = XmlUtil.getValue(element, SpecVersion.Names.MINOR);
            if (strMinor == null) {
                Log.d(TAG, String.format("<%s> not found", SpecVersion.Names.MINOR));
                break;
            }

            int major;
            int minor;

            try {
                major = Integer.valueOf(strMajor.trim());
                minor = Integer.valueOf(strMinor.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                break;
            }

            if (major != SpecVersion.Values.MAJOR_1) {
                Log.d(TAG, String.format("%s=%d %s=%d", SpecVersion.Names.MAJOR, major, SpecVersion.Names.MINOR, minor));
                break;
            }

            ret = true;
        } while (false);

        return ret;
    }
}