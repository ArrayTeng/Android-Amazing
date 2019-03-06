package com.example.okhttp

import java.io.Closeable

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
    }

}