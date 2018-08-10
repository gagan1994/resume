package com.example.gagan.resumebuilder.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.gagan.resumebuilder.R;
import com.example.gagan.resumebuilder.interfaces.EditCallBack;

import java.util.Date;

public class Utils {
    public static void displayDialogue(final EditCallBack editCallBack) {
        final Dialog dialog = new Dialog(editCallBack.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialogue_layout);

        final TextInputEditText text = (TextInputEditText) dialog.findViewById(R.id.til_text);
        TextInputLayout til_layout = (TextInputLayout) dialog.findViewById(R.id.til_layout);
        text.setHint(editCallBack.getHintText());
        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialogDone);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCallBack.setTextFromDilogue(text.getText().toString());
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }


    public static String setFistLetterCaps(String text) {
        String upperString = TextUtils.isEmpty(text) ? "" :
                text.substring(0, 1).toUpperCase() + text.substring(1);
        return upperString;
    }

    public static String getYear(Date from, Date to) {
        if (from == null || to == null)
            return null;
        return from.getYear() + " - " + to.getYear();
    }
}
