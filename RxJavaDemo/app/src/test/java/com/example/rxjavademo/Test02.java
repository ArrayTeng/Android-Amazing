package com.example.rxjavademo;

import com.example.rxjavademo.rxjava.Consumer;
import com.example.rxjavademo.rxjava.Observable;

import org.junit.Test;


/**
 * @author tengfei
 * date 2019-12-05 21:21
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Test02 {

    @Test
    public void test(){
        Observable.just("").subscribe(new Consumer() {
            @Override
            public void onNext(Object item) {

            }
        });
    }
}
