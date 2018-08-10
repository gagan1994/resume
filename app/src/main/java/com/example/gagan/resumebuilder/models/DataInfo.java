package com.example.gagan.resumebuilder.models;

import com.example.gagan.resumebuilder.interfaces.Temp1BaseModel;
import com.example.gagan.resumebuilder.utils.Utils;

import java.util.Date;

public class DataInfo implements Temp1BaseModel {
    Date from;
    Date to;
    String header;
    String description;
    String fromdate, todate;
    private String year;

    public DataInfo() {
        this.header = "Header here";
        this.description = "Description here...";
        year = "tap to add date";
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeader() {
        return header;
    }

    public String getYear() {

        return year;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setYear(String s) {
        year = s;
    }
}
