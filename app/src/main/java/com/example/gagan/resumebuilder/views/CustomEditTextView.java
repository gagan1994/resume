package com.example.gagan.resumebuilder.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

import com.example.gagan.resumebuilder.interfaces.EditCallBack;
import com.example.gagan.resumebuilder.utils.Utils;


public class CustomEditTextView extends AppCompatTextView implements EditCallBack {
    private String HINT = "Enter text here...";
    private final OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Utils.displayDialogue(CustomEditTextView.this);
        }
    };
    public OnClickListener listener;

    public CustomEditTextView(Context context) {
        super(context);
        initView();
    }


    public CustomEditTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomEditTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        setOnClickListener(onClickListener);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        listener = l;
        super.setOnClickListener(onClickListener);
    }

    @Override
    public void setTextFromDilogue(String text) {
        setText(text);
    }

    @Override
    public String getHintText() {
        return HINT;
    }
}
