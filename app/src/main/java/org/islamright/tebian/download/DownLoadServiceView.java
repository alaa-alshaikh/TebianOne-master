package org.islamright.tebian.download;

/**
 * Created by AlaaAlShaikh on 25/06/15.
 */
public interface DownLoadServiceView {
    void initService(int progressMax , int progressVale);
    void downloadFinished(int resString);
    void setProgressValue(int progressMax , int progressVale , int percentage);
}
