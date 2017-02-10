package org.islamright.tebian.page;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;

import org.islamright.tebian.model.Aya;

import java.util.ArrayList;

public interface PageView {
    void initView(View view);

    void setImage(Drawable drawable , ArrayList<Aya> arrayList);

    void showMessage(String message);

    void goToExplanation(Intent intent);
}
