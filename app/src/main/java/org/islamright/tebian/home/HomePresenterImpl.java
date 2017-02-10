package org.islamright.tebian.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.islamright.tebian.App;
import org.islamright.tebian.download_ui.DownloadActivity;
import org.islamright.tebian.fahras.FahrasActivity;
import org.islamright.tebian.model.Page;
import org.islamright.tebian.page_by_number.EnterPageNumberActivity;
import org.islamright.tebian.search.SearchActivity;
import org.islamright.tebian.util.Constant;
import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Preferences;
import org.islamright.tebian.util.Util;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class HomePresenterImpl implements HomePresenter, HomeOnFinishedListener {

    private AppCompatActivity activity;
    private HomeView homeView;
    private HomeInterActor interActor = new HomeInterActorImpl();

    public HomePresenterImpl(AppCompatActivity activity, HomeView homeView) {
        this.activity = activity;
        this.homeView = homeView;

    }

    @Override
    public void onCreate() {
        this.homeView.initView();
        Bundle bundle = new Bundle();
        bundle.putInt(Key.LAST_PAGE_OPEN, Preferences.getInstance().getInt(Key.LAST_PAGE_OPEN, 0));
        onActivityResult(bundle);
    }

    @Override
    public void onActivityResult(Bundle bundle) {
        int position = bundle.getInt(Key.LAST_PAGE_OPEN);
        Preferences.getInstance().putInt(Key.LAST_PAGE_OPEN , position);
        homeView.setCurrentItem(Util.getPositionComplement(position));
        if (Util.getPositionComplement(position) == 0){
            interActor.getPage(this , position);
        }

    }

    @Override
    public void onPageSelected(int position) {

        interActor.getPage(this, Util.getPositionComplement(position));

        Preferences.getInstance().putInt(Key.LAST_PAGE_OPEN, Util.getPositionComplement(position));

//        if (position == 0 && !Util.allImagesAndPagesDownloaded()) {
//            if (App.getInstance().isDownloadProcessRunning()){
//                completeDownloadPages();
//            }else{
//                homeView.showDialog();
//            }
//
//        }

    }

    @Override
    public void completeDownloadPages() {
        Intent intent = new Intent(activity, DownloadActivity.class);
        intent.putExtra(Key.START, Constant.MIN_PAGES);
        intent.putExtra(Key.END, Constant.MAX_PAGES);
        activity.startActivity(intent);
        activity.finish();

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
        activity.startActivityForResult(intent, 1);
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
