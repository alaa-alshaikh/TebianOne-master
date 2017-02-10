package org.islamright.tebian.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import org.islamright.tebian.util.Key;

import java.util.ArrayList;

public class Page {

    private long ID;
    @SerializedName("page_number")
    private int pageNumber;
    @SerializedName("sora_name")
    private String soraName;
    @SerializedName("jzua_number")
    private int jzuaNumber;
    @SerializedName("ayat")
    private ArrayList<Aya> ayatList;


    public Page(Cursor cursor) {
        ID = cursor.getLong(cursor.getColumnIndex(Key.ID));
        pageNumber = cursor.getInt(cursor.getColumnIndex(Key.PAGE_NUMBER));
        soraName = cursor.getString((cursor.getColumnIndex(Key.SORA_NAME)));
        jzuaNumber = cursor.getInt((cursor.getColumnIndex(Key.JZUA_NUMBER)));
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(Key.PAGE_NUMBER, pageNumber);
        values.put(Key.SORA_NAME, soraName);
        values.put(Key.JZUA_NUMBER, jzuaNumber);
        return values;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSoraName() {
        return soraName;
    }

    public void setSoraName(String soraName) {
        this.soraName = soraName;
    }

    public int getJzuaNumber() {
        return jzuaNumber;
    }

    public void setJzuaNumber(int jzuaNumber) {
        this.jzuaNumber = jzuaNumber;
    }

    public ArrayList<Aya> getAyatList() {
        return ayatList;
    }

    public void setAyatList(ArrayList<Aya> ayatList) {
        this.ayatList = ayatList;
    }

    @Override
    public String toString() {
        String page = "Page{" +
                "ID=" + ID +
                ", pageNumber=" + pageNumber +
                ", soraName='" + soraName + '\'' +
                ", jzuaNumber=" + jzuaNumber +
                '}';
        for (Aya aya : ayatList) {
            page += aya.toString();
        }
        page += '}';
        return page;
    }
}
