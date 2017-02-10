package org.islamright.tebian.download_ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import org.islamright.tebian.R;
import org.islamright.tebian.download.DownLoadService;
import org.islamright.tebian.model.DataBus;
import org.islamright.tebian.util.BusProvider;
import org.islamright.tebian.util.Constant;
import org.islamright.tebian.util.Util;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DownloadActivity extends AppCompatActivity implements DownloadView, DialogInterface.OnClickListener {

    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    @InjectView(R.id.tvPercentage)
    TextView tvPercentage;

    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        initView();
        startService(new Intent(this, DownLoadService.class));
    }

    @Override
    protected void onDestroy() {
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);

        BusProvider.getInstance().register(this);

        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(getString(R.string.msgCancelDownload))
                .setPositiveButton(getString(R.string.confirm), this)
                .setNegativeButton(getString(R.string.cancel), this);
        progressBar.invalidate();
    }

    @Override
    public void showAlertDialog() {
        alertDialog.show();
    }

    @Override
    public void showMessage(String message) {
        Util.toaster(this, message);
    }

    @Override
    public void finishView() {
        Intent mIntent = new Intent();
        mIntent.setAction(Constant.START_EXTRACT_AFTER_DOWNLOAD);
        sendBroadcast(mIntent);
        finish();
    }

    public void cancelDownload(View view) {
        showAlertDialog();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                //Yes button clicked
                startService(new Intent(this, DownLoadService.class).setAction(Constant.STOPFOREGROUND_ACTION));
                finish();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                //No button clicked
                break;
        }
    }

    @Subscribe
    public void event(DataBus dataBus) {

        if (dataBus.isFinished()) {
            if (getString(dataBus.getResString()).equals(getString(R.string.loading_complete)))
                finishView();
            Util.toaster(this, getString(dataBus.getResString()));
        } else {
            progressBar.setMax(dataBus.getProgressMax());

            progressBar.setProgress(dataBus.getProgressVale());

            tvPercentage.setText(dataBus.getPercentage() + " %");
        }
    }


}
