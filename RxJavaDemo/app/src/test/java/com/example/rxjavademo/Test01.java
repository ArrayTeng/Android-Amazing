package com.example.rxjavademo;

import androidx.annotation.NonNull;

import com.example.rxjavademo.rxjava.Observable;
import com.example.rxjavademo.rxjava.Observer;

/**
 * @author tengfei
 * date 2019-12-04 19:32
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Test01 {

    @org.junit.Test
    public void test() {
        //Observable 调用 just 方法返回 ObservableJust 对象
        Observable.just("hello").subscribe(new Observer() {
            @Override
            public void onSubscribe() {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(@NonNull Object item) {
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
