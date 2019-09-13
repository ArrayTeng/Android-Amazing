package com.example.specialeffects.eleSearch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.specialeffects.R
import kotlinx.android.synthetic.main.activity_ele_search.*

/**
 * @author tengfei
 * date 2019-09-13 09:52
 * email tengfeigo@outlook.com
 * description
 */
class EleSearchActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ele_search)


        tvSearchContent.setOnClickListener {
            val locationArray = IntArray(2)
            tvSearchContent.getLocationOnScreen(locationArray)
            val intent = Intent(this, EleSearchResultActivity::class.java)
            intent.putExtra("X", locationArray[0])
            intent.putExtra("Y", locationArray[1])

            startActivity(intent)
            overridePendingTransition(0,0)
        }


    }
}