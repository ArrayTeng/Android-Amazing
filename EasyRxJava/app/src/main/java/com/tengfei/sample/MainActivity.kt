package com.tengfei.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rxjava.Emitter
import com.example.rxjava.Observable
import com.example.rxjava.ObservableOnSubscribe
import com.example.rxjava.Observer
import com.example.rxjava.map.Function
import kotlin.math.log

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: Emitter<String>?) {
                Log.e("tmd","111 ${Thread.currentThread().name}")
                emitter?.onNext("发送消息")
            }
        }).map(object : Function<String, String> {
            override fun apply(t: String?): String {
                Log.e("tmd","222 ${Thread.currentThread().name}")
                return "Hai"
            }
        }).subscribeOn()//ObservableSubscribeOn
            .observerOn()
            .subscribeObserver(object : Observer<String> {
                override fun onNext(t: String?) {
                    Log.e("tmd","333 ${Thread.currentThread().name}")
                }

                override fun onSubscribe() {
                }

                override fun onError(e: Throwable?) {
                }

                override fun onComplete() {
                }
            })
    }
}