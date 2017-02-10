package org.islamright.tebian.explanation;

import org.islamright.tebian.model.Aya;
import org.islamright.tebian.model.Explanation;

import java.util.ArrayList;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface ExplanationView {

    void initView();

    void setUpView(Aya aya , int ExplanationPosition);

    void notifyDataSetChangedListViewButtons(int ExplanationPosition);

    void setExplanationInWebView(String explanation);

    void hideShowAya(boolean show);

    void showDialog();

    void hideDialog();

    void showMessage(String message);

    void moveListToEnd();
}
