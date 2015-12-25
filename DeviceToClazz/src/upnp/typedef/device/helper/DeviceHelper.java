package upnp.typedef.device.helper;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import upnp.typedef.device.Device;
import upnp.typedef.device.Icon;
import upnp.typedef.device.Service;
import upnp.typedef.device.urn.DeviceType;
import upnp.typedef.device.urn.ServiceType;
import upnp.utils.XmlUtil;

public class DeviceHelper {

    private static final String TAG = "DeviceHelper";

    private DeviceHelper() {
    }

    public static boolean parse(Device device, InputStream is) {
        boolean ret = false;

        do {
            if (device == null) {
                break;
            }

            if (is == null) {
                break;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            Document document;

            try {
                builder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
                break;
            }

            try {
                document = builder.parse(is);
            } catch (SAXException e) {
                e.printStackTrace();
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            Element root = document.getDocumentElement();
            if (root == null) {
                Log.d(TAG, "<root> not found");
                break;
            }

            if (!parseXml(device, root)) {
                break;
            }

            ret = true;
        } while (false);

        return ret;
    }

    private static boolean parseXml(Device device, Element root) {
        boolean ret = false;

        do {
            if (!root.getTagName().equalsIgnoreCase(Ddd.Root.NAME)) {
                Log.d(TAG, String.format("<%s> not found", Ddd.Root.NAME));
                break;
            }

            String xmlns = root.getAttribute("xmlns");
            if (xmlns == null) {
                Log.d(TAG, "xmlns not found");
                break;
            }

            if (!xmlns.equals(Ddd.Root.XMLNS)) {
                Log.d(TAG, String.format("xmlns=%s not found", xmlns));
                break;
            }

            if (!SpecVersionUtil.parse(root)) {
                Log.d(TAG, "SpecVersionUtil.parse failed");
                break;
            }

            if (!parseDevice(device, root)) {
                Log.d(TAG, "parseXmlDevice failed");
                break;
            }

            ret = true;
        } while (false);

        return ret;
    }

    private static boolean parseDevice(Device device, Element root) {
        boolean ret = false;

        do {
            Element tag = XmlUtil.getChild(root, Ddd.Root.DEVICE);
            if (tag == null) {
                Log.d(TAG, String.format("<%s> not found", Ddd.Root.DEVICE));
                break;
            }

            // <UDN>
            if (!device.setDeviceId(XmlUtil.getValue(tag, Ddd.Root.Device.UDN))) {
                Log.d(TAG, String.format("<%s>: invalid", Ddd.Root.Device.UDN));
                break;
            }

            // <deviceType>
            String deviceType = XmlUtil.getValue(tag, Ddd.Root.Device.DEVICE_TYPE);
            if (deviceType == null) {
                Log.d(TAG, String.format("<%s>: not found", Ddd.Root.Device.DEVICE_TYPE));
                break;
            }

            DeviceType type = DeviceType.create(deviceType);
            if (type == null) {
                Log.d(TAG, String.format("invalid: %s", deviceType));
            }

            device.setDeviceType(type);

            // <FriendlyName>
            if (!device.setFriendlyName(XmlUtil.getValue(tag, Ddd.Root.Device.FRIENDLY_NAME))) {
                Log.d(TAG, String.format("<%s>: invalid", Ddd.Root.Device.FRIENDLY_NAME));
                break;
            }

            // optional
            device.setManufacturer(XmlUtil.getValue(tag, Ddd.Root.Device.MANUFACTURER));
            device.setManufacturerUrl(XmlUtil.getValue(tag, Ddd.Root.Device.MANUFACTURERURL));
            device.setModelDescription(XmlUtil.getValue(tag, Ddd.Root.Device.MODELDESCRIPTION));
            device.setModelName(XmlUtil.getValue(tag, Ddd.Root.Device.MODELNAME));
            device.setModelNumber(XmlUtil.getValue(tag, Ddd.Root.Device.MODELNUMBER));
            device.setModelUrl(XmlUtil.getValue(tag, Ddd.Root.Device.MODELURL));
            device.setSerialNumber(XmlUtil.getValue(tag, Ddd.Root.Device.SERIALNUMBER));
            device.setPresentationUrl(XmlUtil.getValue(tag, Ddd.Root.Device.PRESENTATIONURL));
            device.setUpc(XmlUtil.getValue(tag, Ddd.Root.Device.UPC));
            device.setDlnaDoc(XmlUtil.getValue(tag, Ddd.Root.Device.DLNA_DOC));
            device.setDlnaCap(XmlUtil.getValue(tag, Ddd.Root.Device.DLNA_CAP));
            device.setQplayCapability(XmlUtil.getValue(tag, Ddd.Root.Device.QQ_CAP));

            // <IconList> is optional
            parseIconList(device, tag);

            if (!parseServiceList(device, tag)) {
                Log.d(TAG, "parseServiceList failed");
                break;
            }

            ret = true;
        } while (false);

        return ret;
    }

    private static boolean parseIconList(Device device, Element tag) {
        boolean ret = true;

        do {
            Element xmlIconlist = XmlUtil.getChild(tag, Ddd.Root.Device.ICON_LIST);
            if (xmlIconlist == null) {
                ret = false;
                break;
            }

            NodeList nodeIcons = xmlIconlist.getElementsByTagName(Ddd.Root.Device.IconList.ICON);
            for (int i = 0; i < nodeIcons.getLength(); ++i) {
                Element xmlIcon = (Element) nodeIcons.item(i);

                Icon icon = parseIcon(xmlIcon);
                if (icon == null) {
                    Log.d(TAG, "parseIcon failed");
                    ret = false;
                    break;
                }

                /**
                 * fix icon url:
                 *
                 * location = http://10.0.10.143:41571/dev/9edf1281-cb67-35cf-ffff-ffffc7831a22/desc.xml
                 *
                 * (1)
                 * icon = icon/icon048x048.jpeg
                 * Icon RealUrl = http://10.0.10.143:41571/dev/9edf1281-cb67-35cf-ffff-ffffc7831a22/icon/icon048x048.jpeg
                 *
                 * (2)
                 * icon = /upnphost/udhisapi.dll?content=uuid:ef6b545f-9dfb-411e-a93c-3bf6f349b163
                 * Icon RealUrl = http://10.0.10.143:41571/upnphost/udhisapi.dll?content=uuid:ef6b545f-9dfb-411e-a93c-3bf6f349b163
                 */
                if (icon.getUrl().startsWith("/")) {
                    StringBuilder urlBuilder = new StringBuilder(1024);
                    urlBuilder.append(device.getUrlBase());
                    urlBuilder.append(icon.getUrl());
                    icon.setUrl(urlBuilder.toString());
                }
                else {
                    String url = device.getLocation();
                    int index = url.lastIndexOf("/");
                    StringBuilder urlBuilder = new StringBuilder(1024);
                    urlBuilder.append(url.substring(0, index + 1));
                    urlBuilder.append(icon.getUrl());
                    icon.setUrl(urlBuilder.toString());
                }

                device.addIcon(icon);
            }
        } while (false);

        return ret;
    }

    public static Icon parseIcon(Element tag) {
        Icon icon = null;

        do {
            String mimeType = XmlUtil.getValue(tag, Ddd.Root.Device.IconList.Icon.MIMETYPE);
            if (mimeType == null) {
                break;
            }

            String width = XmlUtil.getValue(tag, Ddd.Root.Device.IconList.Icon.WIDTH);
            if (width == null) {
                break;
            }

            String height = XmlUtil.getValue(tag, Ddd.Root.Device.IconList.Icon.HEIGHT);
            if (height == null) {
                break;
            }

            String depth = XmlUtil.getValue(tag, Ddd.Root.Device.IconList.Icon.DEPTH);
            if (depth == null) {
                break;
            }

            String url = XmlUtil.getValue(tag, Ddd.Root.Device.IconList.Icon.URL);
            if (url == null) {
                break;
            }

            icon = new Icon();
            icon.setMimeType(mimeType);
            icon.setUrl(url);

            try {
                icon.setWidth(Integer.valueOf(width));
                icon.setHeight(Integer.valueOf(height));
                icon.setDepth(Integer.valueOf(depth));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                break;
            }
        } while (false);

        return icon;
    }

    private static boolean parseServiceList(Device device, Element tag) {
        boolean ret = true;

        do {
            Element xmlServicelist = XmlUtil.getChild(tag, Ddd.Root.Device.SERVICE_LIST);
            if (xmlServicelist == null) {
                Log.d(TAG, String.format("<%s> not found", Ddd.Root.Device.SERVICE_LIST));
                ret = false;
                break;
            }

            NodeList nodeServices = xmlServicelist.getElementsByTagName(Ddd.Root.Device.ServiceList.SERVICE);
            for (int i = 0; i < nodeServices.getLength(); ++i) {
                Element xmlService = (Element) nodeServices.item(i);

                Service s = parseService(xmlService);
                if (s == null) {
                    Log.d(TAG, "parse failed");
                    ret = false;
                    break;
                }

                device.addService(s);
            }
        } while (false);

        return ret;
    }

    public static Service parseService(Element tag) {
        Service service = null;

        do {
            String serviceType = XmlUtil.getValue(tag, Ddd.Root.Device.ServiceList.Service.SERVICE_TYPE);
            if (serviceType == null) {
                break;
            }

            String serviceId = XmlUtil.getValue(tag, Ddd.Root.Device.ServiceList.Service.SERVICE_ID);
            if (serviceId == null) {
                break;
            }

            String ctrlUrl = XmlUtil.getValue(tag, Ddd.Root.Device.ServiceList.Service.CONTROL_URL);
            if (ctrlUrl == null) {
                break;
            }

            String subUrl = XmlUtil.getValue(tag, Ddd.Root.Device.ServiceList.Service.EVENT_SUB_URL);
            if (subUrl == null) {
                break;
            }

            String scpdUrl = XmlUtil.getValue(tag, Ddd.Root.Device.ServiceList.Service.SCPD_URL);
            if (scpdUrl == null) {
                break;
            }

            ServiceType type = new ServiceType();
            if (! type.parse(serviceType)) {
                Log.e(TAG, serviceType + " invalid!");
                break;
            }

            service = new Service();
            service.setType(type);
            service.setServiceId(serviceId);
            service.setControlUrl(ctrlUrl);
            service.setEventSubUrl(subUrl);
            service.setScpdUrl(scpdUrl);
        } while (false);

        return service;
    }
}