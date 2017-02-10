package org.islamright.tebian.search_page;

import android.os.Bundle;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface SearchPagePresenter {

    void onCreate();

    void onActivityResult(Bundle bundle);

    void onPageSelected(int position);

    void openFahras(String fahrasPath);

    void openPageByPageNumber();

    void openSearch();
}
