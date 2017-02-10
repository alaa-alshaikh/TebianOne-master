package org.islamright.tebian.network;

/**
 * Created by AlaaAlShaikh on 03/11/2015.
 */
public class HttpDownload {
    private static HttpDownload ourInstance = new HttpDownload();

    public static HttpDownload getInstance() {
        return ourInstance;
    }

    private HttpDownload() {

    }


}
