package com.example.traffic

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.traffic.commom.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottom_bar.*

/**
 * @author tengfei
 * date 2019-09-26 20:58
 * email tengfeigo@outlook.com
 * description
 */
class MainActivity : BaseActivity() {

    companion object {
        val fragmentPageList: List<Fragment> = arrayListOf(HomeFragment(), MineFragment())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPagerContent.adapter = BottomPagerAdapter(fragmentPageList = fragmentPageList,
                fragmentManager = supportFragmentManager)

        bottomBarLayout.setViewPager(viewPagerContent)


    }
}


class BottomPagerAdapter(fragmentManager: FragmentManager, private val fragmentPageList: List<Fragment>) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment = fragmentPageList[position]

    override fun getCount(): Int = fragmentPageList.size

}