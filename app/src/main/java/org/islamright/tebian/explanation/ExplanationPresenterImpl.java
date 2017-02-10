package org.islamright.tebian.explanation;

import android.os.Bundle;

import org.islamright.tebian.App;
import org.islamright.tebian.R;
import org.islamright.tebian.database.DatabaseHelper;
import org.islamright.tebian.model.Page;
import org.islamright.tebian.util.Key;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class ExplanationPresenterImpl implements ExplanationPresenter, ExplanationOnFinishedListener {


    private ExplanationView explanationView;
    private ExplanationInterActor interActor = new ExplanationInterActorImpl();
    private Page page;
    private int positionAya, positionExplanation;

    public ExplanationPresenterImpl(ExplanationView explanationView) {
        this.explanationView = explanationView;
    }

    @Override
    public void onCreate(Bundle bundle) {
        explanationView.initView();

        positionAya = bundle.getInt(Key.AYA_NUMBER) - 1;

        interActor.getPage(this, bundle.getInt(Key.PAGE_NUMBER));


    }

    @Override
    public void onItemClick(int position) {
        if (this.positionExplanation != position) {
            explanationView.showDialog();
            this.positionExplanation = position;
            explanationView.setExplanationInWebView(page.getAyatList().get(positionAya).getExplanationsList().get(position).getExplanation());
            explanationView.notifyDataSetChangedListViewButtons(position);
        }
    }

    @Override
    public void next() {
        if (positionAya < page.getAyatList().size() - 1) {
            explanationView.setUpView(page.getAyatList().get(++positionAya), positionExplanation);
        } else {
            explanationView.showMessage(App.getInstance().getString(R.string.msgEndExplanationPage));
        }
    }

    @Override
    public void back() {
        if (positionAya > 0) {
            explanationView.setUpView(page.getAyatList().get(--positionAya), positionExplanation);
        } else {
            explanationView.showMessage(App.getInstance().getString(R.string.msgStartExplanationPage));
        }
    }

    @Override
    public void setPage(Page page) {
        this.page = page;
        // this make start from end of list
        positionExplanation = page.getAyatList().get(positionAya).getExplanationsList().size()-1;
//DatabaseHelper.getInstance().
        positionExplanation = page.getAyatList().get(positionAya).getExplanationsList().size()-1;
        explanationView.setUpView(page.getAyatList().get(positionAya), positionExplanation);
        explanationView.moveListToEnd();
    }

}
