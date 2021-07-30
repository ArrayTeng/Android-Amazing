package com.example.easyokhttp.okhttp

import kotlin.math.max

class Dispatcher constructor(){

    //同时访问服务器，不同域名最大限制64个
    @get:Synchronized var maxRequests = 64
    set(maxRequests) {
        synchronized(this){
            field = maxRequests
        }
    }

    //访问同一域名服务器，最大限制5个
    @get:Synchronized var maxRequestsPerHost = 5
    set(maxRequestsPerHost){
        synchronized(this){
            field = maxRequestsPerHost
        }
    }

}