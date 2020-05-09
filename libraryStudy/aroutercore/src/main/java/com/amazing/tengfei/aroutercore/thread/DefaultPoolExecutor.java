package com.amazing.tengfei.aroutercore.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.NonNull;

/**
 * @author 飞一般的感觉
 * date 2020/5/9 10:43 AM
 * email arrayadapter.cn@gmail.com
 * description
 */
public class DefaultPoolExecutor {

    //核心线程和最大线程都是cpu核心数+1
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int MAX_CORE_POOL_SIZE = CPU_COUNT + 1;
    //存活30秒 回收线程
    private static final long SURPLUS_THREAD_LIFE = 30L;

    private static ThreadPoolExecutor sBackupExecutor;

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {

        private AtomicInteger atomicInteger = new AtomicInteger(1);

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "ARouterThread" + atomicInteger.getAndIncrement());
        }
    };

    public static ThreadPoolExecutor newDefaultPoolExecutor(int corePoolSize) {

        if (sBackupExecutor == null) {
            sBackupExecutor = new ThreadPoolExecutor(corePoolSize, MAX_CORE_POOL_SIZE, SURPLUS_THREAD_LIFE,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(64), THREAD_FACTORY);
        }
        sBackupExecutor.allowCoreThreadTimeOut(true);
        return sBackupExecutor;
    }
}
