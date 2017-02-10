package org.islamright.tebian.page;

import android.os.Bundle;
import android.view.View;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface PagePresenter {

    void onCreate(Bundle bundle);

    void onCreateView(View view);

    void onClick();

    void onTouch(float x, float y);

    int onAyaPressed();

    void goToExplanation(int numberAyaPressed);

}
