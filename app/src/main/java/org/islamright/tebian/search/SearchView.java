package org.islamright.tebian.search;

import org.islamright.tebian.model.Aya;

import java.util.ArrayList;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface SearchView {

    void initView();

    void setItems(ArrayList<Aya> items , String word);

    void setButtonsBackground(int btnSubSearchBackground, int btnFullSearchBackground);

    void showDialog();

    void showMessage(String message);

    void showEmptyMessage(boolean showEmptyMessage);

    void setCountOfSearchResult(int count);

    void showAyaTextDialog(String ayaText);
}
