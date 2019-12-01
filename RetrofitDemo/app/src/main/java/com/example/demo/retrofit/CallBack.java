package com.example.demo.retrofit;


/**
 * @author tengfei
 * date 2019-12-01 21:41
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface CallBack<T> {

    void onResponse(Call<T> call, Response<T> response);

    void onFailure(Call<T> call, Throwable t);
}
