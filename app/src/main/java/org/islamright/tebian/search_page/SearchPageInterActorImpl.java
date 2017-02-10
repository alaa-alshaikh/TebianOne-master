package org.islamright.tebian.search_page;

import org.islamright.tebian.database.DatabaseHelper;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class SearchPageInterActorImpl implements SearchPageInterActor {

    @Override
    public void getPage(SearchPageOnFinishedListener listener, int pageNumber) {
        listener.onFinish(DatabaseHelper.getInstance().selectPageByPageNumber(pageNumber));
    }
}
