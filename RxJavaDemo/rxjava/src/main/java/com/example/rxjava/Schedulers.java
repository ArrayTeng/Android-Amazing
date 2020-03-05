package com.example.rxjava;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2020-03-04 23:18
 * email arrayadapter.cn@gmail.com
 * description
 */
public abstract class Schedulers {

    static Schedulers IO;

    static final Schedulers MAINTHREAD;

    static{
        IO = new IOSchedulers();

        MAINTHREAD = new MainSchedulers(new Handler(Looper.getMainLooper()));
    }


    @NonNull
    public static Schedulers io() {
        return IO;
    }

    public static Schedulers mainThread() {
        return MAINTHREAD;
    }

    public  abstract void scheduleDirct(Runnable runnable);

    private static class IOSchedulers extends Schedulers {

        ExecutorService service;

        public IOSchedulers(){
            service = Executors.newScheduledThreadPool(1, new ThreadFactory() {
                @Override
                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread(runnable);
                    thread.setDaemon(false);
                    return thread;
                }
            });
        }

        @Override
        public void scheduleDirct(Runnable runnable) {
            service.execute(runnable);
        }
    }

    private static class MainSchedulers extends Schedulers {
        Handler handler;
        public MainSchedulers(Handler handler) {
           this.handler = handler;
        }

        @Override
        public void scheduleDirct(Runnable runnable) {
            Message message = Message.obtain(handler,runnable);
            handler.sendMessage(message);
        }
    }
}
