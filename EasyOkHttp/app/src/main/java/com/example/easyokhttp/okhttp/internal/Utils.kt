package com.example.easyokhttp.okhttp.internal

import okhttp3.OkHttpClient
import java.util.concurrent.ThreadFactory


@JvmField
internal val okHttpName =
    OkHttpClient::class.java.name.removePrefix("okhttp3.").removeSuffix("Client")

fun threadFactory(name: String, daemon: Boolean): ThreadFactory = ThreadFactory { runnable ->
    Thread(runnable,name).apply {
        isDaemon = daemon
    }
}