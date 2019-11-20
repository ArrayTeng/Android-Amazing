package com.example.okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.okhttpdemo.okhttp.Call;
import com.example.okhttpdemo.okhttp.CallBack;
import com.example.okhttpdemo.okhttp.OkHttpClient;
import com.example.okhttpdemo.okhttp.Request;
import com.example.okhttpdemo.okhttp.Response;

import java.io.IOException;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Request request = new Request.Builder()
                .url("http://www.baidu.com/")
                .header("", "")
                .Build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new CallBack() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
