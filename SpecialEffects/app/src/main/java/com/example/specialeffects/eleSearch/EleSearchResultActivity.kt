package com.example.specialeffects.eleSearch

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.specialeffects.R
import kotlinx.android.synthetic.main.activity_ele_search_result.*

/**
 * @author tengfei
 * date 2019-09-13 10:20
 * email tengfeigo@outlook.com
 * description
 */
class EleSearchResultActivity : AppCompatActivity() {

    private var locationX: Int = 0
    private var locationY: Int = 0

    private var lastEditSearchX = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ele_search_result)

        val x = intent.getIntExtra("X", 0)
        val y = intent.getIntExtra("Y", 0)
        locationX = x
        locationY = y

        tvSearchResult.post {
            //View 加载完成后执行动画
            performEnterAnimation()
        }


    }

    private fun performEnterAnimation() {
        //共享元素的组件位置应该是一样的
        val currentLocationArray = IntArray(2)
        //获取当前元素组件的位置信息
        tvSearchResult.getLocationOnScreen(currentLocationArray)
        //根据当前元素组件的位置信息可以获取到它的 x y 坐标
        val currentX = currentLocationArray[0]
        val currentY = currentLocationArray[1]

        val translateX = locationX - currentX
        val translateY = locationY - currentY
        //重新给 tvSearchResult 布局
        tvSearchResult.x = tvSearchResult.x + translateX.toFloat()
        tvSearchResult.y = tvSearchResult.y + translateY.toFloat()

        //重新给 editSearch 布局
        editSearch.y = tvSearchResult.y + (tvSearchResult.height - editSearch.height) / 2
        //给搜索按钮重新布局
        tvSearch.y = tvSearchResult.y + (tvSearchResult.height - tvSearch.height) / 2

        val top = resources.displayMetrics.density * 20

        //搜索框缩放动画
        val scaleTvSearchResultAnimation: ValueAnimator = scaleTvSearchResult(1.0f, 0.8f)
        //搜索框往上平移动动画
        val translateTvSearchResultAnimation: ValueAnimator =
            translateTvSearchResult(tvSearchResult.y, top)
        //文本框向左平移
        lastEditSearchX = editSearch.x
        //当前文本框应该在的位置
        val currentEditSearchX = ivArrow.right * 2.toFloat()
        val translateEditSearch = translateEditSearch(lastEditSearchX, currentEditSearchX)
        val alphaAnimation = alphaAnimation(0F, 1F)

        scaleTvSearchResultAnimation.start()
        translateTvSearchResultAnimation.start()
        translateEditSearch.start()
        alphaAnimation.start()
    }

    /**
     * 退出动画
     */
    private fun performExitAnimation() {
        //共享元素的组件位置应该是一样的
        val currentLocationArray = IntArray(2)
        //获取当前元素组件的位置信息
        tvSearchResult.getLocationOnScreen(currentLocationArray)
        //根据当前元素组件的位置信息可以获取到它的 x y 坐标
        val currentX = currentLocationArray[0]
        val currentY = currentLocationArray[1]
        //获取偏移量
        val translateX = locationX - currentX
        val translateY = locationY - currentY

        //搜索框上下平移动画
        val translateTvSearchAnimation =
            translateTvSearchResult(tvSearchResult.y, tvSearchResult.y + translateY)

        //搜索框缩放动画
        val scaleTvSearchResultAnimation: ValueAnimator = scaleTvSearchResult(0.8f, 1f)

        val alphaAnimation = alphaAnimation(1F, 0F)

        val translateEditSearch = translateEditSearch(editSearch.x, lastEditSearchX)

        translateTvSearchAnimation.duration = 350
        scaleTvSearchResultAnimation.duration = 350
        alphaAnimation.duration = 350
        translateEditSearch.duration = 350
        translateTvSearchAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                finish()
                overridePendingTransition(0, 0)
            }
        })


        translateEditSearch.start()
        alphaAnimation.start()
        translateTvSearchAnimation.start()
        scaleTvSearchResultAnimation.start()

    }


    override fun onBackPressed() {
        performExitAnimation()
    }

    private fun scaleTvSearchResult(from: Float, to: Float): ValueAnimator {
        val valueAnimator: ValueAnimator = ValueAnimator.ofFloat(from, to)
        valueAnimator.addUpdateListener {
            tvSearchResult.scaleX = it.animatedValue as Float
        }
        return valueAnimator
    }

    private fun translateTvSearchResult(from: Float, to: Float): ValueAnimator {
        val valueAnimator: ValueAnimator = ValueAnimator.ofFloat(from, to)
        valueAnimator.addUpdateListener {
            tvSearchResult.y = it.animatedValue as Float
            //在搜索框向上移动的时候文本输入框也要移动
            editSearch.y = tvSearchResult.y + (tvSearchResult.height - editSearch.height) / 2

            ivArrow.y = tvSearchResult.y + (tvSearchResult.height - ivArrow.height) / 2

            tvSearch.y = tvSearchResult.y + (tvSearchResult.height - tvSearch.height) / 2
        }
        return valueAnimator
    }

    private fun translateEditSearch(from: Float, to: Float): ValueAnimator {
        val valueAnimator: ValueAnimator = ValueAnimator.ofFloat(from, to)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            editSearch.x = value
        }
        return valueAnimator
    }

    private fun alphaAnimation(from: Float, to: Float): ValueAnimator {
        val animator = ValueAnimator.ofFloat(from, to)
        animator.addUpdateListener {
            val value = it.animatedValue
            ivArrow.alpha = value as Float
            tvSearch.alpha = value
            searchResultContent.alpha = value

        }
        return animator
    }
}


