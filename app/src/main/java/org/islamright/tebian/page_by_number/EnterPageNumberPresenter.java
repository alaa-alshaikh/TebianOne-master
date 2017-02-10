package org.islamright.tebian.page_by_number;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface EnterPageNumberPresenter {

    boolean isValidNumber(String pageNumber);

    void go(String pageNumber);

    void cancel();
}
