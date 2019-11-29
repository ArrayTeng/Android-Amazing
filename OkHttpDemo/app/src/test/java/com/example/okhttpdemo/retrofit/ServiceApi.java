package com.example.okhttpdemo.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author tengfei
 * date 2019-11-29 11:27
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface ServiceApi {

    @GET("")
    Call<Result<Entity>> getEntityInfo();
}
