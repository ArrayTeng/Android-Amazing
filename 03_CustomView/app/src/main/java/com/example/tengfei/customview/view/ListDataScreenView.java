package com.example.tengfei.customview.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.tengfei.customview.adapter.BaseMenuAdapter;

/**
 * @author tengfei 多条目筛选功能控件
 */
public class ListDataScreenView extends LinearLayout {

    private Context mContext;

    private LinearLayout mTabLinearLayout;
    private FrameLayout mMenuMiddleLayout, mContentView;
    private View mShadowView;
    private int shadowColor = Color.parseColor("#999999");

    private int mContentHeight;

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        mContentHeight = (int) (height * (75F / 100));
        ViewGroup.LayoutParams contentParams = mContentView.getLayoutParams();
        contentParams.height = mContentHeight;
        mContentView.setLayoutParams(contentParams);
        mContentView.setTranslationY(-mContentHeight);
    }

    private void initLayout() {
        //初始化顶部的 mTabLinearLayout
        setOrientation(VERTICAL);
        mTabLinearLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams tabLayoutParams =
                new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTabLinearLayout.setLayoutParams(tabLayoutParams);
        addView(mTabLinearLayout);

        //初始化内容栏 mMenuMiddleLayout
        mMenuMiddleLayout = new FrameLayout(mContext);
        LinearLayout.LayoutParams menuMiddleLayoutParams =
                new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0);
        menuMiddleLayoutParams.weight = 1;
        mMenuMiddleLayout.setLayoutParams(menuMiddleLayoutParams);
        addView(mMenuMiddleLayout);

        //初始化阴影效果 ShadowView
        mShadowView = new View(mContext);
        mShadowView.setBackgroundColor(shadowColor);
        mShadowView.setAlpha(0F);
        mShadowView.setVisibility(GONE);
        mMenuMiddleLayout.addView(mShadowView);

        //初始化内容界面 ContentView
        mContentView = new FrameLayout(mContext);
        mContentView.setBackgroundColor(Color.WHITE);
        mMenuMiddleLayout.addView(mContentView);
    }

    public void setAdapter(BaseMenuAdapter adapter) {
        if (adapter == null) {
            throw new NullPointerException("BaseMenuAdapter can't null");
        }
        for (int i = 0; i < adapter.getTabCount(); i++) {
            //获取菜单 TabView
            View tabView = adapter.getTabView(i, mTabLinearLayout);
            mTabLinearLayout.addView(tabView);
            LinearLayout.LayoutParams tabViewParams = (LayoutParams) tabView.getLayoutParams();
            tabViewParams.weight = 1;
            //get menu content view
            View contentMenuView = adapter.getMenuContentView(i, mMenuMiddleLayout);
            contentMenuView.setVisibility(GONE);
            mMenuMiddleLayout.addView(contentMenuView);
        }
    }
}
