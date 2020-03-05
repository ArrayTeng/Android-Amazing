package com.example.rxjava;

import org.junit.Test;

import javax.xml.transform.Source;

import androidx.annotation.NonNull;


public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Observable.just("Hello World")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        System.out.println(Thread.currentThread().getName());
                        return "hai";
                    }
                })
                .subscribeOn(Schedulers.io())
                .observerOn(Schedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe() {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        System.out.println(s);
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