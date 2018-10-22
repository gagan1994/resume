package com.example.gagan.resumebuilder.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class CustomZoomView extends LinearLayout {
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    public CustomZoomView(Context context) {
        super(context);
        initLayout(context);

    }

    public CustomZoomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);

    }

    public CustomZoomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    private void initLayout(Context context) {
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        //...
        //Your onDraw() code
        //...
        canvas.restore();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }

}
