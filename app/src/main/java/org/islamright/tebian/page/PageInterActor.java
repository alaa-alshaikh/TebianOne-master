package org.islamright.tebian.page;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface PageInterActor {

    void getPage(PageOnFinishedListener listener, int pageNumber);

    void getImage(PageOnFinishedListener listener, int pageNumber);

}
