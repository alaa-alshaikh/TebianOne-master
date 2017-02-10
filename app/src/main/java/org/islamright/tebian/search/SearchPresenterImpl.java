package org.islamright.tebian.search;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import org.islamright.tebian.R;
import org.islamright.tebian.model.Aya;
import org.islamright.tebian.search_page.SearchPageActivity;
import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Util;

import java.util.ArrayList;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class SearchPresenterImpl implements SearchPresenter, SearchOnFinishedListener {

    private AppCompatActivity activity;
    private SearchView searchView;
    private SearchInterActor interActor = new SearchInterActorImpl();
    private String word = "";
    private ArrayList<Aya> ayatList;
    private int position;

    public SearchPresenterImpl(AppCompatActivity activity, SearchView searchView) {
        this.activity = activity;
        this.searchView = searchView;
    }


    @Override
    public void onFinish(ArrayList<Aya> ayatList) {
        this.ayatList = ayatList;
        searchView.setItems(ayatList, word);
        searchView.showEmptyMessage(ayatList.size() < 1);
        searchView.setCountOfSearchResult(ayatList.size());
    }

    @Override
    public void onCreate() {
        searchView.initView();
    }

    @Override
    public void search(boolean isFullSearch, String word) {
        this.word = word;
        if (isFullSearch) {
            this.word += " ";
            interActor.searchForWord(this, word);
            searchView.setButtonsBackground(R.drawable.btn_enable, R.drawable.btn_pressed);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                interActor.searchForWord(this, "" + word + "*");
            }else {
                interActor.searchForWord(this, "*" + word + "*");
            }
            searchView.setButtonsBackground(R.drawable.btn_pressed, R.drawable.btn_enable);
        }


    }

    @Override
    public void itemClick(int position) {
        this.position = position;
        Intent intent = new Intent(activity, SearchPageActivity.class);
        intent.putExtra(Key.LAST_PAGE_OPEN, Util.getPositionComplement(ayatList.get(position).getPageNumber()));
        intent.putExtra(Key.X, ayatList.get(position).getX());
        intent.putExtra(Key.Y, ayatList.get(position).getY());
        intent.putExtra(Key.W, ayatList.get(position).getW());
        intent.putExtra(Key.H, ayatList.get(position).getH());
        activity.startActivity(intent);
    }

    @Override
    public void itemLongClick(int position) {
        this.position = position;
        searchView.showDialog();
    }

    @Override
    public void showAyaText() {
        searchView.showAyaTextDialog(ayatList.get(position).getText());
    }

    @Override
    public void copyAyaText() {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(ayatList.get(position).getText());
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", ayatList.get(position).getText());
            clipboard.setPrimaryClip(clip);
        }
    }

    @Override
    public void shareAyaText() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, ayatList.get(position).getText());
        sendIntent.setType("text/plain");
        activity.startActivity(sendIntent);
    }
}
