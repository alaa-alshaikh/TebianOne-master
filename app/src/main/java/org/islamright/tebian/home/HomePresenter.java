package org.islamright.tebian.home;

import android.os.Bundle;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface HomePresenter {

    void onCreate();

    void onActivityResult(Bundle bundle);

    void onPageSelected(int position);

    void completeDownloadPages();

    void openFahras(String fahrasPath);

    void openPageByPageNumber();

    void openSearch();
}
