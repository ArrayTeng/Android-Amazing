package com.example.okhttpdemo;


import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void test(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()

                .build();

        HttpUrl url = new HttpUrl.Builder()
                .addQueryParameter("","")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();


        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

}