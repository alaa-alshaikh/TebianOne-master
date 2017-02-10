package org.islamright.tebian.fahras;

import org.islamright.tebian.model.FahrasItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlaaAlShaikh on 27/04/15.
 */
public interface FahrasOnFinishedListener {
    void onFinish(ArrayList<FahrasItem> fahrasItems);
}
