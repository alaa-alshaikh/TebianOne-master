package org.islamright.tebian.search_page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import org.islamright.tebian.R;
import org.islamright.tebian.home.EmptyBackgroundView;
import org.islamright.tebian.home.FragmentPagesAdapter;
import org.islamright.tebian.util.Constant;
import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Preferences;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchPageActivity extends AppCompatActivity implements SearchPageView, ViewPager.OnPageChangeListener {

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

    @InjectView(R.id.layer)
    LinearLayout layer;

    @InjectView(R.id.flImageEmptyBackground)
    FrameLayout flImageEmptyBackground;

    private FragmentPagesAdapter adapter;

    private SearchPagePresenter presenter = new SearchPagePresenterImpl(this, this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter.onCreate();
        presenter.onActivityResult(getIntent().getExtras());
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
    public void createPointAroundSelectedAya(Rect rect) {
        layer.removeAllViews();
        layer.addView(new MyView(this, rect));
    }

    @Override
    public void removePointAroundSelectedAya() {
        layer.removeAllViews();
    }

    @Override
    public void setCurrentItem(int position) {
        fragmentViewPager.setCurrentItem(position);
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

    class MyView extends View {
        Rect rect;
        public MyView(Context context, Rect rect) {
            super(context);
            this.rect= rect;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setColor(Color.CYAN);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(rect, paint);
        }
    }
}