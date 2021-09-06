package simple.easy.glide;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;

import simple.easy.glide.request.BitmapRequest;
import simple.easy.glide.request.RequestListener;

@Deprecated
public class BitmapDispatcher extends Thread{

    private Handler handler = new Handler(Looper.getMainLooper());

    private LinkedBlockingQueue<BitmapRequest> requestQueue;

    public BitmapDispatcher(LinkedBlockingQueue<BitmapRequest> requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        super.run();
        while (!isInterrupted()){
            if (requestQueue==null){
                continue;
            }

            try {
                BitmapRequest bitmapRequest = requestQueue.take();
                if (bitmapRequest == null){
                    continue;
                }
                //设置占位符
                showLoadingImage(bitmapRequest);
                //缓存处理

                //网络请求
                Bitmap bitmap = getImageBitmap(bitmapRequest.getUrl());
                //显示图片
                showImageView(bitmapRequest,bitmap);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void showImageView(BitmapRequest bitmapRequest, Bitmap bitmap) {
        final ImageView imageView = bitmapRequest.getImageView();
        if (bitmap != null && imageView != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                    RequestListener requestListener = bitmapRequest.getRequestListener();
                    if (requestListener != null) {
                        requestListener.onSuccess(bitmap);
                    }
                }
            });
        } else {
            RequestListener requestListener = bitmapRequest.getRequestListener();
            if (requestListener != null) {
                requestListener.onFail();
            }
        }
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bitmap=null;
        try {
            URL imageUrl = new URL(url);
            //要用到HttpURLConnection
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            //Bitmap工厂类，流转化成Bitmap
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private void showLoadingImage(BitmapRequest bitmapRequest) {
        ImageView imageView = bitmapRequest.getImageView();
        if (bitmapRequest.getResId() >0&&imageView!=null){
            int resId = bitmapRequest.getResId();
            handler.post(() -> imageView.setImageResource(resId));
        }
    }
}
