package org.islamright.tebian.download;

import android.content.Context;

import org.islamright.tebian.R;
import org.islamright.tebian.util.Logging;

public class DownloadServicePresenterImpl implements DownloadServicePresenter, DownloadServiceOnFinishedListener {

    private DownLoadServiceView downLoadServiceView;
    private DownloadServiceInterActor interActor;
    private boolean downloadCanceled;
    private int progressMax, pagesDownloaded, imagesDownloaded, endNumber;

    public DownloadServicePresenterImpl(DownLoadServiceView downLoadServiceView, Context mContext) {
        this.downLoadServiceView = downLoadServiceView;
        interActor = new DownloadServiceInterActorImpl(this, mContext);
    }


    @Override
    public void onCreate() {


        downLoadServiceView.initService(1000, 0);
        interActor.downloadFile();
//        pagesDownloaded = Preferences.getInstance().getInt(Key.LAST_PAGE_DOWNLOADED, 0);
//
//        imagesDownloaded = Preferences.getInstance().getInt(Key.LAST_IMAGE_DOWNLOADED, 0);
//
//        Util.createNoMediaFile();
//
//        if (Util.firstImagesAndPagesDownloaded()) {
//            //complete download
//            progressMax = (Constant.MAX_PAGES * 2) - 10;
//            endNumber = Constant.MAX_PAGES;
//        } else {
//            //download first pages
//            progressMax = Constant.MIN_PAGES * 2;
//            endNumber = Constant.MIN_PAGES;
//        }
//
//        downLoadServiceView.initService(progressMax, pagesDownloaded + imagesDownloaded);
//
//        if (pagesDownloaded == progressMax) {
//            interActor.downLoadImage(imagesDownloaded + 1);
//        } else {
//            interActor.downLoadPage(pagesDownloaded + 1);
//        }

    }

//    @Override
//    public void pageDownloaded(int pageNumber) {
//        Logging.e(getClass(), "pageDownloaded : " + pageNumber);
//        //save in pref
//        Preferences.getInstance().putInt(Key.LAST_PAGE_DOWNLOADED, pageNumber);
//        // setProgress
//        downLoadServiceView.setProgressValue(progressMax, pageNumber);
//        pagesDownloaded = pageNumber;
//        if (downloadCanceled) {
//            downLoadServiceView.downloadFinished(R.string.loading_cancel);
//            return;
//        }
//
//        if (pageNumber < endNumber) {
//            interActor.downLoadPage(++pageNumber);
//        } else {
//            interActor.downLoadImage(imagesDownloaded + 1);
//        }
//    }
//
//    @Override
//    public void imageDownloaded(int imageNumber) {
//
//        Logging.e(getClass(), "imageDownloaded : " + imageNumber);
//
//        Preferences.getInstance().putInt(Key.LAST_IMAGE_DOWNLOADED, imageNumber);
//
//        // setProgress
//        downLoadServiceView.setProgressValue(progressMax, pagesDownloaded + imageNumber);
//
//        if (downloadCanceled) {
//            downLoadServiceView.downloadFinished(R.string.loading_cancel);
//            return;
//        }
//
//        if (imageNumber < endNumber) {
//            interActor.downLoadImage(++imageNumber);
//        } else {
//            downloadComplete();
//        }
//    }

    @Override
    public void downloadComplete() {
        downLoadServiceView.downloadFinished(R.string.loading_complete);
    }

    @Override
    public void cancelDownload() {

        downloadCanceled = true;
    }

    @Override
    public void fileDownloaded(String location) {
//save file to sdcard and maniplated it later
        downloadComplete();
//        new Extract();
    }

    @Override
    public void downloadFailed(Exception e) {
        Logging.e(getClass(), e);
        downLoadServiceView.downloadFinished(R.string.loading_failed);

    }

    @Override
    public void downloadProgress(int downloaded, int total, int percentage) {
        if (downloadCanceled) {
            downLoadServiceView.downloadFinished(R.string.loading_cancel);
            return;
        }
        downLoadServiceView.setProgressValue(total, downloaded, percentage);

    }
}