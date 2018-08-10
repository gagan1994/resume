package com.example.gagan.resumebuilder.models;

import com.example.gagan.resumebuilder.interfaces.Temp1BaseModel;

import java.util.ArrayList;
import java.util.List;

public class ItemList implements Temp1BaseModel {
    private static List<String> stringList = new ArrayList<>();

    public List<String> getData() {
        return stringList;
    }
}
