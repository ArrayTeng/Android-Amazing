package com.example.tengfei.model

import com.example.tengfei.poko.PoKo

/**
 * @author tengfei
 * date 2020-02-02 18:50
 * email arrayadapter.cn@gmail.com
 * description
 */

@PoKo
data class BottomBar(
    var activeColor: String,
    var inActiveColor: String,
    var selectTab: Int,
    var tabs: List<TabBean>
)

@PoKo
data class TabBean(
    var size: Int,
    var enable: Boolean,
    var index: Int,
    var tintColor: String,
    var pageUrl: String,
    var title: String
)