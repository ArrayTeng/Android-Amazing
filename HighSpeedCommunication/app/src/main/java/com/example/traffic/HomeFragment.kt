package com.example.traffic

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author tengfei
 * date 2019-09-26 21:02
 * email tengfeigo@outlook.com
 * description
 */
class HomeFragment:Fragment(),View.OnClickListener {


    override fun onClick(v: View?)  = when(v?.id){
        R.id.breakNews -> startActivity(Intent(activity,BreakNewsActivity::class.java))
        R.id.knockOut -> startActivity(Intent(activity,KnockOutActivity::class.java))
        else -> {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breakNews.setOnClickListener(this)
        knockOut.setOnClickListener(this)
    }
}