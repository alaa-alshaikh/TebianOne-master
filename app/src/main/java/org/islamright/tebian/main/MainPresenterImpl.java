package org.islamright.tebian.main;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import org.islamright.tebian.App;
import org.islamright.tebian.R;
import org.islamright.tebian.database.DatabaseHelper;
import org.islamright.tebian.download_ui.DownloadActivity;
import org.islamright.tebian.explanation_reading_list.ExplanationReadingReadingActivity;
import org.islamright.tebian.extract_and_save.Extract;
import org.islamright.tebian.extract_and_save.SaveToDatabase;
import org.islamright.tebian.home.HomeActivity;
import org.islamright.tebian.network.Apis;
import org.islamright.tebian.ui.InstructionAndIntroductionActivity;
import org.islamright.tebian.util.Constant;
import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Preferences;

import java.io.File;
import java.math.BigDecimal;
import java.security.spec.KeySpec;

public class MainPresenterImpl implements MainPresenter, MainOnFinishedListener {

    private MainView mainView;

    public MainPresenterImpl(MainView viewer) {
        this.mainView = viewer;
    }

    @Override
    public void onCreate() {
        this.mainView.initView();
    }

    @Override
    public void openIntroduction() {
        mainView.openActivity(InstructionAndIntroductionActivity.class, new Intent().putExtra("image", R.drawable.introduction_image));
    }

    @Override
    public void openInstructions() {
        mainView.openActivity(InstructionAndIntroductionActivity.class, new Intent().putExtra("image", R.drawable.instructions_image));
    }

    @Override
    public void enterApp() {
        //mainView.showAlertDialog();
        mainView.openActivity(HomeActivity.class, new Intent());
    }

    @Override
    public void enter() {
        if (App.getInstance().isDownloadProcessRunning()) {
            downloadFirstPages();
            return;
        }
//for (int x =0 ; x<100 ; x++)
//{
//    Log.d("string", ""+ x);
//    Log.d("string", ""+ new BigDecimal(x));
//}

        // check if the folder is downloaded and saves in database here

        File extractFile = new File(Constant.MAIN_PATH_NEW);
        if (extractFile.exists()) {

            if (!DatabaseHelper.getInstance().checkDataBase()) {
                // enter app
                enterApp();
            } else {
                // save to database service
                startSaveToDatabaseService();
            }
        } else {

            File downloadFile = new File(Apis.FILE_LOCATION);
            if (downloadFile.exists()) {
                // extract and save service
                startExtractService();

            } else {
                // open download
                mainView.showAlertDialog();

            }


        }
    }

    @Override
    public void downloadFirstPages() {
        mainView.openActivity(DownloadActivity.class, new Intent());
    }

    @Override
    public void openWebsite() {
        mainView.openActivity(null, new Intent(Intent.ACTION_VIEW, Uri.parse(App.getInstance().getString(R.string.website))));
    }

    @Override
    public void openEmail() {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{App.getInstance().getString(R.string.email)});
        email.putExtra(Intent.EXTRA_SUBJECT, "");
        email.putExtra(Intent.EXTRA_TEXT, "");
        email.setType("message/rfc822");
        mainView.openActivity(null, (Intent.createChooser(email, App.getInstance().getString(R.string.ChooseEmail))));
    }

    @Override
    public void openExplanationList() {
        mainView.openActivity(ExplanationReadingReadingActivity.class, new Intent().putExtra(Key.READING_EXPLANATION, "get-explanation"));
    }

    @Override
    public void openReadingsList() {
        mainView.openActivity(ExplanationReadingReadingActivity.class, new Intent().putExtra(Key.READING_EXPLANATION, "get-readings"));

    }

    @Override
    public void startSaveToDatabaseService() {

        mainView.openServiceWithData(SaveToDatabase.class, new Intent(), true);
    }

    @Override
    public void startExtractService() {
        mainView.openService(Extract.class, new Intent());
    }
}
