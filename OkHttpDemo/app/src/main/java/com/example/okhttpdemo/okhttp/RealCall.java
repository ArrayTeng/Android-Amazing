package com.example.okhttpdemo.okhttp;

import android.util.Log;

import java.util.concurrent.ExecutorService;

/**
 * @author tengfei
 * date 2019-11-20 10:52
 * email arrayadapter.cn@gmail.com
 * description
 */
public class RealCall implements Call {

    private static final String TAG = "RealCall_TAG";

    private OkHttpClient okHttpClient;

    private RealCall(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public static RealCall newRealCall(OkHttpClient okHttpClient) {
        return new RealCall(okHttpClient);
    }


    @Override
    public void enqueue(CallBack responseCallBack) {
        okHttpClient.dispatcher.enqueue(new AsyncCall(responseCallBack));
    }

    public class AsyncCall extends NamedRunable {

        CallBack callBack;

        public AsyncCall(CallBack responseCallBack) {
            super();
            this.callBack = responseCallBack;
        }


        void executeOn(ExecutorService executorService) {
            //callBack.onResponse(RealCall.this,null);
            executorService.execute(this);
        }

        @Override
        void execute() {
            Log.e(TAG, "execute: 任务最终被执行" );
        }
    }
}
