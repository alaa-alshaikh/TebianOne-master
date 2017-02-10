package org.islamright.tebian.search_page;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.islamright.tebian.fahras.FahrasActivity;
import org.islamright.tebian.model.Page;
import org.islamright.tebian.page_by_number.EnterPageNumberActivity;
import org.islamright.tebian.search.SearchActivity;
import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Util;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class SearchPagePresenterImpl implements SearchPagePresenter, SearchPageOnFinishedListener {

    private AppCompatActivity activity;
    private SearchPageView homeView;
    private SearchPageInterActor interActor = new SearchPageInterActorImpl();
    private int position;
    private Rect rect;

    public SearchPagePresenterImpl(AppCompatActivity activity, SearchPageView homeView) {
        this.activity = activity;
        this.homeView = homeView;

    }

    @Override
    public void onCreate() {
        this.homeView.initView();
    }

    @Override
    public void onActivityResult(Bundle bundle) {
        position = bundle.getInt(Key.LAST_PAGE_OPEN);
        int x = (int) bundle.getDouble(Key.X);
        int y = (int) bundle.getDouble(Key.Y);
        int w = (int) bundle.getDouble(Key.W);
        int h = (int) bundle.getDouble(Key.H);


        homeView.setCurrentItem(position);

        if (position == 0){
            interActor.getPage(this , Util.getPositionComplement(position));
        }

        rect = new Rect(x, y, x+w, y+h);
        this.homeView.createPointAroundSelectedAya(rect);


    }

    @Override
    public void onPageSelected(int position) {

        if (this.position ==position){
            this.homeView.createPointAroundSelectedAya(rect);
        }else {
            this.homeView.removePointAroundSelectedAya();
        }

        interActor.getPage(this, Util.getPositionComplement(position));
    }


    @Override
    public void openFahras(String fahrasPath) {
        Intent intent = new Intent(activity, FahrasActivity.class);
        intent.putExtra(Key.FAHRAS_PATH, fahrasPath);
        activity.startActivityForResult(intent , 1);
    }

    @Override
    public void openPageByPageNumber() {
        Intent intent = new Intent(activity, EnterPageNumberActivity.class);
        activity.startActivityForResult(intent , 1);
    }

    @Override
    public void openSearch() {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onFinish(Page page) {
        homeView.updateButtonsText(page.getJzuaNumber() + "", page.getPageNumber() + "", page.getSoraName());
    }
}
