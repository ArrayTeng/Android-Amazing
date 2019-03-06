package com.example.okhttp

import java.io.File
import java.io.IOException

interface DownloadCallBack {
    fun onFailure(e: IOException)

    fun onSuccess(file:File)
}
