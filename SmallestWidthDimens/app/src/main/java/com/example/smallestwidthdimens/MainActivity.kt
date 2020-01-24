package com.example.smallestwidthdimens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val TAG = "MainActivity_test"

    private val defaultEditDesignDP: String = "375"

    var dimen = ""

    var density: Float = 0.0f

    private var dpi: Int = 0
    private var heightPixels: Int = 0
    private var widthPixels: Int = 0
    private var defaultEditAdapterDP: Float = 0.0F

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val displayMetrics: DisplayMetrics = resources.displayMetrics
        density = displayMetrics.density
        dpi = displayMetrics.densityDpi
        heightPixels = displayMetrics.heightPixels
        widthPixels = displayMetrics.widthPixels
        defaultEditAdapterDP = widthPixels / density


        tvDensity.text = "density   :   $density"
        tvDpi.text = "当前屏幕像素密度 dpi:   $dpi"
        tvHeightPixels.text = "当前屏幕高（px为单位）:   $heightPixels"
        tvWidthPixels.text = "当前屏幕宽（px为单位）:   $widthPixels"
        tvMinDP.text = "当前屏幕最小宽度（以 DP 为单位）:  $defaultEditAdapterDP"


    }


    fun byPhone(view: View) {
        dimenText.text = ""
        val editDesignDP = if (TextUtils.isEmpty(editDesignDP.text.toString())) {
            defaultEditDesignDP
        } else {
            editDesignDP.text.toString()
        }
        val editAdapterDP = if (TextUtils.isEmpty(editAdapterDP.text.toString())) {
            defaultEditAdapterDP.toString()
        } else {
            editAdapterDP.text.toString()
        }

        val dimenDp: Float = editAdapterDP.toFloat() / editDesignDP.toFloat()

        val stringBuilder = StringBuilder()
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n")
        stringBuilder.append("<resources>\n")
        for (i in 1..editDesignDP.toInt()) {
            stringBuilder.append("<dimen name=\"dp_$i\">${i * dimenDp}dp</dimen>\n")
        }
        stringBuilder.append("</resources>\n")

        dimen = stringBuilder.toString()
        dimenText.text = dimen
        writeTxtFile(
            dimen,
            Environment.getExternalStorageDirectory().absolutePath,
            "dimen.xml",
            true
        )
    }
}
