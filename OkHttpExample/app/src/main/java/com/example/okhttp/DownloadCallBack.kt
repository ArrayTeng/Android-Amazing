package com.example.okhttp

import java.io.IOException

interface DownloadCallBack {
    fun onFailure(e: IOException)

    fun onSuccess()
}
