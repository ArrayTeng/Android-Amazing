package com.example.okhttpdemo.okhttp;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    private Request originalRequest;

    private RealCall(OkHttpClient okHttpClient, Request request) {
        this.originalRequest = request;
        this.okHttpClient = okHttpClient;
    }

    public static RealCall newRealCall(OkHttpClient okHttpClient, Request request) {
        return new RealCall(okHttpClient, request);
    }


    @Override
    public void enqueue(CallBack responseCallBack) {
        okHttpClient.dispatcher.enqueue(new AsyncCall(responseCallBack));
    }

    public class AsyncCall extends NamedRunable {

        private static final String GET = "GET";

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

            Log.e(TAG, "execute: 任务最终被执行");

            try {
                URL url = new URL(originalRequest.url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(originalRequest.method.getName());
                connection.setDoOutput(originalRequest.method.doOutPut());

                RequestBody body = originalRequest.body;
                if (body!=null){
                    connection.setRequestProperty("Content-Type",body.type);
                    connection.setRequestProperty("Content-Length",String.valueOf(body.getLength()));
                }

                connection.connect();

                if (body!=null){
                    OutputStream outputStream = connection.getOutputStream();
                    body.onWriteStream(outputStream);
                }
                int responseCode = connection.getResponseCode();

                if (responseCode == 200) {
                    InputStream inputStream = connection.getInputStream();
                    Response response = new Response(inputStream);
                    callBack.onResponse(RealCall.this, response);
                }
            } catch (IOException e) {
                e.printStackTrace();
                callBack.onFailure(RealCall.this, e);
            }
        }
    }
}
