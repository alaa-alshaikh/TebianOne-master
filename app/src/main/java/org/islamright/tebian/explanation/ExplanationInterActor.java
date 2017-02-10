package org.islamright.tebian.explanation;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface ExplanationInterActor {
    void getPage(ExplanationOnFinishedListener listener, int pageNumber);
}
