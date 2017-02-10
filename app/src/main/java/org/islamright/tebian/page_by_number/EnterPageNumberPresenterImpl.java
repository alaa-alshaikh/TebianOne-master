package org.islamright.tebian.page_by_number;

import android.text.TextUtils;

import org.islamright.tebian.App;
import org.islamright.tebian.R;
import org.islamright.tebian.util.Constant;
import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Preferences;
import org.islamright.tebian.util.Util;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class EnterPageNumberPresenterImpl implements EnterPageNumberPresenter, EnterPageNumberOnFinishedListener {

    private EnterPageNumberView enterPageNumberView;

    public EnterPageNumberPresenterImpl(EnterPageNumberView enterPageNumberView) {
        this.enterPageNumberView = enterPageNumberView;
    }

    @Override
    public boolean isValidNumber(String pageNumber) {
        if (TextUtils.isEmpty(pageNumber)) {
            return false;
        }

        if (!TextUtils.isDigitsOnly(pageNumber)) {
            return false;
        }

        if (!(Integer.parseInt(pageNumber) > 0 && Integer.parseInt(pageNumber) <= Constant.MAX_PAGES)) {
            return false;
        }

        return true;
    }

    @Override
    public void go(String pageNumber) {
        if (isValidNumber(pageNumber)) {
            enterPageNumberView.finishView(Integer.parseInt(pageNumber));
        } else {
            enterPageNumberView.showMessage(App.getInstance().getString(R.string.msgEnterValidNumber));
        }
    }

    @Override
    public void cancel() {
        enterPageNumberView.finishView();
    }
}
