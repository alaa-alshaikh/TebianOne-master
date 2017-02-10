package org.islamright.tebian.download;

import android.graphics.Bitmap;
import android.view.View;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.islamright.tebian.util.Util;

import java.util.concurrent.TimeoutException;

/**
 * Created by AlaaAlShaikh on 23/06/15.
 */
public class ImageLoaderListener implements ImageLoadingListener {
    private DownloadServiceOnFinishedListener downloadServiceOnFinishedListener;
    private int imageNumber;


    public ImageLoaderListener(DownloadServiceOnFinishedListener downloadServiceOnFinishedListener, int imageNumber) {
        this.downloadServiceOnFinishedListener = downloadServiceOnFinishedListener;
        this.imageNumber = imageNumber;
    }


    @Override
    public void onLoadingStarted(String s, View view) {

    }

    @Override
    public void onLoadingFailed(String s, View view, FailReason failReason) {
        downloadServiceOnFinishedListener.downloadFailed(new TimeoutException("tebian fetch image time out"));
    }

    @Override
    public void onLoadingComplete(String s, View view, Bitmap bitmap) {

        Util.saveImage(bitmap, imageNumber);
      //  downloadServiceOnFinishedListener.imageDownloaded(imageNumber);
    }

    @Override
    public void onLoadingCancelled(String s, View view) {
        downloadServiceOnFinishedListener.downloadFailed(new TimeoutException("tebian fetch image time out"));
    }
}
