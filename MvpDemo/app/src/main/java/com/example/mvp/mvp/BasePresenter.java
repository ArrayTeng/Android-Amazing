package com.example.mvp.mvp;

/**
 * @author tengfei
 * date 2019-12-10 21:13
 * email arrayadapter.cn@gmail.com
 * description
 */
public class BasePresenter<V extends BaseView> {

    private V mView;

    public void attach(V view) {
        this.mView = view;
    }

    public void detach() {
        this.mView = null;
    }

    public V mView() {
        return mView;
    }
}
