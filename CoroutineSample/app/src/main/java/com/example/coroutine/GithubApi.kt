package com.example.coroutine

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author tengfei
 * date 2019/8/6 4:43 PM
 * email tengfeigo@outlook.com
 * description
 */

interface GithubApi {

    @GET("users/{login}")
    suspend fun getUser(@Path("login") login: String): ResponseBody

}