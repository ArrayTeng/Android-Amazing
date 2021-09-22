package com.tengfei.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rxjava.Emitter
import com.example.rxjava.Observable
import com.example.rxjava.ObservableOnSubscribe
import com.example.rxjava.Observer
import com.example.rxjava.map.Function

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.create(object :ObservableOnSubscribe<String>{
            override fun subscribe(emitter: Emitter<String>?) {
                emitter?.onNext("发送消息")
            }
        }).map(object:Function<String,String>{
            override fun apply(t: String?): String {

                return ""
            }
        }).subscribeObserver(object:Observer<String>{
            override fun onNext(t: String?) {
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