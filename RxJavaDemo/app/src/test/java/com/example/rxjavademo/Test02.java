package com.example.rxjavademo;

import android.graphics.Bitmap;

import com.example.rxjavademo.rxjava.Consumer;
import com.example.rxjavademo.rxjava.Function;
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
        Observable.just("https://www.baidu.com").map(new Function<String,Bitmap>() {
            @Override
            public Bitmap apply(String s) {
                return null;
            }

        });
    }



    @Test
    public void testRxJava(){

    }
}
