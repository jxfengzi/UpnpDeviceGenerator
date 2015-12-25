package upnp.typedef.device.helper;

import android.util.Log;

import upnp.typedef.device.Service;
import upnp.typedef.exception.InvalidDataTypeException;
import upnp.typedef.property.AllowedValueRange;
import upnp.typedef.property.DataType;
import upnp.typedef.device.Action;
import upnp.typedef.device.Argument;
import upnp.typedef.property.AllowedValueList;
import upnp.typedef.property.PropertyDefinition;
import upnp.utils.XmlUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ServiceHelper {

    private static final String TAG = "ServiceHelper";

    private ServiceHelper() {
    }

    public static boolean parse(Service service, String file) throws FileNotFoundException, InvalidDataTypeException {
        return parse(service, new FileInputStream(file));
    }

    public static boolean parse(Service service, InputStream stream) throws InvalidDataTypeException {
        boolean ret = false;

        do {
            if (service == null) {
                break;
            }

            if (stream == null) {
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
                document = builder.parse(stream);
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

            if (!parseXml(service, root)) {
                break;
            }

            ret = true;
        } while (false);

        return ret;
    }

    private static boolean parseXml(Service service, Element root) throws InvalidDataTypeException {
        boolean ret = false;

        do {
            if (!root.getTagName().equalsIgnoreCase(Scpd.Root.NAME)) {
                Log.d(TAG, String.format("<%s> not found", Scpd.Root.NAME));
                break;
            }

            String xmlns = root.getAttribute("xmlns");
            if (xmlns == null) {
                Log.d(TAG, "xmlns not found");
                break;
            }

            if (!xmlns.equals(Scpd.Root.XMLNS)) {
                Log.d(TAG, String.format("xmlns=%s not found", xmlns));
                break;
            }

            if (!parseActionList(service, root)) {
                Log.d(TAG, "parseActionList failed");
                break;
            }

            if (!parseServiceStateTable(service, root)) {
                Log.d(TAG, "parseServiceStateTable failed");
                break;
            }

            ret = true;
        } while (false);

        return ret;
    }

    private static boolean parseActionList(Service service, Element tag) {
        boolean ret = true;

        do {
            Element xmlActions = XmlUtil.getChild(tag, Scpd.ACTIONS);
            if (xmlActions == null) {
                Log.d(TAG, String.format("<%s> not found", Scpd.ACTIONS));
                ret = false;
                break;
            }

            NodeList nodeActions = xmlActions.getElementsByTagName(Scpd.ACTION);
            for (int i = 0; i < nodeActions.getLength(); ++i) {
                Element xmlAction = (Element) nodeActions.item(i);

                Action action = parseAction(xmlAction);
                if (action == null) {
                    Log.d(TAG, "parseAction failed");
                    ret = false;
                    break;
                }

                service.addAction(action);
            }
        } while (false);

        return ret;
    }

    private static Action parseAction(Element tag) {
        Action action = null;

        do {
            String name = XmlUtil.getValue(tag, Scpd.ACTION_NAME);
            if (name == null) {
                break;
            }

            action = new Action();
            action.setName(name);

            Element xmlArgumentList = XmlUtil.getChild(tag, Scpd.ARGS);
            if (xmlArgumentList == null) {
                Log.e(TAG, "xmlArgumentList is null: " + name);
                break;
            }

            NodeList nodeList = xmlArgumentList.getElementsByTagName(Scpd.ARGS_ARG);
            for (int i = 0; i < nodeList.getLength(); ++i) {
                Element xmlArg = (Element) nodeList.item(i);

                Argument arg = parseArgument(xmlArg);
                if (arg == null) {
                    Log.e(TAG, "parseArgument failed");
                    action = null;
                    break;
                }

                action.addArgument(arg);
            }
        } while (false);

        return action;
    }

    private static Argument parseArgument(Element tag) {
        Argument arg = null;

        do {
            String name = XmlUtil.getValue(tag, Scpd.ARGS_ARG_NAME);
            if (name == null) {
                Log.d(TAG, String.format("<%s> is null", Scpd.ARGS_ARG_NAME));
                break;
            }

            String direction = XmlUtil.getValue(tag, Scpd.ARGS_ARG_DIRECTION);
            if (direction == null) {
                Log.d(TAG, String.format("<%s> is null", Scpd.ARGS_ARG_DIRECTION));
                break;
            }

            String relatedStateVariable = XmlUtil.getValue(tag, Scpd.ARGS_ARG_RELATEDPROPERTY);
            if (relatedStateVariable == null) {
                Log.d(TAG, String.format("<%s> is null", Scpd.ARGS_ARG_RELATEDPROPERTY));
                arg = null;
                break;
            }

            arg = new Argument();
            arg.setDirection(direction);
            arg.setName(name);
            arg.setRelatedProperty(relatedStateVariable);
        } while (false);

        return arg;
    }

    private static boolean parseServiceStateTable(Service service, Element tag) throws InvalidDataTypeException {
        boolean ret = true;

        do {
            Element xmlProperties = XmlUtil.getChild(tag, Scpd.STATES);
            if (xmlProperties == null) {
                Log.d(TAG, String.format("<%s> not found", Scpd.STATES));
                ret = false;
                break;
            }

            NodeList nodeProperties = xmlProperties.getElementsByTagName(Scpd.STATE);
            for (int i = 0; i < nodeProperties.getLength(); ++i) {
                Element xmlPropertyDefinition = (Element) nodeProperties.item(i);

                PropertyDefinition definition = parsePropertyDefinition(xmlPropertyDefinition);
                if (definition == null) {
                    Log.d(TAG, "parsePropertyDefinition failed");
                    ret = false;
                    break;
                }

                service.addProperty(definition);
            }

        } while (false);

        return ret;
    }

    private static PropertyDefinition parsePropertyDefinition(Element tag) throws InvalidDataTypeException {
        PropertyDefinition def = null;

        do {
            String sendEvents = tag.getAttribute(Scpd.STATE_ATTR_SENDEVENTS);
            if (sendEvents == null) {
                break;
            }

            String name = XmlUtil.getValue(tag, Scpd.STATE_NAME);
            if (name == null) {
                break;
            }

            String dataTypeValue = XmlUtil.getValue(tag, Scpd.STATE_DATATYPE);
            if (dataTypeValue == null) {
                break;
            }

            DataType dataType = DataType.create(dataTypeValue);
            String defaultValue = XmlUtil.getValue(tag, Scpd.STATE_DEFAULT_VALUE);

            def = new PropertyDefinition();
            def.setName(name);
            def.setSendEvents(sendEvents);
            def.setDataType(dataType);
            def.setDefaultValue(defaultValue);

            // parse <allowedValueRange>
            Element eAllowedValueRange = XmlUtil.getChild(tag, Scpd.ALLOWED_VALURANGE);
            if (eAllowedValueRange != null) {
                AllowedValueRange range = parseAllowedValueRange(dataType, eAllowedValueRange);
                def.setAllowedValueRange(range);
            }

            // parse <allowedValueList>
            Element eAllowedValueList = XmlUtil.getChild(tag, Scpd.ALLOWED_VALUES);
            if (eAllowedValueList != null) {
                AllowedValueList list = parseAllowedValueList(dataType, eAllowedValueList);
                def.setAllowedValueList(list);
            }
        } while (false);

        return def;
    }

    private static AllowedValueList parseAllowedValueList(DataType dataType, Element element) {
        AllowedValueList list = new AllowedValueList(dataType);

        NodeList argumentList = element.getElementsByTagName(Scpd.ALLOWED_VALUE);
        for (int i = 0; i < argumentList.getLength(); ++i) {
            Element eAllowedValue = (Element) argumentList.item(i);
            String value = eAllowedValue.getTextContent();
            Object v = dataType.toObjectValue(value);
            list.appendAllowedValue(v);
        }

        return list;
    }

    private static AllowedValueRange parseAllowedValueRange(DataType dataType, Element element) {
        AllowedValueRange range = null;

        do {
            String strMin = XmlUtil.getValue(element, Scpd.ALLOWED_VALURANGE_MIN);
            String strMax = XmlUtil.getValue(element, Scpd.ALLOWED_VALURANGE_MAX);
            if (strMin == null || strMax == null) {
                break;
            }

            Object min = dataType.toObjectValue(strMin);
            Object max = dataType.toObjectValue(strMax);

            range = AllowedValueRange.create(dataType, min, max);
        } while (false);

        return range;
    }
}
