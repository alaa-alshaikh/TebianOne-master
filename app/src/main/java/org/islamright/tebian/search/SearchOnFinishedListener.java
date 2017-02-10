package org.islamright.tebian.search;

import org.islamright.tebian.model.Aya;

import java.util.ArrayList;

/**
 * Created by AlaaAlShaikh on 27/04/15.
 */
public interface SearchOnFinishedListener {
    void onFinish(ArrayList<Aya> ayatList);
}
