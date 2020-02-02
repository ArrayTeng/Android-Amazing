@file:Suppress("UNCHECKED_CAST")

package com.example.tengfei.utils

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import com.example.tengfei.model.BottomBar
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

    private var sBottomBar: BottomBar? = null

    fun getDestConfig(): HashMap<String, Destination>? {
        if (destinationMap == null) {
            val content = parseFile("destination.json")


            destinationMap = JSON.parseObject(content,
                object : TypeReference<HashMap<String, Destination>?>() {})
        }
        return destinationMap
    }

    fun getBottomBar():BottomBar?{
        if (sBottomBar == null){
            val content = parseFile("main_tabs_config.json")
            sBottomBar = JSON.parseObject(content,BottomBar::class.java)
        }
        return sBottomBar
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