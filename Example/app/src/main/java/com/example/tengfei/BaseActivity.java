package com.example.tengfei;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.tengfei.view.TitleBar;

/**
 * @author tengfei
 * date 2019/2/1 11:11 AM
 * email tengfeigo@outlook.com
 * description 自定义通用基础 Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    private TitleBar mTitleBar;
    private RelativeLayout mBaseLayout;
    private FrameLayout mContentLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        initContentView();
        initOperation();
    }

    /**
     * initialization content layout
     */
    private void initContentView() {
        mBaseLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams contentLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);

        FrameLayout.LayoutParams rootViewParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);

        if (isShowTitleBar()) {
            initTitleBar();
            contentLayoutParams.addRule(RelativeLayout.BELOW, R.id.title_bar);
            mBaseLayout.addView(mTitleBar);
        }
        mContentLayout = new FrameLayout(this);
        mContentLayout.setId(R.id.content_layout);
        View contentChildView = LayoutInflater.from(this).inflate(setContentLayoutView(), null);
        mContentLayout.addView(contentChildView, rootViewParams);
        mBaseLayout.addView(mContentLayout, contentLayoutParams);

        setContentView(mBaseLayout, rootViewParams);
    }

    /**
     * 设置内容布局资源
     *
     * @return 内容布局填充内容布局资源
     */
    @LayoutRes
    public abstract int setContentLayoutView();

    /**
     * initialization titleBar
     */
    private void initTitleBar() {
        mTitleBar = new TitleBar(this);
        mTitleBar.setId(R.id.title_bar);
    }

    /**
     * initialization operation
     */
    public abstract void initOperation();

    /**
     * 设置是否显示标题栏
     *
     * @return if true show or not
     */
    public boolean isShowTitleBar() {
        return true;
    }
}
