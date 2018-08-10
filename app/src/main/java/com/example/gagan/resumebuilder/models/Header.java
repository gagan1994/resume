package com.example.gagan.resumebuilder.models;

import android.support.annotation.NonNull;

import com.example.gagan.resumebuilder.interfaces.Temp1BaseModel;

public class Header implements Temp1BaseModel {
    private int icon;
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Header(@NonNull String title) {
        this.title = title;
    }

    public Header(@NonNull String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public int getDrawable() {
        return icon;
    }
}
