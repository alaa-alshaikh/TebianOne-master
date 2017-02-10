package org.islamright.tebian.page;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import org.islamright.tebian.model.Aya;
import org.islamright.tebian.model.Page;
import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Util;


public class PagePresenterImpl implements PagePresenter, PageOnFinishedListener {


    private PageView pageView;
    private Page page;
    private float x, y;
    private PageInterActor interActor = new PageInterActorImpl();
    public PagePresenterImpl(PageView pageView) {
        this.pageView = pageView;
    }

    @Override
    public void onCreate(Bundle bundle) {
        int pageNumber = Util.getPositionComplement(bundle.getInt(Key.PAGE_NUMBER));
        interActor.getPage(this, pageNumber);
    }

    @Override
    public void onCreateView(View view) {
        pageView.initView(view);
        interActor.getImage(this, page.getPageNumber());
    }

    @Override
    public int onAyaPressed() {
        for (Aya aya : page.getAyatList()) {

            double xVal = aya.getX();
            double yVal = aya.getY();
            double wVal = aya.getW();
            double hVal = aya.getH();


            if (x >= xVal && x <= xVal + wVal) {
                if (y >= yVal && y <= yVal + hVal) {
                    return aya.getAyaNumber();
                }
            }
        }
        return -1;
    }

    @Override
    public void goToExplanation(int numberAyaPressed) {
        Intent intent = new Intent();
        intent.putExtra(Key.AYA_NUMBER, numberAyaPressed);
        intent.putExtra(Key.PAGE_NUMBER, page.getPageNumber());
        pageView.goToExplanation(intent);
    }

    @Override
    public void onClick() {
        int numberAyaPressed = onAyaPressed();
        if (numberAyaPressed != -1) {
            goToExplanation(numberAyaPressed);
        }
    }

    @Override
    public void onTouch(float x, float y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public void onFinish(Page page) {
        this.page = page;
    }

    @Override
    public void onFinish(Drawable drawable) {
        pageView.setImage(drawable, page.getAyatList());
    }
}
