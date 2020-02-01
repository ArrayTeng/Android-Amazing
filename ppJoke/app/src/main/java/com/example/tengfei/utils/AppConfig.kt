@file:Suppress("UNCHECKED_CAST")

package com.example.tengfei.utils

import com.alibaba.fastjson.JSON
import com.example.tengfei.model.Destination
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @author tengfei
 * date 2020-02-01 22:26
 * email arrayadapter.cn@gmail.com
 * description
 */
object AppConfig {
    private var destinationMap: HashMap<String, Destination>? = null

    fun getDestConfig(): HashMap<String, Destination>? {
        if (destinationMap == null) {
            val content = parseFile("destination.json")
            destinationMap = JSON.parse(content) as HashMap<String, Destination>
        }
        return destinationMap
    }


    fun parseFile(fileName: String): String {
        val inputStream = AppGlobal.getApp()!!.assets.open(fileName)
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        var line: String? = null

        val stringBuilder = StringBuilder()

        while ({ line = bufferedReader.readLine();line }() != null) {
            stringBuilder.append(line)
        }
        return stringBuilder.toString()
    }
}