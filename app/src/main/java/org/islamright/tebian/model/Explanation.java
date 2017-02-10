package org.islamright.tebian.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import org.islamright.tebian.util.Key;

public class Explanation {
    private long ID;
    private long ayaID;
    @SerializedName("id")
    private long typeID;
    @SerializedName("name")
    private String name;
    @SerializedName("explanation")
    private String explanation;
    @SerializedName("verification")
    private String verification;


    public Explanation(Cursor c) {
       ID = c.getLong(c.getColumnIndex(Key.ID));
        ayaID = c.getLong(c.getColumnIndex(Key.AYA_ID));
        typeID = c.getLong(c.getColumnIndex(Key.TYPE_ID));
        name = c.getString(c.getColumnIndex(Key.NAME));
        explanation = c.getString(c.getColumnIndex(Key.EXPLANATION));
        verification = c.getString(c.getColumnIndex(Key.VERIFICATION));
    }


    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(Key.AYA_ID, ayaID);
        values.put(Key.TYPE_ID, typeID);
        values.put(Key.NAME, name);
        values.put(Key.EXPLANATION, explanation);
        values.put(Key.VERIFICATION, verification);
        return values;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getAyaID() {
        return ayaID;
    }

    public void setAyaID(long ayaID) {
        this.ayaID = ayaID;
    }

    public long getTypeID() {
        return typeID;
    }

    public void setTypeID(long typeID) {
        this.typeID = typeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    @Override
    public String toString() {
        return "Explanation{" +
                "ID=" + ID +
                ", ayaID=" + ayaID +
                ", typeID=" + typeID +
                ", name='" + name + '\'' +
                ", explanation='" + explanation + '\'' +
                ", verification='" + verification + '\'' +
                '}';
    }
}
