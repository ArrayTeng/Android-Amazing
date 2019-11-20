package com.example.okhttpdemo.okhttp;

import java.io.IOException;


/**
 * @author tengfei
 * date 2019-11-20 10:59
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface CallBack {

    void onFailure(Call call, IOException e);


    void onResponse(Call call, Response response) throws IOException;

}
