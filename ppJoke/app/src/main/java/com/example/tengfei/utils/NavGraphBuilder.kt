package com.example.tengfei.utils

import android.content.ComponentName
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphNavigator
import androidx.navigation.fragment.FragmentNavigator

/**
 * @author tengfei
 * date 2020-02-02 13:09
 * email arrayadapter.cn@gmail.com
 * description 构建页面导航结构图
 */

object NavGraphBuilder {

    fun buildNavGraph(navController: NavController) {
        val provider = navController.navigatorProvider
        val activityNavigator = provider.getNavigator(ActivityNavigator::class.java)
        val fragmentNavigator = provider.getNavigator(FragmentNavigator::class.java)
        val map = AppConfig.getDestConfig()

        val navGraph = NavGraph(NavGraphNavigator(provider))

        map!!.forEach {
            val value = it.value
            if (value.isActivity) {
                val activityDestination = activityNavigator.createDestination()
                activityDestination.id = value.id
                activityDestination.setComponentName(
                    ComponentName(
                        AppGlobal.getApp()!!.packageName,
                        value.clazzName
                    )
                )
                activityDestination.addDeepLink(value.pageUrl)
                navGraph.addDestination(activityDestination)
            } else {
                val fragmentDestination = fragmentNavigator.createDestination()
                fragmentDestination.id = value.id
                fragmentDestination.addDeepLink(value.pageUrl)
                fragmentDestination.className = value.clazzName
                navGraph.addDestination(fragmentDestination)
            }
            if (value.isStart) {
                navGraph.startDestination = value.id
            }
        }

        navController.graph = navGraph
    }
}