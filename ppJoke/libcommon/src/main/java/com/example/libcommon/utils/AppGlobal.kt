package com.example.libcommon.utils

import android.annotation.SuppressLint
import android.app.Application

/**
 * @author tengfei
 * date 2020-02-01 22:15
 * email arrayadapter.cn@gmail.com
 * description
 */


class AppGlobal {
    companion object {
        private var sApplication: Application? = null

        @SuppressLint("PrivateApi", "DiscouragedPrivateApi")
        fun getApp(): Application? {

            if (sApplication == null) {
                val clazz: Class<*> = Class.forName("android.app.ActivityThread")
                val method = clazz.getDeclaredMethod("currentApplication")
                sApplication = method.invoke(null) as Application?
            }

            return sApplication


        }

    }
}

