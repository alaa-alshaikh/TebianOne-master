package org.islamright.tebian.splash;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import org.islamright.tebian.main.MainActivity;
import org.islamright.tebian.util.Constant;
import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Preferences;

import java.io.File;
import java.io.IOException;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class SplashPresenterImpl implements SplashPresenter {

    private AppCompatActivity activity;
    private SplashInterActor interActor;

    public SplashPresenterImpl(AppCompatActivity activity) {
        this.activity = activity;
        interActor = new SplashInterActorImpl();
    }


    @Override
    public void onCreate() {
        if (!Preferences.getInstance().hasKey(Key.SCREEN_WIDTH) && !Preferences.getInstance().hasKey(Key.SCREEN_HIGHT)) {
            getScreenDim();
        }
        if (Preferences.getInstance().getBoolean(Key.FIRST_TIME_DOWNLOADING, true)) {
            Preferences.getInstance().putBoolean(Key.FIRST_TIME_DOWNLOADING, false);
            File downloadFile = new File(Constant.MAIN_PATH_NEW);
            if (downloadFile.exists()) {
                deleteRecursive(downloadFile);
            }

        }
        goToMainActivity();
    }

    void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }


    private void getScreenDim() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;


        interActor.saveScreenDimensions(screenWidth, screenHeight);


        calculatePercentage(screenWidth * 1.0f, screenHeight * 1.0f);
    }


    public void calculatePercentage(float width, float height) {
        float percentageWidth = width / Constant.IMAGE_WIDTH;
        float percentageHeight = height / Constant.IMAGE_HEIGHT;
        float percentageNewHeight = (width / height) / 0.6f;

        if (percentageNewHeight > 1) {
            percentageNewHeight = 1;
        }

        interActor.saveScreenPercentage(percentageWidth, percentageHeight, percentageNewHeight);
    }

    private void goToMainActivity() {
        activity.startActivity(new Intent(activity, MainActivity.class));
        activity.finish();
    }

}
