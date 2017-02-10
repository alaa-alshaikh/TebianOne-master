package org.islamright.tebian.download;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.islamright.tebian.util.Util;

import java.util.concurrent.TimeoutException;

/**
 * Created by AlaaAlShaikh on 07/05/15.
 */
public class ImageTarget implements Target {

    private DownloadServiceOnFinishedListener downloadServiceOnFinishedListener;
    private int imageNumber;


    public ImageTarget(DownloadServiceOnFinishedListener downloadServiceOnFinishedListener, int imageNumber) {
        this.downloadServiceOnFinishedListener = downloadServiceOnFinishedListener;
        this.imageNumber = imageNumber;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        Util.saveImage(bitmap , imageNumber);
       // downloadServiceOnFinishedListener.imageDownloaded(imageNumber);
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        downloadServiceOnFinishedListener.downloadFailed(new TimeoutException("tebian fetch image time out"));
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}
