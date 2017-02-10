package org.islamright.tebian.main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.splunk.mint.Mint;

import org.islamright.tebian.R;
import org.islamright.tebian.download.DownLoadService;
import org.islamright.tebian.extract_and_save.Extract;
import org.islamright.tebian.util.Constant;

/**
 * Main view
 */
public class MainActivity extends AppCompatActivity implements MainView, DialogInterface.OnClickListener {

    private MainPresenter presenter = new MainPresenterImpl(this);

    private AlertDialog.Builder alertDialog;

    private ProgressDialog progressDialog;

    private StartExtractServiceBroadcast mStartExtractServiceBroadcast = new StartExtractServiceBroadcast();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mint.initAndStartSession(this, "8499f116");
        setContentView(R.layout.activity_main);
        //   BusProvider.getInstance().register(this);

        presenter.onCreate();

    }

    @Override
    public void initView() {

        alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setMessage(getString(R.string.msgPromptDownloadFirstPages))
                .setPositiveButton(getString(R.string.confirm), this)
                .setNegativeButton(getString(R.string.cancel), this);

    }

    public void enter(View view) {
        presenter.enter();
    }

    public void openInstructions(View view) {
        presenter.openInstructions();
    }

    public void openIntroduction(View view) {
        presenter.openIntroduction();
    }

    public void openEmail(View view) {
        presenter.openEmail();
    }

    public void openWebsite(View view) {
        presenter.openWebsite();
    }

    public void openExplanationList(View view) {
        presenter.openExplanationList();
    }

    public void openReadingList(View view) {
        presenter.openReadingsList();
    }


    @Override
    public void showAlertDialog() {
        alertDialog.show();
    }

    @Override
    public void openActivity(Class aClass, Intent intent) {
        if (aClass != null)
            intent.setClass(this, aClass);

        startActivity(intent);
    }

    @Override
    public void openService(Class mClass, Intent intent) {

        if (mClass != null)
            intent.setClass(this, mClass);

        startService(intent);

    }

    @Override
    public void openServiceWithData(Class mClass, Intent intent, boolean isProgressOpen) {

        if (mClass != null)
            intent.setClass(this, mClass);
        if (isProgressOpen)
            startService(new Intent(this, DownLoadService.class).setAction(Constant.DATABASE_ACTION));
        else
            startService(intent);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                //Yes button clicked
                presenter.downloadFirstPages();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                //No button clicked
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Constant.START_EXTRACT_AFTER_DOWNLOAD);
        mIntentFilter.addAction(Constant.OPEN_PROGRESS);
        mIntentFilter.addAction(Constant.CLOSE_PROGRESS);
        registerReceiver(mStartExtractServiceBroadcast, mIntentFilter);
    }

    //    @Subscribe
//    public void event(boolean event) {
//        if (event) {
//            progressDialog = new ProgressDialog(MainActivity.this);
//            progressDialog.setTitle(getString(R.string.processing));
//            progressDialog.setMessage(getString(R.string.please_wait));
//            progressDialog.setCancelable(false);
//            progressDialog.show();
//        } else {
//            if (progressDialog != null)
//                progressDialog.dismiss();
//        }
//    }

    class StartExtractServiceBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            switch (intent.getAction()) {
                case Constant.START_EXTRACT_AFTER_DOWNLOAD:
                    openService(Extract.class, new Intent());
                    break;
                case Constant.CLOSE_PROGRESS:
                    if (progressDialog != null)
                        progressDialog.dismiss();
                    break;
                case Constant.OPEN_PROGRESS:
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setTitle(getString(R.string.processing));
                    progressDialog.setMessage(getString(R.string.please_wait));
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    break;

            }
        }
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mStartExtractServiceBroadcast);
    }
}
