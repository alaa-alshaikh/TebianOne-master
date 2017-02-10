package org.islamright.tebian.explanation_reading_list;

import java.util.List;

/**
 * Created by AlaaAlShaikh on 29/10/2015.
 */
public class ExplanationReadingReadingPresenterImpl implements ExplanationReadingPresenter, OnFinishLoadingExplanationReadingListener {
    private ExplanationOrReadingActor mExplanationInterActor = new ExplanationOrReadingActor();
    private List<ExplanationOrReadingItem> mExplanationOrReadingItem;
    private ExplanationReadingView mExplanationReadingView;

    public ExplanationReadingReadingPresenterImpl(ExplanationReadingView mExplanationReadingView) {
        this.mExplanationReadingView = mExplanationReadingView;
    }



    @Override
    public void onCreate(String key) {
        mExplanationReadingView.initView();
        mExplanationInterActor.getExplanationReadingList(this, key);
    }

    @Override
    public void itemClick(int url) {
        mExplanationReadingView.finishView(mExplanationOrReadingItem.get(url).getUrl());
    }

    @Override
    public void finish(List<ExplanationOrReadingItem> mExplanationOrReadingItem) {
        this.mExplanationOrReadingItem = mExplanationOrReadingItem;
        mExplanationReadingView.setItems(this.mExplanationOrReadingItem);

    }

    @Override
    public void error(String message) {
        mExplanationReadingView.finishView("error" +message);
    }
}
