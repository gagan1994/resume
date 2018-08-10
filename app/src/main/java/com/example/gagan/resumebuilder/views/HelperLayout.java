package com.example.gagan.resumebuilder.views;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelperLayout extends FlowLayout {
    private Context mContext;


    public HelperLayout(Context context) {
        super(context);
        mContext = context;
    }

    public HelperLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    CloseClickedHelper respondClick;

    public void setCloseClickedListener(CloseClickedHelper closeclick) {
        respondClick = closeclick;
    }

    public void add(Object id, String title) {
        CustomLayout c = new CustomLayout(mContext, id, title);

        c.setOnClosClick(new CustomLayout.IOnCloseClick() {
            @Override
            public void OnCloseClick(Object id, String title, LinearLayout closeObj) {
                if (respondClick != null)
                    respondClick.CloseClicked(id, title);
                removeView(closeObj);
            }
        });

        addView(c);
    }

    public void clearViews(){
        removeAllViews();
    }


   /* public void removeTag(Object obj){
        removeView(closeObj);
    }*/

    public interface CloseClickedHelper {
        void CloseClicked(Object id, String title);
    }
}
