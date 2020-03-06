package com.example.rxjavademo;

import com.example.rxjavademo.study.Function;
import com.example.rxjavademo.study.Observable;
import com.example.rxjavademo.study.Observer;
import com.example.rxjavademo.study.Schedulers;

import org.junit.Test;

import androidx.annotation.NonNull;

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Observable.just("hello")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String item) {
                        System.out.println(Thread.currentThread().getName());
                        return 123;
                    }
                }).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer item) {
                System.out.println(Thread.currentThread().getName());
                return 46;
            }
        }).subscribeOn(Schedulers.IO)
                .observerOn(Schedulers.MAINTHREAD).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe() {
                System.out.println(Thread.currentThread().getName());
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("  onNext " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}