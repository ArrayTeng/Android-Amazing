package com.example.specialeffects.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import java.util.*
import kotlin.math.max

/**
 * @author tengfei
 * date 2019-10-13 22:43
 * email tengfeigo@outlook.com
 * description
 */
class TagLayout : ViewGroup {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}


    private var rectList = ArrayList<Rect>()

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightUsed = 0
        var verticalHeightUsed = 0
        var widthUsed = 0
        var widthLineUsed = 0

        val spaceWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        val spaceWidthMode = MeasureSpec.getMode(widthMeasureSpec)

        for (index in 0 until childCount) {
            val childView = getChildAt(index)
            measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            //换行的逻辑操作
            if (spaceWidthMode != MeasureSpec.UNSPECIFIED && widthLineUsed + childView.measuredWidth > spaceWidthSize) {
                widthLineUsed = 0
                heightUsed += verticalHeightUsed
            }
            val boundRect = Rect()
            rectList.add(boundRect)
            boundRect.set(
                widthLineUsed,
                heightUsed,
                widthLineUsed + childView.measuredWidth,
                heightUsed + childView.measuredHeight
            )

            widthLineUsed += childView.measuredWidth
            widthUsed = max(widthUsed, widthLineUsed)
            //一行当中高度最高的那个 child View
            verticalHeightUsed = max(verticalHeightUsed,childView.measuredHeight)
        }

        val width: Int = widthUsed
        val height: Int = verticalHeightUsed
        setMeasuredDimension(width, height)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        for (index in 0 until childCount) {
            val childView = getChildAt(index)
            val rect = rectList[index]
            childView.layout(rect.left, rect.top, rect.right, rect.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet): ViewGroup.LayoutParams {
        return ViewGroup.MarginLayoutParams(context, attrs)
    }


}