package com.example.tengfei.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.example.tengfei.client.IBindEntity;
import com.example.tengfei.client.IListener;
import com.example.tengfei.client.NumberEntity;

import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @author tengfei
 */
public class MyService extends Service {

    private static final String TAG = "MyServiceTag";

    /**
     * 在跨进程传输中，binder会把服务端传输过来的对象重新反序列化为一个新的对象，所以实际上在客户端和服务端操作的对象实际上是两个不同对象，所以在涉及到
     * 跨进程传输使用观察者模式将数据反馈给客户端的时候就需要用到 RemoteCallbackList ，因为两个不用进程实际操作的是两个不同对象，所以在使用常规的方法比如说
     * List 来维护 Listener 对象，就会遇到这么一个问题，无论你在服务端进程还是客户端进程，你删除的 Listener 对象仅仅只是当前进程的 Listener 对象，原因在上面已经说清楚了
     * 这时候就需要用到 RemoteCallbackList ，RemoteCallbackList 是系统提供的专门用于删除跨进程的 Listener 对象的接口，尽管跨进程传输在客户端和服务端会生成不同的对象
     * 但是它们的底层binder是同一个，当需要从 RemoteCallbackList 中移除 Listener 时只需要遍历所有的 Listener 并将具有相同 binder 对象的 Listener 给移除掉，这就是
     * RemoteCallbackList 帮助开发者所做的事情
     */
    private RemoteCallbackList<IListener> remoteCallbackList = new RemoteCallbackList<>();
    private AtomicBoolean mServiceIsWork = new AtomicBoolean(false);

    private IBinder iBinder = new IBindEntity.Stub() {
        @Override
        public void registerListener(IListener listener) throws RemoteException {
            Log.i(TAG, "server binder current thread # " + Thread.currentThread().getName());
            remoteCallbackList.register(listener);
        }

        @Override
        public void unRegisterListener(IListener listener) throws RemoteException {
            remoteCallbackList.unregister(listener);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "server current thread # " + Thread.currentThread().getName());
        new Thread(new ServiceWorker()).start();
    }

    private class ServiceWorker implements Runnable {
        int j = 0;

        @Override
        public void run() {
            while (!mServiceIsWork.get()) {
//                Log.i(TAG,"ServiceWorker");
                j++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final int n = remoteCallbackList.beginBroadcast();
                for (int i = 0; i < n; i++) {
                    NumberEntity numberEntity = new NumberEntity(j);
                    IListener iListener = remoteCallbackList.getBroadcastItem(i);
                    try {
                        iListener.onNewInFoArrived(numberEntity);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                remoteCallbackList.finishBroadcast();
            }
        }
    }

    @Override
    public void onDestroy() {
        mServiceIsWork.set(true);
        super.onDestroy();
    }
}
