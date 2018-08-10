package com.example.gagan.resumebuilder.interfaces;

import android.content.Context;

public interface EditCallBack {
    Context getContext();
    void setTextFromDilogue(String text);

    String getHintText();
}
