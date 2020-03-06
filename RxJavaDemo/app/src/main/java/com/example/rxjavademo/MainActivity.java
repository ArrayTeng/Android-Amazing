package com.example.rxjavademo;

import android.os.Bundle;
import android.util.Log;

import com.example.rxjavademo.study.Function;
import com.example.rxjavademo.study.Observable;
import com.example.rxjavademo.study.Observer;
import com.example.rxjavademo.study.Schedulers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.just("hello")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String item) {
                        Log.e(TAG, "apply: " + Thread.currentThread().getName());
                        return 123;
                    }
                }).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer item) {
                Log.e(TAG, "apply: " + Thread.currentThread().getName());
                return 46;
            }
        }).subscribeOn(Schedulers.IO)
                .observerOn(Schedulers.MAINTHREAD)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe() {
                        Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.e(TAG, "onNext: " + Thread.currentThread().getName() + "  ------  " + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });
    }

}


