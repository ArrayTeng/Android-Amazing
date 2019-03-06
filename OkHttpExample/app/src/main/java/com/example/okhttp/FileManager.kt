package com.example.okhttp

import android.annotation.SuppressLint
import android.content.Context
import java.io.File

/**
 * @author tengfei
 * date 2019/3/6 10:14 PM
 * email tengfeigo@outlook.com
 * description File管理类
 */
class FileManager private constructor() {

    var context: Context? = null
    private var mRootDir: File? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        val instance = Holder.fileManager
    }

    private object Holder {
        @SuppressLint("StaticFieldLeak")
        val fileManager = FileManager()
    }

    fun initFileManager(context: Context) {
        this.context = context.applicationContext
    }

    fun rootDir(file: File) {
        if (!file.exists()) {
            file.mkdirs()
        }

        if (file.exists() && file.isDirectory) {
            mRootDir = file
        }
    }

    fun createFile(): File {
        val fileName = ""
        if (mRootDir == null) {
            mRootDir = context!!.cacheDir
        }

        return File(mRootDir, fileName)
    }
}