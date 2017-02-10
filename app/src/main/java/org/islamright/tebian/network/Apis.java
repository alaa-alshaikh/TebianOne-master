package org.islamright.tebian.network;

import org.islamright.tebian.App;
import org.islamright.tebian.util.Constant;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface Apis {
    public static final String MAIN_URL = "http://qur2an.duxi.co/";

    public static final String PAGE  = "/index.php/generating-api-mobile-app/{pageNumber}";
    public static final String IMAGE = "http://qur2an.duxi.co/pages/gif/%1$d.gif";

    public static final String FILE = "http://qur2an.duxi.co/upload/thumbnails/qur2an.zip";
    public static final String FILE_LOCATION =  "/sdcard/qur2an.zip";


    public static final String FILE_EXPLANATION = "http://qur2an.duxi.co/upload/thumbnails/";
    public static final String EXPLANATION_LIST  = "/index.php/{list}";
}
