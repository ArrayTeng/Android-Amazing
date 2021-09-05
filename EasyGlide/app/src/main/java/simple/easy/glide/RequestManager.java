package simple.easy.glide;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import simple.easy.glide.binding.inter.Lifecycle;
import simple.easy.glide.binding.inter.LifecycleListener;
import simple.easy.glide.request.BitmapRequest;
import simple.easy.glide.work.DefaultConnectivity;
import simple.easy.glide.work.ImageViewTarget;
import simple.easy.glide.work.TargetTracker;

/**
 * Fragment的生命周期回调会回调RequestManager的生命周期然后进行相关资源的释放操作
 */
public class RequestManager implements LifecycleListener {

    private static volatile RequestManager requestManager;

    private LinkedBlockingQueue<BitmapRequest> requestQueue = new LinkedBlockingQueue<>();

    private BitmapDispatcher[] bitmapDispatchers;

    //先认为是 ActivityFragmentLifecycle 对象
    private Lifecycle lifecycle;
    private Context context;

    private final ImageViewTarget imageViewTarget = new ImageViewTarget();
    private final TargetTracker targetTracker = new TargetTracker();

    //监听网络变化
    DefaultConnectivity connectivity;

    //定义线程池管理线程
    ExecutorService executorService;

    private RequestManager(Lifecycle lifecycle, Context context){
        this.lifecycle = lifecycle;
        this.context = context;
        //Glide的with流程创建了一个空的Fragment（RequestManagerFragment）RequestManagerFragment的构造函数中
        //初始化了一个 ActivityFragmentLifecycle 所以这里的 lifecycle 指的就是此刻创建的 ActivityFragmentLifecycle
        //ActivityFragmentLifecycle 用于管理所有的 LifecycleListener
        this.lifecycle.addListener(this);//自己注册自己
        //定义一个 LifecycleListener 通过广播的方式来监听网络
        connectivity = new DefaultConnectivity(context);
        this.lifecycle.addListener(connectivity);

        //初始化线程池
        initThreadExecutor();
        //启动
        start();
    }



    private void initThreadExecutor() {
        //我也不知道为啥这么定义的线程数量就是最佳的，反正抄AsyncTask的就行
        int size = Runtime.getRuntime().availableProcessors();
        if (size <= 0) {
            size = 1;
        }
        size *= 2;
        executorService = Executors.newFixedThreadPool(size);
    }

    private void start() {
        //停止所有线程
        stop();
        //处理并开始所有线程
        startAllDispatcher();
    }

    private void stop() {
        if (bitmapDispatchers!=null && bitmapDispatchers.length>0){
            for (BitmapDispatcher dispatcher:bitmapDispatchers){
                if (!dispatcher.isInterrupted()){
                    dispatcher.interrupt();
                }
            }
        }
    }

    public BitmapRequest load(String url){
        return new BitmapRequest(context).loadUrl(url);
    }

    private void startAllDispatcher() {
        final int threadCount = Runtime.getRuntime().availableProcessors();
        bitmapDispatchers = new BitmapDispatcher[threadCount];
        if (bitmapDispatchers.length > 0){
            for (int i = 0;i<threadCount;i++){
                BitmapDispatcher bitmapDispatcher = new BitmapDispatcher(requestQueue);
                executorService.execute(bitmapDispatcher);
                bitmapDispatchers[i] = bitmapDispatcher;
            }
        }
    }

    public static RequestManager getInstance(Lifecycle lifecycle, Context context){
        if (requestManager == null){
            synchronized (RequestManager.class){
                if (requestManager == null){
                    requestManager = new RequestManager(lifecycle,context);
                }
            }
        }
        return requestManager;
    }

    @Override
    public void onStart() {
        imageViewTarget.onStart();
        targetTracker.onStart();
    }

    @Override
    public void onStop() {
        imageViewTarget.onStop();
        targetTracker.onStop();
    }

    @Override
    public void onDestroy() {
        this.lifecycle.removeListener(this);
        imageViewTarget.onDestroy();
        targetTracker.onDestroy();
        this.lifecycle.removeListener(connectivity);
    }

    /**
     * 将请求添加到队列
     */
    public void addBitmapRequest(BitmapRequest bitmapRequest) {
        if (bitmapRequest == null) return;

        if (!requestQueue.contains(bitmapRequest)){
            requestQueue.add(bitmapRequest);
        }
    }
}
