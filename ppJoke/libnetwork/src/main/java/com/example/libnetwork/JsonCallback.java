package com.example.libnetwork;

/**
 * @author tengfei
 * date 2020-02-03 19:14
 * email arrayadapter.cn@gmail.com
 * description
 */
public abstract class JsonCallback<T> {
    public void onSuccess(ApiResponse<T> response) {

    }

    public void onError(ApiResponse<T> response) {

    }

    public void onCacheSuccess(ApiResponse<T> response) {

    }
}
