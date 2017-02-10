package org.islamright.tebian.explanation_reading_list;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AlaaAlShaikh on 29/10/2015.
 */
public class ExplanationOrReadingItem {
    @SerializedName("id")
    private int Id;
    @SerializedName("name")
    private String mName;
    @SerializedName("url")
    private String mUrl;

    public int getId() {
        return Id;
    }

    public void setId(int mId) {
        Id = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }


}
