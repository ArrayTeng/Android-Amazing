package com.example.tengfei.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import androidx.core.view.get
import com.example.tengfei.R
import com.example.tengfei.model.BottomBar
import com.example.tengfei.utils.AppConfig
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode


/**
 * @author tengfei
 * date 2020-02-02 19:10
 * email arrayadapter.cn@gmail.com
 * description
 */
class AppBottomBar : BottomNavigationView {

    private var sBottomBar: BottomBar? = null

    private val iconArray = intArrayOf(
        R.drawable.icon_tab_home,
        R.drawable.icon_tab_sofa,
        R.drawable.icon_tab_publish,
        R.drawable.icon_tab_find,
        R.drawable.icon_tab_mine
    )

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        sBottomBar = AppConfig.getBottomBar()
        initView()
    }

    @SuppressLint("RestrictedApi")
    private fun initView() {

        val state: Array<IntArray?> = arrayOfNulls(2)
        state[0] = intArrayOf(android.R.attr.state_selected)
        state[1] = intArrayOf()

        val colors = intArrayOf(
            Color.parseColor(sBottomBar?.activeColor),
            Color.parseColor(sBottomBar?.inActiveColor)
        )
        val colorStateList = ColorStateList(state, colors)
        //设置文本以及图片在不同状态的颜色
        itemTextColor = colorStateList
        itemIconTintList = colorStateList

        labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED

        val tabBeans = sBottomBar?.tabs



        for (index in tabBeans!!.indices) {

            val tabBean = tabBeans[index]
            if (!tabBean.enable) continue
            val id = getItemId(tabBean.pageUrl)
            if (id == -1) continue

            val menuItem = menu.add(0, id, tabBean.index, tabBean.title)
            menuItem.setIcon(iconArray[tabBean.index])
        }

        for (index in tabBeans.indices) {
            val tabBean = tabBeans[index]
            if (!tabBean.enable) continue
            val id = getItemId(tabBean.pageUrl)
            if (id == -1) continue
            val bottomNavigationMenuView = getChildAt(0) as BottomNavigationMenuView
            val bottomNavigationItemView =
                bottomNavigationMenuView[index] as BottomNavigationItemView

            bottomNavigationItemView.setIconSize(dp2Px(tabBean.size))

            if (TextUtils.isEmpty(tabBean.title)) {
                bottomNavigationItemView.setIconTintList(
                    ColorStateList.valueOf(
                        Color.parseColor(
                            tabBean.tintColor
                        )
                    )
                )
                bottomNavigationItemView.setShifting(false)
            }
        }
    }

    private fun dp2Px(dpValue: Int): Int {
        val metrics = context.resources.displayMetrics
        return (metrics.density * dpValue + 0.5f).toInt()
    }

    private fun getItemId(pageUrl: String): Int {
        val destination = AppConfig.getDestConfig()?.get(pageUrl) ?: return -1
        return destination.id
    }

}