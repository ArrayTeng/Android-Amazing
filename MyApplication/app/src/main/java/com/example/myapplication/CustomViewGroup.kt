package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.RelativeLayout

/**
 * @author tengfei
 * date 2020-03-25 10:36
 * email arrayadapter.cn@gmail.com
 * description
 */
class CustomViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {


    companion object {
        const val TAG = "CustomViewGroup_test"
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e(TAG, "CustomViewGroup  ${getAction(ev!!.action)}")
        return super.dispatchTouchEvent(ev)
    }

    var hasViewGroupIntercept: Boolean = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        if (!hasViewGroupIntercept && ev?.action == MotionEvent.ACTION_MOVE) {
//            hasViewGroupIntercept = true
//            return true
//        }
        Log.e(TAG, "CustomViewGroup onInterceptTouchEvent")

        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e(TAG, "CustomViewGroup onTouchEvent")
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