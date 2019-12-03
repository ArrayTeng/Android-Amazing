package com.example.demo.service;

import com.example.demo.retrofit.Call;
import com.example.demo.retrofit.annotation.GET;
import com.example.demo.retrofit.annotation.Query;


/**
 * @author tengfei
 * date 2019-12-01 00:42
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface IService {
    //http://gank.io/api/xiandu/categories
    @GET("xiandu/data/id/appinn/count/10/page/1")
    Call<String> test(@Query("query01") String query, @Query("query02") String query2, @Query("query03") String query3);
}
