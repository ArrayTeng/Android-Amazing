package com.amazing.tengfei.kotlindemo.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.amazing.tengfei.kotlindemo.R
import kotlinx.android.synthetic.main.fragment_page3.*

/**
 * @author 飞一般的感觉
 * date 2020/5/14 3:14 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
class Page3Fragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_page3,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btPage3ToPage1.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_fragment_page03_to_fragment_page01)
        }


    }

    
}