package com.example.demo.okhttp

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import okio.BufferedSink
import okio.ForwardingSink
import okio.Okio

/**
 * @author tengfei
 * date 2019-12-13 10:29
 * email arrayadapter.cn@gmail.com
 * description
 */
class ExRequestBody constructor(private val requestBody: RequestBody) : RequestBody() {

    var currentLength: Long = 0

    override fun contentLength(): Long {
        return requestBody.contentLength()
    }

    override fun contentType(): MediaType? {
        return requestBody.contentType()
    }

    override fun writeTo(sink: BufferedSink) {
        val contentLength = contentLength()
        val forwardingSink = object : ForwardingSink(sink) {
            override fun write(source: Buffer, byteCount: Long) {
                currentLength += byteCount

                super.write(source, byteCount)
            }
        }
        val bufferSink: BufferedSink = Okio.buffer(sink)
        requestBody.writeTo(bufferSink)
        bufferSink.flush()

    }
}