package com.example.rxjava;

import org.junit.Test;

import androidx.annotation.NonNull;


public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Observable.just("").subscribe(new Observer<String>() {


            @Override
            public void onSubscribe() {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}