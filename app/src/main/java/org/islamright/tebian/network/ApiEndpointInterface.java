package org.islamright.tebian.network;

import org.islamright.tebian.explanation_reading_list.ExplanationOrReadingItem;
import org.islamright.tebian.model.Page;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public interface ApiEndpointInterface {

    @GET(Apis.PAGE)
    void getPage(@Path("pageNumber") int pageNumber, Callback<ArrayList<Page>> callback);

    @POST(Apis.EXPLANATION_LIST)
    void getExplanation(@Path("list") String path, Callback<ArrayList<ExplanationOrReadingItem>> callback);
}
