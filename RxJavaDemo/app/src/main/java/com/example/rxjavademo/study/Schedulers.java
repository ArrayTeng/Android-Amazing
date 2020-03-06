package com.example.rxjavademo.study;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2020-03-06 14:36
 * email arrayadapter.cn@gmail.com
 * description
 */
public abstract class Schedulers {

    public static final Schedulers IO;

    public static final Schedulers MAINTHREAD;

    static {
        IO = new IOSchedulers();

        MAINTHREAD = new MainSchedulers(new Handler(Looper.getMainLooper()));
    }


    public abstract void scheduleDirect(Runnable runnable);


    private static class IOSchedulers extends Schedulers {

        private ExecutorService executorService;

        public IOSchedulers() {
            executorService = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {

                private final AtomicInteger mCount = new AtomicInteger(1);

                @Override
                public Thread newThread(@NonNull Runnable runnable) {

                    return new Thread(runnable, "IOSchedulers" + mCount.getAndIncrement());
                }
            });
        }

        @Override
        public void scheduleDirect(Runnable runnable) {
            executorService.execute(runnable);
        }
    }


    private static final class MainSchedulers extends Schedulers {

        private Handler handler;

        public MainSchedulers(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void scheduleDirect(Runnable runnable) {
            Message message = Message.obtain(handler, runnable);
            handler.sendMessage(message);
        }
    }

}
