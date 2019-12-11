package com.example.mvp.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author tengfei
 * date 2019-12-10 21:13
 * email arrayadapter.cn@gmail.com
 * description
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        mPresenter = createPresenter();
        mPresenter.attach(this);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P createPresenter();

    protected abstract void initContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }
}
