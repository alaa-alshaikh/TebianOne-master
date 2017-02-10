package org.islamright.tebian.fahras;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.islamright.tebian.model.FahrasItem;
import org.islamright.tebian.util.Logging;
import org.islamright.tebian.util.Util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class FahrasInterActorImpl implements FahrasInterActor {

    @Override
    public void getFahras(FahrasOnFinishedListener listener, String fahrasPath) {
        try {
            String json = Util.readFileFromAssets(fahrasPath);
            Gson gson = new Gson();

            Type collectionType = new TypeToken<ArrayList<FahrasItem>>(){}.getType();
            ArrayList<FahrasItem> fahrasItemList = gson.fromJson(json, collectionType);
            listener.onFinish(fahrasItemList);
        } catch (IOException e) {
            Logging.e(getClass(), e);
        }
    }
}
