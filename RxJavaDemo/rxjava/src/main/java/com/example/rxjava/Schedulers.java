package com.example.rxjava;

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

    static{
        IO = new IOSchedulers();
    }


    @NonNull
    public static Schedulers io() {
        return IO;
    }

    public  void scheduleDirct(Runnable runnable) {

    }

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
}
