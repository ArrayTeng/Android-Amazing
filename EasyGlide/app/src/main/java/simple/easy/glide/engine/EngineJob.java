package simple.easy.glide.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;

import simple.easy.glide.resource.Key;
import simple.easy.glide.resource.Value;
import simple.easy.glide.util.MainTreadExecute;
import simple.easy.glide.util.ThreadPoolManager;

public class EngineJob implements Runnable {

    private String path;
    private ResponseListener responseListener;
    private Context context;
    private ImageView imageView;

    public Value loadResource(String path, ResponseListener responseListener, Context context, ImageView imageView){
        this.path = path;
        this.responseListener = responseListener;
        this.context = context;
        this.imageView = imageView;

        //校验是一个图片的链接
        Uri uri = Uri.parse(path);
        if ("HTTP".equalsIgnoreCase(uri.getScheme())||"HTTPS".equalsIgnoreCase(uri.getScheme())){
            ThreadPoolManager.getInstance().execute(this);
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
                imageView.setImageBitmap(bitmap);
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
            bitmap = BitmapFactory.decodeStream(inputStream);
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
