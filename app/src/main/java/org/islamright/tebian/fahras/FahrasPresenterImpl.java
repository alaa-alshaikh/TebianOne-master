package org.islamright.tebian.fahras;

import org.islamright.tebian.model.FahrasItem;
import org.islamright.tebian.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class FahrasPresenterImpl implements FahrasPresenter, FahrasOnFinishedListener {

    private FahrasView fahrasView;
    private FahrasInterActor interActor = new FahrasInterActorImpl();
    private List<FahrasItem> fahrasItems;

    public FahrasPresenterImpl(FahrasView fahrasView) {
        this.fahrasView = fahrasView;
    }

    @Override
    public void onCreate(String fahrasPath) {
        fahrasView.initView();
        interActor.getFahras(this, fahrasPath);
    }

    @Override
    public void itemClick(int position) {
        fahrasView.finishView(fahrasItems.get(position).getStart());
    }


    @Override
    public void onFinish(ArrayList<FahrasItem> fahrasItems) {
        this.fahrasItems = fahrasItems;
        fahrasView.setItems(fahrasItems);
    }
}
