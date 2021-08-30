package simple.easy.glide.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

import simple.easy.glide.RequestManager;
import simple.easy.glide.binding.ApplicationLifecycle;

public class BitmapRequest {

    //请求路径
    private String url;

    //上下文
    private Context mContext;

    //需要加载图片的控件
    private SoftReference<ImageView> imageView;

    //占位图片
    private int resId;

    //回调对象
    private RequestListener requestListener;

    //图片的标识
    private String urlMd5;

    public BitmapRequest(Context context) {
        this.mContext = context;
    }

    //加载URL
    public BitmapRequest loadUrl(String url){
        this.url = url;
        return this;
    }

    //设置占位图片
    public BitmapRequest loading(int resId){
        this.resId = resId;
        return this;
    }

    //设置监听器
    public BitmapRequest setRequestListener(RequestListener requestListener){
        this.requestListener = requestListener;
        return this;
    }

    //显示图片控件
    public void into(ImageView imageView){
        imageView.setTag(urlMd5);
        this.imageView = new SoftReference<>(imageView);
        RequestManager.getInstance(new ApplicationLifecycle(),mContext).addBitmapRequest(this);
    }

    public String getUrl() {
        return url;
    }

    public Context getContext() {
        return mContext;
    }

    public ImageView getImageView() {
        return imageView.get();
    }

    public int getResId() {
        return resId;
    }

    public RequestListener getRequestListener() {
        return requestListener;
    }

    public String getUrlMd5() {
        return urlMd5;
    }

}
