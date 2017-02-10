package org.islamright.tebian.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class FahrasItem implements Serializable{

    private String id;
    private String name;
    private int start;
    private int end;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
