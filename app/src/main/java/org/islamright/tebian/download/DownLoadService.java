package org.islamright.tebian.download;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import org.islamright.tebian.App;
import org.islamright.tebian.R;
import org.islamright.tebian.download_ui.DownloadActivity;
import org.islamright.tebian.main.MainActivity;
import org.islamright.tebian.model.DataBus;
import org.islamright.tebian.util.BusProvider;
import org.islamright.tebian.util.Constant;
import org.islamright.tebian.util.Logging;

public class DownLoadService extends Service implements DownLoadServiceView {

    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private DownloadServicePresenter presenter ;


    public DownLoadService() {
        presenter  = new DownloadServicePresenterImpl(this,this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logging.e(getClass(), "onCreate");

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                presenter.onCreate();
            }
        };
        thread.start();
        App.getInstance().setDownloadProcessRunning(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case Constant.STOPFOREGROUND_ACTION:
                    presenter.cancelDownload();
                    stopSelf();
                    break;
            }
        }
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void initService(int progressMax, int progressVale) {
        Intent intent = new Intent(this, DownloadActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 604, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.loading_data))
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        mBuilder.setProgress(progressMax, progressVale, false);
        mNotifyManager.notify(Constant.NOTIFICATION_ID.FOREGROUND_SERVICE, mBuilder.build());

        startForeground(Constant.NOTIFICATION_ID.FOREGROUND_SERVICE, mBuilder.build());
    }

    @Override
    public void downloadFinished(int resString) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 604, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        mBuilder.setContentText(getString(resString))
                .setProgress(0, 0, false)
                .setContentIntent(pendingIntent);
        stopForeground(true);
        mNotifyManager.notify(Constant.NOTIFICATION_ID.FOREGROUND_SERVICE, mBuilder.build());
        BusProvider.getInstance().post(new DataBus(true, resString));

        stopSelf();
    }

    @Override
    public void setProgressValue(int progressMax, int progressVale, int percentage) {
        mBuilder.setContentText(percentage + " %")
                .setProgress(progressMax, progressVale, false);
        mNotifyManager.notify(Constant.NOTIFICATION_ID.FOREGROUND_SERVICE, mBuilder.build());
        BusProvider.getInstance().post(new DataBus(progressMax, progressVale, percentage));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logging.e(getClass(), "onDestroy");
        App.getInstance().setDownloadProcessRunning(false);
    }
}
