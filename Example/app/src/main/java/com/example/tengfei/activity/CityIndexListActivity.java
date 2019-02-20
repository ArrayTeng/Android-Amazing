package com.example.tengfei.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.JsonToken;
import android.util.Log;
import android.view.View;

import com.example.tengfei.BaseActivity;
import com.example.tengfei.R;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * @author tengfei
 * date 2019/1/29 5:48 PM
 * email tengfeigo@outlook.com
 * description 城市索引列表
 */
public class CityIndexListActivity extends BaseActivity {

    @Override
    public int setContentLayoutView() {
        return R.layout.activity_city_index_list;
    }

    @Override
    public void initOperation(@Nullable Bundle savedInstanceState) {


        CreateAuthorization createAuthorization = new CreateAuthorization();

        createAuthorization.note = GithubConfig.NOTE;
        createAuthorization.client_id = GithubConfig.CLIENT_ID;
        createAuthorization.client_secret = GithubConfig.CLIENT_SECRET;
        createAuthorization.scopes = GithubConfig.SCOPES;
        // https://developer.github.com/v3/oauth/#non-web-application-flow
        String userCredentials = "TengfeiGo" + ":" + "TengFei1995";
        Gson gson = new Gson();
        String basicAuth =
                "Basic " + new String(Base64.encode(userCredentials.getBytes(), Base64.DEFAULT));
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), gson.toJson(createAuthorization));
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://api.github.com/user?access_token=a5ce084e4f67f90f82dd7e6a437e524cf946e78e")
//                        .post(requestBody)
                        .addHeader("Authorization", basicAuth.trim())
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("tmd", "failure" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.i("tmd", response.body().string());
                    }
                });
            }
        });

    }

}
