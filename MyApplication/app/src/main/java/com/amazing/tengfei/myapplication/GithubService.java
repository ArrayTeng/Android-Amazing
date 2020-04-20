package com.amazing.tengfei.myapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author tengfei
 * date 2020/4/13 9:56 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface GithubService {

    @GET("")
    Call<ResponseBody> getGithubService();
}
