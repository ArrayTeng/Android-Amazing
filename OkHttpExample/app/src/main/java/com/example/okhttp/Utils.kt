package com.example.okhttp

import java.io.Closeable
import android.text.TextUtils
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and


/**
 * @author tengfei
 * date 2019/3/6 10:38 PM
 * email tengfeigo@outlook.com
 * description
 */
class Utils {
    companion object {
        fun close(closeable: Closeable) {
            closeable.close()
        }

        fun md5Url(url: String): String {
            if (TextUtils.isEmpty(url)) {
                return url
            }
            val sb = StringBuffer()
            try {
                val messageDigest = MessageDigest.getInstance("md5")
                messageDigest.update(url.toByteArray())
                val cipher = messageDigest.digest()
                for (b in cipher) {
                    // 转成了 16 机制
                    val hexStr = Integer.toHexString((b and 0xff.toByte()).toInt())
                    // 不足还补 0
                    sb.append(if (hexStr.length == 1) "0$hexStr" else hexStr)
                }
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }

            return sb.toString()
        }
    }


}