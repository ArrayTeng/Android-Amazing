package com.example.touchdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author tengfei
 * date 2020-03-18 10:40
 * email arrayadapter.cn@gmail.com
 * description
 */
public class TouchView extends View {

    private static final String TAG = "TouchView_TAG";

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "TouchView -- dispatchTouchEvent : ");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "TouchView -- onTouchEvent : ");
        return super.onTouchEvent(event);
    }
}
