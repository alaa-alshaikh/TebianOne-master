package org.islamright.tebian.home;

import org.islamright.tebian.database.DatabaseHelper;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class HomeInterActorImpl implements HomeInterActor {

    @Override
    public void getPage(HomeOnFinishedListener listener, int pageNumber) {
        listener.onFinish(DatabaseHelper.getInstance().selectPageByPageNumber(pageNumber));
    }
}
