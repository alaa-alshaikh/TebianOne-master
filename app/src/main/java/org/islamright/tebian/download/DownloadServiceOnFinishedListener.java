package org.islamright.tebian.download;

/**
 * Created by AlaaAlShaikh on 27/04/15.
 */
public interface DownloadServiceOnFinishedListener {

   // void pageDownloaded(int pageNumber);

   // void imageDownloaded(int imageNumber);
   void fileDownloaded(String location);
    void downloadFailed(Exception e);
    void downloadProgress(int downloaded, int total, int percentage);
}
