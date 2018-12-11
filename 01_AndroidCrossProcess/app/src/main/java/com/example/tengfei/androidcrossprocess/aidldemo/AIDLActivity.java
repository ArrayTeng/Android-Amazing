package com.example.tengfei.androidcrossprocess.aidldemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tengfei.androidcrossprocess.Book;
import com.example.tengfei.androidcrossprocess.IBookManager;
import com.example.tengfei.androidcrossprocess.IOnNewBookArrivedListener;
import com.example.tengfei.androidcrossprocess.R;

import java.util.List;

/**
 * @author tengfei
 */
public class AIDLActivity extends AppCompatActivity {

    private static final String TAG = "AIDLActivity";

    private IBookManager mRemoteBookManager;

    /**
     * binder死亡重连机制
     */
    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (mRemoteBookManager == null) {
                return;
            }
            mRemoteBookManager.asBinder().unlinkToDeath(deathRecipient, 0);
            mRemoteBookManager = null;
            Intent intent = new Intent(AIDLActivity.this, BookManagerService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    };

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0x001:
                    Log.d(TAG, "receive new book : " + msg.obj);
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager iBookManager = IBookManager.Stub.asInterface(service);
            try {
                service.linkToDeath(deathRecipient,0);
                mRemoteBookManager = iBookManager;
                List<Book> bookList = iBookManager.getBookList();
                bookList.add(new Book(0x003, "海底两万里"));
                Log.i(TAG, "从服务端获得数据是 ： " + bookList.toString());
                iBookManager.registerListener(onNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * 运行在binder线程池中所以有必要通过 handler 将线程切换到 UI 线程中处理
     */
    private IOnNewBookArrivedListener onNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrivedListener(Book book) {
            mHandler.obtainMessage(0x001, book).sendToTarget();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        Intent intent = new Intent(this, BookManagerService.class);
        /*
         * 绑定服务，回调service中的onBind方法，返回Service中的bind实例，在onServiceConnected方法中可以获取到服务端的binder实例，通过这个实例并将它
         * 转换成客户端所需要的AIDL接口类型的实例，调用IBookManager的registerListener注册监听回调，并将其添加到CopyOnWriteArrayList集合中，典型的观察者
         * 模式，在服务端中回调此方法并将消息传递给所有注册此回调的客户端
         */
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                mRemoteBookManager.unregisterListener(onNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(connection);
        super.onDestroy();
    }
}
