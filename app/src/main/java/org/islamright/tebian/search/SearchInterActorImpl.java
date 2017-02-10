package org.islamright.tebian.search;

import org.islamright.tebian.database.DatabaseHelper;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class SearchInterActorImpl implements SearchInterActor {

    @Override
    public void searchForWord(SearchOnFinishedListener onFinishedListener, String word) {
        onFinishedListener.onFinish(DatabaseHelper.getInstance().selectAyatByKeyWordFTS(word));
    }
}
