package org.islamright.tebian.search;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface SearchInterActor {
    void searchForWord(SearchOnFinishedListener onFinishedListener  ,String word);
}
