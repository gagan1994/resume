package com.example.gagan.resumebuilder.models;

import com.example.gagan.resumebuilder.interfaces.Temp1BaseModel;

public class SkillsInfo implements Temp1BaseModel {
    String title;
    int pos;

    public SkillsInfo() {
        title = "Tap to enter skillls";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
