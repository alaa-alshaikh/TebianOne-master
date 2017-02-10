package org.islamright.tebian.search;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface SearchPresenter {
    void onCreate();

    void search(boolean isFullSearch, String word);

    void itemClick(int position);

    void itemLongClick(int position);

    void showAyaText();

    void copyAyaText();

    void shareAyaText();
}
