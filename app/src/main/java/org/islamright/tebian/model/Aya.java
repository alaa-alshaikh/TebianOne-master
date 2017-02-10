package org.islamright.tebian.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Util;

import java.util.ArrayList;


public class Aya {

    private long ID;
    private long pageID;
    private int pageNumber;
    @SerializedName("aya_number")
    private int ayaNumber;
    @SerializedName("aya_number_general")
    private int ayaNumberGeneral;
    @SerializedName("soura_number")
    private int soraNumber;
    @SerializedName("sora_name")
    private String soraName;
    @SerializedName("aya_text")
    private String text;
    @SerializedName("aya_name_without_tashkil")
    private String textWithoutTashkil;
    @SerializedName("x")
    private double x;
    @SerializedName("y")
    private double y;
    @SerializedName("w")
    private double w;
    @SerializedName("h")
    private double h;
    @SerializedName("new_x")
    private double newX;
    @SerializedName("new_y")
    private double newY;
    @SerializedName("new_w")
    private double newW;
    @SerializedName("new_h")
    private double newH;
    @SerializedName("explanation")
    private ArrayList<Explanation> explanationsList;

    public Aya(Cursor cursor) {
        ID = cursor.getLong(cursor.getColumnIndex(Key.ID));
        ayaNumber = cursor.getInt(cursor.getColumnIndex(Key.AYA_NUMBER));
        ayaNumberGeneral = cursor.getInt(cursor.getColumnIndex(Key.AYA_NUMBER_GENERAL));
        pageID = cursor.getInt(cursor.getColumnIndex(Key.PAGE_ID));
        pageNumber = cursor.getInt(cursor.getColumnIndex(Key.PAGE_NUMBER));
        soraName = cursor.getString(cursor.getColumnIndex(Key.SORA_NAME));
        text = cursor.getString(cursor.getColumnIndex(Key.TEXT));
        textWithoutTashkil = cursor.getString(cursor.getColumnIndex(Key.TEXT_WITHOUT_TASHKIL));
        x = cursor.getDouble(cursor.getColumnIndex(Key.X));
        y = cursor.getDouble(cursor.getColumnIndex(Key.Y));
        h = cursor.getDouble(cursor.getColumnIndex(Key.H));
        w = cursor.getDouble(cursor.getColumnIndex(Key.W));
        newX = cursor.getDouble(cursor.getColumnIndex(Key.NEW_X));
        newY = cursor.getDouble(cursor.getColumnIndex(Key.NEW_Y));
        newH = cursor.getDouble(cursor.getColumnIndex(Key.NEW_H));
        newW = cursor.getDouble(cursor.getColumnIndex(Key.NEW_W));
    }

    public Aya() {

    }


    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(Key.AYA_NUMBER, ayaNumber);
        values.put(Key.AYA_NUMBER_GENERAL, ayaNumberGeneral);
        values.put(Key.PAGE_ID, pageID);
        values.put(Key.PAGE_NUMBER, pageNumber);
        values.put(Key.SORA_NAME, soraName);
        values.put(Key.SORA_NUMBER, soraNumber);
        values.put(Key.TEXT, text);
        values.put(Key.TEXT_WITHOUT_TASHKIL, textWithoutTashkil);
        values.put(Key.X, Util.applyEquationToWidth(x));
        values.put(Key.Y, Util.applyEquationToHeight(y));
        values.put(Key.W, Util.applyEquationToWidth(w));
        values.put(Key.H, Util.applyEquationToWidth(h));
        values.put(Key.NEW_X, newX);
        values.put(Key.NEW_Y, newY);
        values.put(Key.NEW_W, newW);
        values.put(Key.NEW_H, newH);
        return values;
    }

    public ContentValues getContentValuesFTS(long ID) {
        ContentValues values = new ContentValues();
        values.put(Key.ID, ID);
        values.put(Key.AYA_NUMBER, ayaNumber);
        values.put(Key.AYA_NUMBER_GENERAL, ayaNumberGeneral);
        values.put(Key.PAGE_ID, pageID);
        values.put(Key.PAGE_NUMBER, pageNumber);
        values.put(Key.SORA_NAME, soraName);
        values.put(Key.SORA_NUMBER, soraNumber);
        values.put(Key.TEXT, text);
        values.put(Key.TEXT_WITHOUT_TASHKIL, textWithoutTashkil);
        values.put(Key.X, Util.applyEquationToWidth(x));
        values.put(Key.Y, Util.applyEquationToHeight(y));
        values.put(Key.W, Util.applyEquationToWidth(w));
        values.put(Key.H, Util.applyEquationToWidth(h));
        values.put(Key.NEW_X, newX);
        values.put(Key.NEW_Y, newY);
        values.put(Key.NEW_W, newW);
        values.put(Key.NEW_H, newH);
        return values;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getPageID() {
        return pageID;
    }

    public void setPageID(long pageID) {
        this.pageID = pageID;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getAyaNumber() {
        return ayaNumber;
    }

    public void setAyaNumber(int ayaNumber) {
        this.ayaNumber = ayaNumber;
    }

    public int getAyaNumberGeneral() {
        return ayaNumberGeneral;
    }

    public void setAyaNumberGeneral(int ayaNumberGeneral) {
        this.ayaNumberGeneral = ayaNumberGeneral;
    }

    public int getSoraNumber() {
        return soraNumber;
    }

    public void setSoraNumber(int soraNumber) {
        this.soraNumber = soraNumber;
    }

    public String getSoraName() {
        return soraName;
    }

    public void setSoraName(String soraName) {
        this.soraName = soraName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextWithoutTashkil() {
        return textWithoutTashkil;
    }

    public void setTextWithoutTashkil(String textWithoutTashkil) {
        this.textWithoutTashkil = textWithoutTashkil;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getNewX() {
        return newX;
    }

    public void setNewX(double newX) {
        this.newX = newX;
    }

    public double getNewY() {
        return newY;
    }

    public void setNewY(double newY) {
        this.newY = newY;
    }

    public double getNewW() {
        return newW;
    }

    public void setNewW(double newW) {
        this.newW = newW;
    }

    public double getNewH() {
        return newH;
    }

    public void setNewH(double newH) {
        this.newH = newH;
    }

    public ArrayList<Explanation> getExplanationsList() {
        return explanationsList;
    }

    public void setExplanationsList(ArrayList<Explanation> explanationsList) {
        this.explanationsList = explanationsList;
    }

    @Override
    public String toString() {
        String aya = "Aya{" +
                "ID=" + ID +
                ", pageID=" + pageID +
                ", pageNumber=" + pageNumber +
                ", ayaNumber=" + ayaNumber +
                ", ayaNumberGeneral=" + ayaNumberGeneral +
                ", soraNumber=" + soraNumber +
                ", soraName='" + soraName + '\'' +
                ", text='" + text + '\'' +
                ", textWithoutTashkil='" + textWithoutTashkil + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", h=" + h +
                ", newX=" + newX +
                ", newY=" + newY +
                ", newW=" + newW +
                ", newH=" + newH
                ;

        for (Explanation explanation : explanationsList){
            aya += explanation.toString();
        }
               aya+= '}';
        return aya;
    }
}

