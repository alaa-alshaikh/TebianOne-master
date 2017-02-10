package org.islamright.tebian.explanation_reading_list;

import java.util.List;

/**
 * Created by AlaaAlShaikh on 29/10/2015.
 */
public interface ExplanationReadingView {
    void initView();

    void setItems(List<ExplanationOrReadingItem> items);

    void finishView(String id);


}
