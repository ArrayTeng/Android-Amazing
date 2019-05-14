package com.library.butterknife;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;

/**
 * @author tengfei
 * date 2019/5/13 11:38 PM
 * email tengfeigo@outlook.com
 * description
 */
public interface Unbinder {

    @UiThread
    @CallSuper
    void unBinder();

    Unbinder EMPTY = new Unbinder() {
        @Override
        public void unBinder() {
        }
    };
}
