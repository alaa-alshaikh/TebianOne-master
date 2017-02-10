package org.islamright.tebian.download;

import android.content.Context;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.islamright.tebian.App;
import org.islamright.tebian.database.DatabaseHelper;
import org.islamright.tebian.model.Page;
import org.islamright.tebian.network.Apis;
import org.islamright.tebian.network.HttpRequest;
import org.islamright.tebian.util.Logging;
import org.islamright.tebian.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 *
 */
public class DownloadServiceInterActorImpl implements DownloadServiceInterActor {

    private DownloadServiceOnFinishedListener downloadServiceOnFinishedListener;
    private ImageTarget imageTarget;
    private Context mContext;
    boolean first = true;

    public DownloadServiceInterActorImpl(DownloadServiceOnFinishedListener downloadServiceOnFinishedListener, Context mContext) {
        this.downloadServiceOnFinishedListener = downloadServiceOnFinishedListener;
        this.mContext = mContext;
    }

    @Override
    public void downloadFile() {

        Logging.d(DownloadServiceInterActorImpl.class, "here");
        Ion.with(mContext)
                .load(Apis.FILE)

                .progress(new ProgressCallback() {
                    @Override
                    public void onProgress(long downloaded, long total) {


                        int percentage = (int) ((downloaded * 100L) / total);
                        downloadServiceOnFinishedListener.downloadProgress(Util.safeLongToInt(downloaded), Util.safeLongToInt(total), percentage);
                    }
                })
                .write(new File(Apis.FILE_LOCATION))
                .setCallback(new FutureCallback<File>() {
                    @Override
                    public void onCompleted(Exception e, File file) {
                        // download done...
                        // do stuff with the File or error
                        if (e != null)
                            downloadServiceOnFinishedListener.downloadFailed(e);
                        else
                            downloadServiceOnFinishedListener.fileDownloaded(file.getAbsolutePath());
                    }

                });
    }


//    @Override
//    public void downLoadPage(final int pageNumber) {
//        HttpRequest.getInstance().apiService.getPage(pageNumber, new Callback<ArrayList<Page>>() {
//            @Override
//            public void success(ArrayList<Page> pages, Response response) {
//                if (pages.size() >0)
//                    DatabaseHelper.getInstance().insertPageComponent(pages.get(0));
//                downloadServiceOnFinishedListener.pageDownloaded(pageNumber);
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                downloadServiceOnFinishedListener.downloadFailed(error);
//            }
//        });
//    }

//    @Override
//    public void downLoadImage(int imageNumber) {
//        ImageLoaderListener imageLoaderListener = new ImageLoaderListener(downloadServiceOnFinishedListener, imageNumber);
//        ImageLoader.getInstance().loadImage(String.format(Locale.ENGLISH, Apis.IMAGE, imageNumber), imageLoaderListener);
//    }
}
