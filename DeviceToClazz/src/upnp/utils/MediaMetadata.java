package upnp.utils;

import java.net.URI;

public class MediaMetadata {

    private String title;
    private String creator;
    private String publisher;
    private String artist;
    private String album;
    private URI albumArtURI;
    private String albumArtUrl;

    public MediaMetadata(String title,
                         String creator,
                         String publisher,
                         String artist,
                         String album,
                         String albumArtUrl) {
        this.title = title;
        this.creator = creator;
        this.publisher = publisher;
        this.artist = artist;
        this.album = album;
        this.albumArtUrl = albumArtUrl;
    }

    public MediaMetadata(String title,
                         String creator,
                         String publisher,
                         String artist,
                         String album,
                         URI albumArtURI) {
        this.title = title;
        this.creator = creator;
        this.publisher = publisher;
        this.artist = artist;
        this.album = album;
        this.albumArtURI = albumArtURI;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public URI getAlbumArtURI() {
        return albumArtURI;
    }

    public void setAlbumArtURI(URI albumArtURI) {
        this.albumArtURI = albumArtURI;
    }

    public String getAlbumArtUrl() {
        return albumArtUrl;
    }

    public void setAlbumArtUrl(String albumArtUrl) {
        this.albumArtUrl = albumArtUrl;
    }

    @Override
    public String toString() {
        return MediaMetadataBuilder.create(this);
    }
}