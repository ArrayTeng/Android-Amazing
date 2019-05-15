package com.example.butterknife.simple;

import android.support.annotation.UiThread;

/**
 * @author tengfei
 * date 2019/5/13 11:38 PM
 * email tengfeigo@outlook.com
 * description
 */
public interface Unbinder {

    @UiThread
    void unBinder();

    Unbinder EMPTY = new Unbinder() {
        @Override
        public void unBinder() {

        }
    };

}
