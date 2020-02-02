package com.example.tengfei.model

import android.os.Parcelable
import com.example.tengfei.poko.PoKo
import kotlinx.android.parcel.Parcelize

/**
 * @author tengfei
 * date 2020-02-01 22:38
 * email arrayadapter.cn@gmail.com
 * description
 */

@PoKo
@Parcelize
data class Destination (
    var isLogin: Boolean,
    var pageUrl: String,
    var isActivity: Boolean,
    var isStart: Boolean,
    var id: Int,
    var clazzName: String
): Parcelable