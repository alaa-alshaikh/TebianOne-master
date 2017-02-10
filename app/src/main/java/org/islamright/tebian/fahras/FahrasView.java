package org.islamright.tebian.fahras;

import org.islamright.tebian.model.FahrasItem;

import java.util.ArrayList;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface FahrasView {
    void initView();

    void setItems(ArrayList<FahrasItem> items);

    void finishView(int pageNumber);
}
