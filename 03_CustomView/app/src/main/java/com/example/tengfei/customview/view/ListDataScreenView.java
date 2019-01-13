package com.example.tengfei.customview.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * @author tengfei 多条目筛选功能控件
 */
public class ListDataScreenView extends LinearLayout {

    private Context mContext;

    private LinearLayout mTabLinearLayout;
    private FrameLayout mMenuMiddleLayout,mContentView;
    private View mShadowView;
    private int shadowColor = Color.parseColor("#999999");

    public ListDataScreenView(Context context) {
        this(context, null);
    }

    public ListDataScreenView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListDataScreenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initLayout();
    }

    private void initLayout() {
        //初始化顶部的 mTabLinearLayout
        mTabLinearLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams tabLayoutParams =
                new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTabLinearLayout.setLayoutParams(tabLayoutParams);
        addView(mTabLinearLayout);
        //初始化内容栏 mMenuMiddleLayout
        mMenuMiddleLayout = new FrameLayout(mContext);
        LinearLayout.LayoutParams contentLayoutParams =
                new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mMenuMiddleLayout.setLayoutParams(contentLayoutParams);
        addView(mMenuMiddleLayout);
        //初始化阴影效果
        mShadowView = new View(mContext);
        mShadowView.setBackgroundColor(shadowColor);
        mMenuMiddleLayout.addView(mShadowView);
        //初始化内容界面
        mContentView = new FrameLayout(mContext);
        mMenuMiddleLayout.addView(mContentView);
    }
}
