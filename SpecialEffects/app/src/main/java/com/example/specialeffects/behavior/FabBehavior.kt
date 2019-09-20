package com.example.specialeffects.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.RelativeLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton.*

/**
 * @author tengfei
 * date 2019-09-20 12:33
 * email tengfeigo@outlook.com
 * description
 */

class FabBehavior(private val context: Context?,private val attrs: AttributeSet?): Behavior(context,attrs) {

    var isAnimationOut :Boolean = false




    /**
     * 当我们依赖的滑动控件（观察的View）开始滑动的时候回调这个方法
     * axes 滑动关联轴
     */
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        // axes == ViewCompat.SCROLL_AXIS_VERTICAL 表示我只关心垂直方向的滑动
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(
            coordinatorLayout, child, directTargetChild, target, axes, type
        )
    }


    /**
     * 当观察的View滑动的时候回调
     */
    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type
        )

        Log.i("tmd","已经消费的Y方向的距离   $dyConsumed   , Y方向剩下的距离   $dyUnconsumed   ")
        // 根据情况执行动画，FloatingActionButton 我想分为两种情况 hide 和 show
        if (dyConsumed > 0 && !isAnimationOut) {
            //大于 0 时表示往下滑动否则往上滑动 show
            onShow(child)
            isAnimationOut = false

        } else if (dyConsumed < 0) {
            //hide
            isAnimationOut = true
            onHide(child)
        }
    }

    private fun onHide(child: FloatingActionButton) {
        ViewCompat.animate(child).scaleX(0F).scaleY(0F).start()
    }

    private fun onShow(child: FloatingActionButton) {
        val layoutParams = child.layoutParams as CoordinatorLayout.LayoutParams
        child.animate().translationY(0F).setInterpolator(DecelerateInterpolator(3F))
    }

}