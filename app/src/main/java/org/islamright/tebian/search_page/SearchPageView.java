package org.islamright.tebian.search_page;

import android.graphics.Rect;

import org.islamright.tebian.model.Aya;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface SearchPageView {

    void initView();

    void updateButtonsText(String jzuaNumber, String pageNumber, String soraName);

    void setCurrentItem(int position);

    void createPointAroundSelectedAya(Rect rect);

    void removePointAroundSelectedAya();
}
