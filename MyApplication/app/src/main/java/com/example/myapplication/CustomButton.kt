package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.Button

/**
 * @author tengfei
 * date 2020-03-25 10:51
 * email arrayadapter.cn@gmail.com
 * description
 */
class CustomButton @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? =
        null, defStyleAttr: Int = 0
) : Button(context, attributeSet, defStyleAttr) {

    companion object {
        const val TAG = "CustomViewGroup_test"
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.e(TAG, "CusButton ${getAction(event?.action)}")
        return true
    }


    private fun getAction(action: Int?): String = when (action) {
        MotionEvent.ACTION_DOWN -> "dispatchTouchEvent ACTION_DOWN"
        MotionEvent.ACTION_MOVE -> "dispatchTouchEvent ACTION_MOVE"
        MotionEvent.ACTION_UP -> "dispatchTouchEvent ACTION_UP"
        MotionEvent.ACTION_CANCEL -> "dispatchTouchEvent ACTION_CANCEL"
        else -> "other"
    }


}