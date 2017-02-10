package org.islamright.tebian.explanation;

import org.islamright.tebian.database.DatabaseHelper;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class ExplanationInterActorImpl implements ExplanationInterActor {
    @Override
    public void getPage(ExplanationOnFinishedListener listener, int pageNumber) {
        listener.setPage(DatabaseHelper.getInstance().selectPageComponent(pageNumber));
    }
}
