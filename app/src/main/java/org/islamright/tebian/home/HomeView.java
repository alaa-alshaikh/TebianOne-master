package org.islamright.tebian.home;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface HomeView {

    void initView();

    void updateButtonsText(String jzuaNumber, String pageNumber, String soraName);

    void showDialog();

    void setCurrentItem(int position);
}
