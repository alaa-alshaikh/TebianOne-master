package org.islamright.tebian.explanation_reading_list;

import java.util.List;

/**
 * Created by AlaaAlShaikh on 18/11/2015.
 */
public interface OnFinishLoadingExplanationReadingListener {
    void finish(List<ExplanationOrReadingItem> mExplanationOrReadingItem);
    void error (String message);
}
