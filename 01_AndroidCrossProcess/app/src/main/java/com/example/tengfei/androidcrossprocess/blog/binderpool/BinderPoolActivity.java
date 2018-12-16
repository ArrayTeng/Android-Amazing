package com.example.tengfei.androidcrossprocess.blog.binderpool;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.tengfei.androidcrossprocess.ISecurityCenter;
import com.example.tengfei.androidcrossprocess.R;

/**
 * @author tengfei
 * <p>
 * 如果在你的项目中有多个模块需要用到 AIDL 进行进程间通信，按照常规的做法是创建 AIDl 、创建远程 Service 绑定服务然后调用相关方法，原则上是可以完成我们的需求
 * ，但是按这种做法带来的后果就是创建了大量的远程 Service 来进行进程间通信，Service 是四大组件之一，是一种系统资源，建了大量 Service 本身就占据来大量的资源让我们的
 * 应用看起来很笨重，而且在 Android 手机设置模块的更多应用里是可以看到正在运行的 Service 的，试想一下如果你创建了 20 个 Service 并且处于运行状态，那么用户看到了又是
 * 什么感受呢？ 针对这种情况我们需要将所有的 AIDL 放到一个Service中去管理，也就是说服务端会提供一个方法，根据当前传入的标记来返回对应的Binder对象，我们需要
 * 创建一个 BinderPool（Binder 连接池）来将所有的Binder请求统一转发到一个远程Service中去执行，避免创建大量的 Service。
 * <p>
 * 创建 IBinderPool.aidl 文件，在内部定义 queryBinder 方法，用于创建服务端的Binder对象，在客户端中调用 queryBinder 方法，通过传入的 code 字符串来判断
 * 返回的是哪一个binder对象，所以 IBinderPool.aidl 文件用于协调并且管理客户端所需的Binder对象的，拿到服务端的binder对象后也就可以根据这个binder对象来将其
 * 转换为客户端各个模块所要的 AIDL 接口类型的对象并调用相关方法来实现进程间通信，协调管理的相关方法统一封装到 BinderPool 中，在
 */
public class BinderPoolActivity extends AppCompatActivity {

    private static final String TAG = "BinderPoolActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_binder_pool);
        findViewById(R.id.bt_blog_binder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        doWork();
                    }
                }).start();
            }
        });
    }

    private void doWork() {
        //获取 BinderPool 的实例对象
        BinderPool binderPool = BinderPool.getInstance(BinderPoolActivity.this);
        //通过 Code 获取对应的服务端 Binder 对象
        IBinder securitybinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        //将服务端的binder对象转换为客户端所需要的 AIDL 接口类型的对象
        ISecurityCenter securityCenter = SecurityCenterImpl.asInterface(securitybinder);
        try {
            securityCenter.decrypt("hello my android");
            Log.i(TAG, securityCenter.encrypt("hello"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
