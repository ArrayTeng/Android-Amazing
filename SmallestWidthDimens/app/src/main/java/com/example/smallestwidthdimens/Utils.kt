package com.example.smallestwidthdimens

import java.io.File
import java.io.FileWriter
import java.io.IOException

/**
 * @author tengfei
 * date 2020-01-24 23:25
 * email arrayadapter.cn@gmail.com
 * description
 */



@Throws(IOException::class)
fun createTxtFile(filePath: String, fileName: String): Boolean {
    var flag = false
    val filename = File("$filePath/$fileName")
    if (!filename.exists()) {
        filename.createNewFile()
        flag = true
    }
    return flag
}


fun writeTxtFile(content: String, filePath: String, fileName: String, append: Boolean): Boolean {
    var flag: Boolean = true
    val thisFile = File("$filePath/$fileName")
    try {
        if (thisFile.exists()){
            thisFile.delete()
        }
        if (!thisFile.parentFile.exists()) {
            thisFile.parentFile.mkdirs()
        }
        val fw = FileWriter("$filePath/$fileName", append)
        fw.write(content)
        fw.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return flag
}