package com.example.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.io.IOException
import android.text.TextUtils
import com.example.demo.okhttp.ExRequestBody
import java.net.URLConnection


private fun guessMimeType(filePath: String): String {
    val fileNameMap = URLConnection.getFileNameMap()

    val mimType = fileNameMap.getContentTypeFor(filePath)

    return if (TextUtils.isEmpty(mimType)) {
        "application/octet-stream"
    } else mimType
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val okHttpClient = OkHttpClient.Builder()
            .build()


        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("","")
            .addFormDataPart("file","tengfei.apk", RequestBody.create(MediaType.parse(guessMimeType("")),""))
            .build()

        val exRequestBody:ExRequestBody = ExRequestBody(requestBody)

        val request: Request = Request.Builder()
            .post(exRequestBody)
            .url("")
            .build()

        val call = okHttpClient.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call, response: Response) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }
}
