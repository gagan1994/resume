package com.example.gagan.resumebuilder.models;

import com.example.gagan.resumebuilder.interfaces.Temp1BaseModel;

public class Information implements Temp1BaseModel {
    String headers;
    String info;
    int iv_icons;

    public Information(String headers, String info, int iv_icons) {
        this.headers = headers;
        this.info = info;
        this.iv_icons = iv_icons;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getIv_icons() {
        return iv_icons;
    }

    public void setIv_icons(int iv_icons) {
        this.iv_icons = iv_icons;
    }
}
