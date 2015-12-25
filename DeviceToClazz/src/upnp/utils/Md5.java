package upnp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

    private String text = null;
    private String hash = null;

    public Md5(String text) {
        this.text = text;
    }

    public String getMd5() {
        if (text == null) {
            return null;
        } else {
            return makeMd5();
        }
    }

    public String getMd5(String text) {
        this.text = text;

        if (text == null) {
            return null;
        } else {
            return makeMd5();
        }
    }

    private String makeMd5() {
        MessageDigest md = null;
        byte[] encryptMsg = null;

        try {
            md = MessageDigest.getInstance("MD5");
            encryptMsg = md.digest(text.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm Exception!");
        }

        String swap = "";
        String byteStr = "";
        StringBuffer strBuf = new StringBuffer();

        for (int i = 0; i <= encryptMsg.length - 1; i++) {
            byteStr = Integer.toHexString(encryptMsg[i]);
            switch (byteStr.length()) {
                case 1:
                    swap = "0" + Integer.toHexString(encryptMsg[i]);
                    break;

                case 2:
                    swap = Integer.toHexString(encryptMsg[i]);
                    break;

                case 8:
                    swap = (Integer.toHexString(encryptMsg[i])).substring(6, 8);
                    break;
            }
            strBuf.append(swap);
        }
        hash = strBuf.toString();

        return hash;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getHash() {
        return hash;
    }
}