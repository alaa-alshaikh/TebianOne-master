package org.islamright.tebian;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.splunk.mint.Mint;

/**
 * Created by AlaaAlShaikh on 05/05/15.
 */
public class App extends Application {

    private static App mInstance;
    private boolean downloadProcessRunning;

    public static synchronized App getInstance() {
        return mInstance;
    }

    public boolean isDownloadProcessRunning() {
        return downloadProcessRunning;
    }

    public void setDownloadProcessRunning(boolean downloadProcessRunning) {
        this.downloadProcessRunning = downloadProcessRunning;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        Mint.initAndStartSession(this, "8499f116");
       // LeakCanary.install(this);

    }

}
