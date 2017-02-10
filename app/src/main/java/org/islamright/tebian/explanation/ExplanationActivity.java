package org.islamright.tebian.explanation;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import org.islamright.tebian.R;
import org.islamright.tebian.model.Aya;
import org.islamright.tebian.util.Logging;
import org.islamright.tebian.util.Util;
import org.lucasr.twowayview.TwoWayView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ExplanationActivity extends AppCompatActivity implements ExplanationView, AdapterView.OnItemClickListener {

    @InjectView(R.id.tvAya)
    TextView tvAya;

    @InjectView(R.id.wvExp)
    WebView wvExp;

    @InjectView(R.id.ivHideShow)
    ImageView ivHideShow;


    @InjectView(R.id.lvButtons)
    TwoWayView lvButtons;

    WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            wvExp.scrollTo(0, 0);
            hideDialog();
        }
    };
    private ProgressDialog progressDialog;
    private ExplanationButtonAdapter adapter;
    private ExplanationPresenter presenter = new ExplanationPresenterImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explanation);
        presenter.onCreate(getIntent().getExtras());
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);

        wvExp.getSettings().setJavaScriptEnabled(true);
        //wvExp.getSettings().setBuiltInZoomControls(true);
        wvExp.getSettings().setSupportZoom(true);
        //wvExp.setWebChromeClient(new WebChromeClient());
        wvExp.setWebViewClient(webViewClient);
        wvExp.getSettings().setBuiltInZoomControls(true);
        //wvExp.setScrollBarStyle(WebView.SCROLL_);
        //wvExp.setScrollbarFadingEnabled(true);
        //wvExp.getSettings().setLoadsImagesAutomatically(true);
        wvExp.getSettings().setDefaultFontSize(getResources().getInteger(R.integer.WvTextSize));
        wvExp.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //wvExp.getSettings().setLoadWithOverviewMode(true);
        /*wvExp.setInitialScale(50);
        wvExp.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        wvExp.getSettings().setUseWideViewPort(true);
*/
        tvAya.setMovementMethod(new ScrollingMovementMethod());

        lvButtons.setOnItemClickListener(this);

        progressDialog = new ProgressDialog(this);

        progressDialog.setCancelable(false);

        progressDialog.setMessage(getString(R.string.msgWait));
    }

    @Override
    public void setUpView(Aya aya, int ExplanationPosition) {
        adapter = new ExplanationButtonAdapter(aya.getExplanationsList(), ExplanationPosition);
        lvButtons.setAdapter(adapter);

        tvAya.setText(aya.getText());

        setExplanationInWebView(aya.getExplanationsList().get(ExplanationPosition).getExplanation());
    }

    @Override
    public void notifyDataSetChangedListViewButtons(int position) {
        adapter.setPosition(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setExplanationInWebView(String explanation) {
        wvExp.loadDataWithBaseURL("", explanation, "text/html", "UTF-8", "");
    }

    @Override
    public void hideShowAya(boolean show) {
        tvAya.setVisibility(show ? View.VISIBLE : View.GONE);
        ivHideShow.setImageResource(show ? R.drawable.hide : R.drawable.show);
    }

    @Override
    public void showDialog() {
        progressDialog.show();
    }

    @Override
    public void hideDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(String message) {
        Util.toaster(this , message);
    }

    @Override
    public void moveListToEnd() {
        lvButtons.postDelayed(new Runnable() {
            @Override
            public void run() {
                lvButtons.smoothScrollToPosition(lvButtons.getCount());
            }
        } , 1500);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivNext:
                presenter.next();
                break;
            case R.id.ivBack:
                presenter.back();
                break;
            case R.id.ivHideShow:
                hideShowAya(tvAya.getVisibility() == View.GONE);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClick(position);
    }

}