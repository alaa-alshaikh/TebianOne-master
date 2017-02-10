package org.islamright.tebian.network;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by AlaaAlShaikh on 08/04/15.
 */
public class HttpRequest extends RestAdapter.Builder {


    private static HttpRequest ourInstance = new HttpRequest();

    public final ApiEndpointInterface apiService;

    private HttpRequest() {
        setEndpoint(Apis.MAIN_URL).
                //.setLogLevel(RestAdapter.LogLevel.FULL)
                        setClient(new OkClient())
                .build();
        apiService = build().create(ApiEndpointInterface.class);
    }

    public static HttpRequest getInstance() {
        return ourInstance;
    }

}
