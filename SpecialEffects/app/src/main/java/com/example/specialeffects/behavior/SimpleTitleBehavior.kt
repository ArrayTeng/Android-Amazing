package com.example.specialeffects.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * @author tengfei
 * date 2019-09-22 20:50
 * email tengfeigo@outlook.com
 * description
 */
class SimpleTitleBehavior(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    var deltay: Float = 0F

    /**
     * 指定你依赖的是什么View
     */
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is RecyclerView
    }


    /**
     * 当依赖的View发生改变的时候回调
     */
    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        if (deltay == 0F) {
            //当重合在一起时RecyclerView滑动的距离
            deltay = dependency.y - child.height
        }

        //慢慢向上滑动的距离
        var dy: Float = dependency.y - child.height
        dy = if (dy < 0F) 0F else dy
        var childTranslationY = -(dy / deltay) * child.height
        child.translationY = childTranslationY
        return true
    }
}