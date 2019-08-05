package com.example.androidutils.file

import android.util.Log
import java.io.*

/**
 * @author tengfei
 * date 2019/7/2 11:07 PM
 * email tengfeigo@outlook.com
 * description
 */


private const val TAG = "FileUtilsLogcat"

/**
 *
 * @description 将数据写入到指定的文本文件中
 * @param content 保存的文本数据
 * @param filePath 文件路径，如：/sdcard/terminalDeviceID/
 * @param fileName 文件名，如：data.txt,文本文件的格式必须是 text 的
 *
 */
fun writeData(content: String, filePath: String, fileName: String) {
    writeTxtToFile(content, filePath, fileName)
}

/**
 *
 * @description 将数据写入到指定的文本文件中
 * @param content 保存的文本数据
 * @param filePath 文件路径，如：/sdcard/terminalDeviceID/
 * @param fileName 文件名，如：data.txt,文本文件的格式必须是 text 的
 *
 */
private fun writeTxtToFile(content: String, filePath: String, fileName: String) {
    //生成文件夹之后，再生成文件，不然会出错
    makeFilePath(filePath, fileName)
    val strFilePath = filePath + fileName
    // 每次写入时，都换行写
    val strContent = content + "\r\n"
    try {
        val file = File(strFilePath)
        if (!file.exists()) {
            Log.d(TAG, "Create the file:$strFilePath")
            file.parentFile.mkdirs()
            file.createNewFile()
        }
        val raf = RandomAccessFile(file, "rwd")
        raf.seek(file.length())
        raf.write(strContent.toByteArray())
        raf.close()
    } catch (e: Exception) {
        Log.e(TAG, "Error on write File:$e")
    }

}

/**
 * @description 生成文件
 * @param filePath 文件路径，如：/sdcard/terminalDeviceID/
 * @param fileName 文件名，如：data.txt,这里文件名的格式不做要求
 */
private fun makeFilePath(filePath: String, fileName: String): File? {
    var file: File? = null
    makeRootDirectory(filePath)
    try {
        file = File(filePath + fileName)
        if (!file.exists()) {
            file.createNewFile()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return file
}

/**
 * 生成文件夹
 *  @param filePath 文件路径，如：/sdcard/terminalDeviceID/
 */
private fun makeRootDirectory(filePath: String) {
    val file: File?
    try {
        file = File(filePath)
        if (!file.exists()) {
            file.mkdir()
        }
    } catch (e: Exception) {
        Log.i(TAG, e.toString())
    }

}

/**
 * 读取指定目录下的所有TXT文件的文件内容
 */
private fun getFileContent(file: File): String {
    var content = ""
    //检查此路径名的文件是否是一个目录(文件夹)
    if (!file.isDirectory) {
        //文件格式为 txt 文件
        if (file.name.endsWith("txt")) {
            try {
                val fileInputStream = FileInputStream(file)
                val inputStreamReader = InputStreamReader(fileInputStream, "UTF-8")
                val bufferedReader = BufferedReader(inputStreamReader)
                val line: String? = bufferedReader.readLine()
                //分行读取
                while (line != null) {
                    content += line + "\n"
                }
                //关闭输入流
                fileInputStream.close()
            } catch (e: java.io.FileNotFoundException) {
                Log.d(TAG, "The File doesn't not exist.")
            } catch (e: IOException) {
                Log.d(TAG, e.message)
            }

        }
    }
    return content
}