package com.example.traffic

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.traffic.commom.BaseActivity
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_break_news.*


/**
 * @author tengfei
 * date 2019-09-25 16:52
 * email tengfeigo@outlook.com
 * description
 */

class BreakNewsActivity : BaseActivity() {

    companion object {
        var tagList = arrayListOf("拥堵", "服务质量", "事故", "路况险情", "其他")

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_break_news)
        setupToolbar()

        tagFlowLayout.adapter = object : TagAdapter<String>(tagList) {
            override fun getView(parent: FlowLayout?, position: Int, info: String?): View {
                val itemFlow: TextView = layoutInflater.inflate(R.layout.item_flow, parent, false) as TextView
                itemFlow.text = info

                return itemFlow
            }

        }
    }
}