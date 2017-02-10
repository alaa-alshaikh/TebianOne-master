package org.islamright.tebian.page;

import android.graphics.drawable.Drawable;

import org.islamright.tebian.model.Page;

/**
 * Created by AlaaAlShaikh on 27/04/15.
 */
public interface PageOnFinishedListener {

    void onFinish(Page page);

    void onFinish(Drawable drawable);

}
