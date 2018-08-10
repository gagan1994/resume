package com.example.gagan.resumebuilder.views;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.gagan.resumebuilder.R;
import com.example.gagan.resumebuilder.utils.Utils;

public class CustomLayout extends LinearLayout {
    EditText text;
    Context context;
    String title;
    Object id;
    IOnCloseClick iOnCloseClick;
    LinearLayout linear;
    private GestureDetector gestureDetector;

    public interface IOnCloseClick {
        void OnCloseClick(Object id, String title, LinearLayout closeObj);
    }

    LayoutParams paramLinear = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    LayoutParams paramImage = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    LayoutParams paramText = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    LayoutParams paramToThis = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);

    public CustomLayout(Context context, Object id, String title) {
        super(context);
        this.title = title;
        this.context = context;
        this.id = id;
        init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner));
        }
    }

    private void init() {
        paramLinear.setMargins(10, 10, 10, 10);
        linear = new LinearLayout(context);
        linear.setLayoutParams(paramLinear);
        linear.setPadding(18, 8, 8, 8);


        text = new EditText(context);
        text.setLayoutParams(paramText);
        text.setText(Utils.setFistLetterCaps(title));
        text.setPadding(10, 5, 5, 5);
        text.setSingleLine();

        text.setTextSize(context.getResources().getDimension(R.dimen.header_size2));
        text.setFocusable(false);
        text.setCursorVisible(false);
        text.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner));
        text.setTextColor(Color.parseColor("#424242"));

        text.setPadding(10, 5, 5, 5);
        text.setSingleLine();
        text.setEllipsize(TextUtils.TruncateAt.END);
        text.setCompoundDrawablePadding(5);
        text.setFocusable(false);
        text.setCursorVisible(false);
        linear.addView(text);
        // linear.addView(close);

        text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iOnCloseClick != null) {
                    iOnCloseClick.OnCloseClick(id, title, getThis());
                }
            }
        });

        setLayoutParams(paramToThis);

        this.addView(linear);
        text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iOnCloseClick != null) {
                    iOnCloseClick.OnCloseClick(id, title, getThis());
                }
            }
        });

        this.setVerticalGravity(Gravity.CENTER_VERTICAL);
    }

    private LinearLayout getThis() {
        return this;
    }

    public void setOnClosClick(IOnCloseClick iOnCloseClick) {
        this.iOnCloseClick = iOnCloseClick;
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            //do something
            return true;
        }
    }

}
