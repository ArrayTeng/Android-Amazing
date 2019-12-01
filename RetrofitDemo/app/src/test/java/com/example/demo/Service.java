package com.example.demo;

import com.example.demo.retrofit.Call;
import com.example.demo.retrofit.annotation.Field;
import com.example.demo.retrofit.annotation.GET;
import com.example.demo.retrofit.annotation.Query;

/**
 * @author tengfei
 * date 2019-12-01 00:42
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface Service {

    @GET("")
    Call<String> test(@Query("") String query, @Query("") String query2, @Query("") String query3, @Field("") String field);
}
