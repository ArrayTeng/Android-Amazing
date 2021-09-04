package simple.easy.glide;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;

/**
 * 1、Glide.with的作用：创建空Fragment管理Glide生命周期、创建RequestManager对象
 * RequestManger类似于一个请求管理的类
 *
 * 本次分享的重点是Glide生命周期的管理以及三级缓存处理（活动缓存、内存缓存、磁盘缓存）
 *
 */
public class Glide {

    private static volatile Glide glide;

    private RequestManagerRetriever requestManagerRetriever;

    public Glide() {
        requestManagerRetriever = new RequestManagerRetriever();
    }

    /**
     * Glide.with 函数负责创建一个空的 fragment 对象用来管理 Glide 的生命周期
     * getRetriever(activity) 返回 RequestManagerRetriever
     *
     */
    public static RequestManager with(Activity activity) {
        return getRetriever(activity).get(activity);
    }

    /**
     * 适配Androidx
     * RequestManagerRetriever 可以看做是 RequestManager 的管理类
     * getRetriever(activity).get(activity); get函数内部创建了一个空的fragment
     */
    public static RequestManager with(FragmentActivity activity){
        return  getRetriever(activity).get(activity);
    }

    /**
     * Glide.get(context) 返回一个Glide的对象
     * getRequestManagerRetriever() 返回 RequestManagerRetriever 对象，
     * RequestManagerRetriever 负责用来管理 RequestManager
     */
    public static RequestManagerRetriever getRetriever(Context context) {

        return Glide.get(context).getRequestManagerRetriever();
    }

    public static Glide get(Context context) {
        if (glide == null) {
            synchronized (Glide.class) {
                if (glide == null) {
                    glide = new Glide();
                }
            }
        }
        return glide;
    }

    private RequestManagerRetriever getRequestManagerRetriever() {
        return requestManagerRetriever;
    }


}
