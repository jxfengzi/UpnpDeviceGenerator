package upnp.utils;

import upnp.typedef.exception.UpnpException;

public class PlayingTime {

    private int hour;
    private int minute;
    private int second;

    public static PlayingTime create(int seconds) {
        PlayingTime t = new PlayingTime();
        t.setSeconds(seconds);
        return t;
    }

    public static PlayingTime create(String duration) throws UpnpException {
        PlayingTime t = new PlayingTime();

        String a[] = duration.split(":");
        if (a.length != 3) {
            throw new UpnpException("duration invalid: " + duration);
        }

        try {
            t.setHour(Integer.valueOf(a[0]));
            t.setMinute(Integer.valueOf(a[1]));
            t.setSecond(Integer.valueOf(a[2]));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new UpnpException("duration invalid: " + duration);
        }

        return t;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setSeconds(int seconds) {
        setHour(seconds / 3600);
        setMinute((seconds / 60) % 60);
        setSecond(seconds % 60);
    }

    public int getSeconds() {
        return hour * 3600 + minute * 60 + second;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}