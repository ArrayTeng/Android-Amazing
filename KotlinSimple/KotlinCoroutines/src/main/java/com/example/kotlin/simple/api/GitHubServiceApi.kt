package com.example.kotlin.simple.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author tengfei
 * date 2019/8/5 9:21 AM
 * email tengfeigo@outlook.com
 * description
 */
interface GitHubServiceApi {
    @GET("users/{login}")
    fun getUser(@Path("login") login:String):Deferred<>
}