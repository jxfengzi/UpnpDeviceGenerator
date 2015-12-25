package upnp.utils;

//import org.dom4j.Document;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;

public class MediaMetadataBuilder {

    private static final String XMLNS = "urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/";
    private static final String XMLNS_UPNP = "urn:schemas-upnp-org:metadata-1-0/upnp/";
    private static final String XMLNS_DC = "http://purl.org/dc/elements/1.1/";
    private static final String XMLNS_DLNA = "urn:schemas-dlna-org:metadata-1-0/";
    private static final String IMAGE_ITEM = "object.item.imageItem";
    private static final String AUDIO_ITEM = "object.item.audioItem.musicTrack";

    private MediaMetadataBuilder(){
    }

    public static String create(MediaMetadata metadata) {
        if (metadata == null) {
            return "";
        }

        StringBuilder b = new StringBuilder(1024 * 4);
        b.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        b.append("<DIDL-Lite");
        b.append(" xmlns:dc=\"http://purl.org/dc/elements/1.1/\"");
        b.append(" xmlns:upnp=\"urn:schemas-upnp-org:metadata-1-0/upnp/\"");
        b.append(" xmlns=\"urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/\"");
        b.append(">");
        b.append("<item id=\"0\" restricted=\"0\" parentID=\"0\">");
        b.append("<dc:title>").append(metadata.getTitle() != null ? metadata.getTitle() : "Unknown").append("</dc:title>");
        b.append("<dc:creator>").append(metadata.getCreator() != null ? metadata.getCreator() : "Unknown Artist").append("</dc:creator>");
        b.append("<dc:publisher>").append(metadata.getPublisher() != null ? metadata.getPublisher() : "Unknown").append("</dc:publisher>");
        b.append("<upnp:class>object.item.audioItem.musicTrack</upnp:class>");
        b.append("<upnp:artist>").append(metadata.getArtist() != null ? metadata.getArtist() : "Unknown Artist").append("</upnp:artist>");
        b.append("<upnp:album>").append(metadata.getAlbum() != null ? metadata.getAlbum() : "Unknown").append("</upnp:album>");
        b.append("<upnp:albumArtURI>");
        b.append(metadata.getAlbumArtUrl() != null ? metadata.getAlbumArtUrl() : "Unknown");
        b.append("</upnp:albumArtURI>");
        b.append("</item>");
        b.append("</DIDL-Lite>");

        return b.toString();

//        Document document = DocumentHelper.createDocument();
//
//        Element root = document.addElement("DIDL-Lite")
//                .addNamespace("", XMLNS)
//                .addNamespace("upnp", XMLNS_UPNP)
//                .addNamespace("dc", XMLNS_DC)
//                .addNamespace("dlna", XMLNS_DLNA);
//
//        Element item = root.addElement("item", XMLNS);
//        item.addAttribute("id", "0")
//            .addAttribute("restricted", "0")
//            .addAttribute("parentID", "0");
//
//        item.addElement("dc:title").setText(metadata.getTitle() != null ? metadata.getTitle() : "Unknown");
//        item.addElement("dc:creator").setText(metadata.getCreator() != null ? metadata.getCreator() : "Unknown Artist");
//        item.addElement("dc:publisher").setText(metadata.getPublisher() != null ? metadata.getPublisher() : "Unknown");
//        item.addElement("upnp:class").setText(AUDIO_ITEM);
//        item.addElement("upnp:artist").setText(metadata.getArtist() != null ? metadata.getArtist() : "Unknown Artist");
//        item.addElement("upnp:album").setText(metadata.getAlbum() != null ? metadata.getAlbum() : "Unknown");
//        item.addElement("upnp:albumArtURI").setText(metadata.getAlbumArtUrl() != null ? metadata.getAlbumArtUrl() : "Unknown");
//
//        return document.asXML();
    }
}