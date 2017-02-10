package org.islamright.tebian.page;

import org.islamright.tebian.database.DatabaseHelper;
import org.islamright.tebian.util.Util;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class PageInterActorImpl implements PageInterActor {

    @Override
    public void getPage(PageOnFinishedListener listener, int pageNumber) {
        listener.onFinish(DatabaseHelper.getInstance().selectPageComponent(pageNumber));
    }

    @Override
    public void getImage(PageOnFinishedListener listener, int pageNumber) {
        listener.onFinish(Util.getImageFromSdcardNew(pageNumber));
    }
}
