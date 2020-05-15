package com.amazing.tengfei.kotlindemo.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.amazing.tengfei.kotlindemo.R
import kotlinx.android.synthetic.main.activity_navigation.*

/**
 * @author 飞一般的感觉
 * date 2020/5/14 3:13 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
class NavigationActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        navController = Navigation.findNavController(this, R.id.navigationFragment)

        val navGraph = navController.navInflater.inflate(R.navigation.mobile_navigaion)
        navController.graph = navGraph

        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            val options = NavOptions.Builder()
                    .setPopUpTo(navController.currentDestination!!.id, true)
                    .setLaunchSingleTop(true)
                    .build()

            try {
                navController.navigate(it.itemId,null,options)
                true
            }catch (e:Exception){
                false
            }

        }

    }


}