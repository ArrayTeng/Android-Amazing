package com.example.okhttp

import okhttp3.Response
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.RandomAccessFile

/**
 * @author tengfei
 * date 2019/3/5 11:17 PM
 * email tengfeigo@outlook.com
 * description
 */
class DownloadRunnable(url: String, start: Long, end: Long, downloadCallBack: DownloadCallBack?) : Runnable {

    private var url: String? = null
    private var start: Long? = null
    private var end: Long? = null
    private var downloadCallBack: DownloadCallBack? = null

    init {
        this.url = url
        this.start = start
        this.end = end
        this.downloadCallBack = downloadCallBack
    }

    override fun run() {
        val file: File = FileManager.instance.createFile()
        var inputStream: InputStream? = null
        var randomAccess: RandomAccessFile? = null
        try {
            val response: Response = OkHttpManager.instance.syncRequest(url!!, start!!, end!!)
            inputStream = response.body()!!.byteStream()
            randomAccess = RandomAccessFile(file, "rwd")
            val buffer = ByteArray(1024 * 10)
            var len = 0
            while ((inputStream.read(buffer)) != -1) {
                len = inputStream.read(buffer)
                randomAccess.write(buffer, 0, len)
            }
            downloadCallBack!!.onSuccess(file)
        } catch (e: IOException) {
            downloadCallBack!!.onFailure(e)
        } finally {
            Utils.close(inputStream!!)
            Utils.close(randomAccess!!)
        }


    }
}