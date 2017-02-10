package org.islamright.tebian.search_page;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface SearchPageInterActor {
    void getPage(SearchPageOnFinishedListener listener, int pageNumber);
}
