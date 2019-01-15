package com.example.tengfei.customview.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
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
public class ListDataScreenView extends LinearLayout implements View.OnClickListener {

    private Context mContext;
    /**
     * 创建头部用来存放 Tab
     */
    private LinearLayout mMenuTabView;
    /**
     * 创建 FrameLayout 用来存放 = 阴影（View） + 菜单内容布局(FrameLayout)
     */
    private FrameLayout mMenuMiddleView;
    /**
     * 阴影
     */
    private View mShadowView;
    /**
     * 创建菜单用来存放菜单内容
     */
    private FrameLayout mMenuContainerView;
    /**
     * 阴影的颜色
     */
    private int mShadowColor = 0x88888888;
    /**
     * 筛选菜单的 Adapter
     */
    private BaseMenuAdapter mAdapter;
    /**
     * 内容菜单的高度
     */
    private int mMenuContainerHeight;
    /**
     * 当前打开的位置
     */
    private int mCurrentPosition = -1;
    private long DURATION_TIME = 350;
    /**
     * 动画是否在执行
     */
    private boolean mAnimatorExecute;

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
        mMenuContainerHeight = (int) (height * 75F / 100);
        ViewGroup.LayoutParams contentParams = mMenuContainerView.getLayoutParams();
        contentParams.height = mMenuContainerHeight;
        mMenuContainerView.setLayoutParams(contentParams);
        mMenuContainerView.setTranslationY(-mMenuContainerHeight);
    }

    private void initLayout() {
        //初始化顶部的 mTabLinearLayout
        setOrientation(VERTICAL);
        mMenuTabView = new LinearLayout(mContext);
        LinearLayout.LayoutParams tabLayoutParams =
                new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mMenuTabView.setLayoutParams(tabLayoutParams);
        addView(mMenuTabView);

        //初始化内容栏 mMenuMiddleLayout 用来存放菜单 + 内容布局View
        mMenuMiddleView = new FrameLayout(mContext);
        LinearLayout.LayoutParams menuMiddleLayoutParams =
                new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0);
        menuMiddleLayoutParams.weight = 1;
        mMenuMiddleView.setLayoutParams(menuMiddleLayoutParams);
        addView(mMenuMiddleView);

        //初始化阴影效果 ShadowView
        mShadowView = new View(mContext);
        mShadowView.setBackgroundColor(mShadowColor);
        mShadowView.setAlpha(0F);
        mShadowView.setOnClickListener(this);
        mShadowView.setVisibility(GONE);
        mMenuMiddleView.addView(mShadowView);

        //初始化菜单内容界面 ContentView
        mMenuContainerView = new FrameLayout(mContext);
        mMenuContainerView.setBackgroundColor(Color.WHITE);
        mMenuMiddleView.addView(mMenuContainerView);
    }

    public void setAdapter(BaseMenuAdapter adapter) {
        if (adapter == null) {
            throw new NullPointerException("BaseMenuAdapter can't null");
        }
        this.mAdapter = adapter;
        for (int i = 0; i < adapter.getTabCount(); i++) {
            //获取菜单 TabView
            View tabView = adapter.getTabView(i, mMenuTabView);
            mMenuTabView.addView(tabView);
            LinearLayout.LayoutParams tabViewParams = (LayoutParams) tabView.getLayoutParams();
            tabViewParams.weight = 1;
            setTabViewClick(tabView, i);
            //get menu content view
            View contentMenuView = adapter.getMenuContentView(i, mMenuContainerView);
            contentMenuView.setVisibility(GONE);
            mMenuContainerView.addView(contentMenuView);
        }
    }

    private void setTabViewClick(final View tabView, final int position) {
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentPosition == -1) {
                    openMenu(position);
                } else {
                    closeMenu();
                }
            }
        });
    }

    private void closeMenu() {
        //关闭动画，位移动画，透明度动画
        //初始化平移动画
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mMenuContainerView, "translationY", 0, -mMenuContainerHeight);
        translationAnimator.setDuration(DURATION_TIME);
        translationAnimator.start();
        //初始化透明度动画
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mShadowView, "alpha", 1F, 0F);
        alphaAnimator.setDuration(DURATION_TIME);
        alphaAnimator.start();
        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View contentMenuView = mMenuContainerView.getChildAt(mCurrentPosition);
                contentMenuView.setVisibility(GONE);
                mCurrentPosition = -1;
                mShadowView.setVisibility(GONE);
            }
        });

    }

    private void openMenu(int position) {
        mShadowView.setVisibility(VISIBLE);
        //打开动画，位移动画，透明度动画
        //初始化平移动画
        View contentMenuView = mMenuContainerView.getChildAt(position);
        contentMenuView.setVisibility(VISIBLE);
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mMenuContainerView, "translationY", -mMenuContainerHeight, 0);
        translationAnimator.setDuration(DURATION_TIME);
        translationAnimator.start();
        //初始化透明度动画
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mShadowView, "alpha", 0F, 1F);
        alphaAnimator.setDuration(DURATION_TIME);
        alphaAnimator.start();
        mCurrentPosition = position;
    }

    @Override
    public void onClick(View v) {
        closeMenu();
    }
}
