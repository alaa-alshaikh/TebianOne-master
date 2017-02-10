package org.islamright.tebian.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.splunk.mint.Mint;

import org.islamright.tebian.R;
import org.islamright.tebian.util.Constant;
import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Preferences;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends AppCompatActivity implements HomeView, DialogInterface.OnClickListener, ViewPager.OnPageChangeListener {

    @InjectView(R.id.fragmentViewPager)
    ViewPager fragmentViewPager;

    @InjectView(R.id.btnJzuaNumber)
    Button btnJzuaNumber;

    @InjectView(R.id.btnPageNumber)
    Button btnPageNumber;

    @InjectView(R.id.btnSoraName)
    Button btnSoraName;

    @InjectView(R.id.layout)
    FrameLayout layout;

    @InjectView(R.id.flImageEmptyBackground)
    FrameLayout flImageEmptyBackground;


    private FragmentPagesAdapter adapter;

//    private AlertDialog.Builder alertDialog;

    private HomePresenter presenter = new HomePresenterImpl(this, this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mint.initAndStartSession(this, "8499f116");
        setContentView(R.layout.activity_home);
        presenter.onCreate();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            presenter.onActivityResult(data.getExtras());
        }
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);

//        alertDialog = new AlertDialog.Builder(HomeActivity.this);
//        alertDialog.setMessage(getString(R.string.msgDownload))
//                .setPositiveButton(getString(R.string.confirm), this)
//                .setNegativeButton(getString(R.string.cancel), this);

        adapter = new FragmentPagesAdapter(getSupportFragmentManager());

        fragmentViewPager.setAdapter(adapter);
        fragmentViewPager.setOnPageChangeListener(this);


        double newHeight = Preferences.getInstance().getFloat(Key.PERCENTAGE_NEW_HEIGHT, 1) * Preferences.getInstance().getInt(Key.SCREEN_HIGHT, 0);
        layout.getLayoutParams().height = (int) newHeight;

        flImageEmptyBackground.addView(new EmptyBackgroundView(this , (float) newHeight-1));

    }



    @Override
    public void updateButtonsText(String jzuaNumber, String pageNumber, String soraName) {
        btnJzuaNumber.setText(getString(R.string.jzua) + " " + jzuaNumber);
        btnPageNumber.setText(pageNumber);
        btnSoraName.setText(soraName);
    }

    @Override
    public void showDialog() {

        //alertDialog.show();
    }

    @Override
    public void setCurrentItem(int position) {
        fragmentViewPager.setCurrentItem(position);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnJzuaNumber:
                presenter.openFahras(Constant.FAHRAS_JZUA_PATH);
                break;
            case R.id.btnPageNumber:
                presenter.openPageByPageNumber();
                break;
            case R.id.btnSoraName:
                presenter.openFahras(Constant.FAHRAS_SORA_PATH);
                break;
            case R.id.btnSearch:
                presenter.openSearch();
                break;
        }
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                //Yes button clicked
                presenter.completeDownloadPages();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                //No button clicked
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        presenter.onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}