package simple.easy.glide.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;

import simple.easy.glide.request.RequestOptions;
import simple.easy.glide.resource.Key;
import simple.easy.glide.resource.Value;
import simple.easy.glide.util.MainTreadExecute;
import simple.easy.glide.util.ThreadPoolManager;
import simple.easy.glide.work.ImageViewTarget;

public class EngineJob implements Runnable {

    private String path;
    private ResponseListener responseListener;
    private Context context;
    private ImageViewTarget imageViewTarget;
    private RequestOptions requestOptions;

    public Value loadResource(String path, ResponseListener responseListener, Context context, ImageViewTarget imageViewTarget,
                              RequestOptions requestOptions){
        this.path = path;
        this.responseListener = responseListener;
        this.context = context;
        this.imageViewTarget = imageViewTarget;
        this.requestOptions = requestOptions;

        //校验是一个图片的链接
        Uri uri = Uri.parse(path);
        if ("HTTP".equalsIgnoreCase(uri.getScheme())||"HTTPS".equalsIgnoreCase(uri.getScheme())){
            ThreadPoolManager.getInstance().execute(this);
        }else {

        }
        return null;
    }

    @Override
    public void run() {
        //这里做接口请求并且由子线程直接转为主线程
        Bitmap bitmap = getImageBitmap(path);
        Executor executor = new MainTreadExecute();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Value value = new Value();
                value.setBitmap(bitmap);
                String key = new Key(path).getKey();
                responseListener.responseSuccess(key,value);

                //渲染UI
                imageViewTarget.setResource(bitmap);
            }
        });
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bitmap=null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try {
            URL imageUrl = new URL(url);
            //要用到HttpURLConnection
            conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            inputStream = conn.getInputStream();
            //Bitmap工厂类，流转化成Bitmap
            Matrix matrix = new Matrix();
            bitmap = BitmapFactory.decodeStream(inputStream);
            bitmap = Bitmap.createBitmap(bitmap,0,0,requestOptions.getWidth(),requestOptions.getHeight(),
                    matrix,true);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn!=null){
                conn.disconnect();
            }

        }
        return bitmap;
    }

}
