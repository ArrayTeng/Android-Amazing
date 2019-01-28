package com.example.tengfei.customview.view;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * @author tengfei
 * date 2019/1/28 2:20 PM
 * email tengfeigo@outlook.com
 * description
 */
public class ImGestureDetector extends GestureDetector.SimpleOnGestureListener {
    private static final String TAG = ImGestureDetector.class.getName();

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.i(TAG, "onSingleTapConfirmed");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i(TAG, "onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        switch (e.getActionMasked()) {
            case MotionEvent.ACTION_UP:
                Log.i(TAG,"onDoubleTapEvent");
                break;
            default:
                break;
        }
        return super.onDoubleTapEvent(e);
    }
}
