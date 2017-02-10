package org.islamright.tebian.explanation;

import android.os.Bundle;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface ExplanationPresenter {
    void onCreate(Bundle bundle);

    void onItemClick(int position);

    void next();

    void back();

}
