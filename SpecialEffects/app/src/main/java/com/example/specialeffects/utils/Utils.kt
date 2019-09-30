package com.example.specialeffects.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * @author tengfei
 * date 2019-09-30 17:00
 * email tengfeigo@outlook.com
 * description
 */


fun px2dp(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        Resources.getSystem().displayMetrics
    )
}