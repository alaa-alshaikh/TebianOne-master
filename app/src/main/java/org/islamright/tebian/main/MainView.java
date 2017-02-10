package org.islamright.tebian.main;

import android.content.Intent;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface MainView {

    void initView();

    void showAlertDialog();

    void openActivity(Class aClass, Intent intent);

    void openService(Class mClass, Intent intent);

    void openServiceWithData(Class mClass, Intent intent, boolean isProgressOpen);
}
