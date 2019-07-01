package com.example.tengfei.mvvm

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author tengfei
 * date 2019/6/9 12:09 PM
 * email tengfeigo@outlook.com
 * description
 */
class User : BaseObservable() {

    @set:Bindable
    var name: String = ""
        set(name) {
            field = name
            notifyPropertyChanged(BR.name)
        }

    var header: String = ""

    companion object {
        @BindingAdapter("bind:header")
        @JvmStatic
        fun loadImage(imageView: ImageView, imgUrl: String) {
            Glide.with(imageView.context).load(imgUrl).into(imageView)
        }
    }
}