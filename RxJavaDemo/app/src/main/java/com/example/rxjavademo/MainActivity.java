package com.example.rxjavademo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //.just("") 创建一个观察者对象， 上一个观察者调用 subscribeOn 并返回新的观察者对象指定观察者的线程

        //Observable.just("123") 返回一个 ObservableJust ，最终调用 ObservableJust 的map函数
        // .map 最终返回的也是一个 Observable 并且持有上一个 observable 对象的引用
        // .map 实际上就是创建了一个 new ObservableMap<T, R>(this, mapper)
        // ObservableMap 持有了 Function 对象以及上一个 observable 对象，当前上一个 observable 对象是 observablejust

        Observable.just("123").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {

                return Integer.parseInt(s);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
