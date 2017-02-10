package org.islamright.tebian.explanation_reading_list;


import org.islamright.tebian.network.HttpRequest;


import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by AlaaAlShaikh on 29/10/2015.
 */
public class ExplanationOrReadingActor implements ExplanationOrReadingInterActor {

    @Override
    public void getExplanationReadingList(final OnFinishLoadingExplanationReadingListener mOnFinishLoadingExplanationReadingListener, String key) {


        HttpRequest.getInstance().apiService.getExplanation(key, new Callback<ArrayList<ExplanationOrReadingItem>>() {
            @Override
            public void success(ArrayList<ExplanationOrReadingItem> list, Response response) {
                mOnFinishLoadingExplanationReadingListener.finish(list);
            }

            @Override
            public void failure(RetrofitError error) {
                mOnFinishLoadingExplanationReadingListener.error(error.getMessage());
            }
        });

    }

}
