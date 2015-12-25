
package upnp.utils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtil {

    public static String getValue(Element node, String tagName) {
        String value = null;

        do {
            if (node == null) {
                break;
            }

            Element e = XmlUtil.getChild(node, tagName);
            if (e == null) {
                break;
            }
             
            value = e.getTextContent();
        } while (false);

        return value;
    }

    public static Element getChild(Element node, String tagName) {
        Element element = null;
        
        do {
            if (node == null) {
                break;
            }
            
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node child = childNodes.item(i);

                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    if (child.getNodeName().equals(tagName)) {
                        element = (Element)child;
                        break;
                    }
                }
            }
        } while (false);
            
        return element;
    }
}