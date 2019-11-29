package com.example.okhttpdemo.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author tengfei
 * date 2019-11-29 11:27
 * email arrayadapter.cn@gmail.com
 * description
 */
public class RetrofitClient {

    private final  Retrofit retrofit;

    private RetrofitClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("")
                .client(okHttpClient)
                .build();
    }

    public static RetrofitClient getInstance() {
        return Holder.RETROFIT_CLIENT;
    }

    private static final class Holder {
        private static final RetrofitClient RETROFIT_CLIENT = new RetrofitClient();
    }

    public ServiceApi getServiceApi(){
        return retrofit.create(ServiceApi.class);
    }
}
