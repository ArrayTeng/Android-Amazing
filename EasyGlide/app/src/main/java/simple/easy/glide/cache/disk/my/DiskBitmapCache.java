package simple.easy.glide.cache.disk.my;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import simple.easy.glide.cache.disk.DiskLruCache;
import simple.easy.glide.resource.Value;

public class DiskBitmapCache implements BitmapCache{

    //磁盘缓存最大不能超过 50M
    private final long MAXSIZE = 50 * 1024 * 1024;

    //私有目录缓存图片文件 - 单个文件最大缓存值
    private final String IMAGE_CACHE_PATH = "image";

    private DiskLruCache diskLruCache;

    //1、单例模式
    private static volatile DiskBitmapCache instance;

    public static DiskBitmapCache getInstance(Context context){
        if (instance == null){
            synchronized (DiskBitmapCache.class){
                if (instance == null){
                    instance = new DiskBitmapCache(context);
                }
            }
        }
        return instance;
    }

    //2、初始化DisLruCache
    private DiskBitmapCache(Context context){

        File file = getImageCacheFile(context,IMAGE_CACHE_PATH);
        if (!file.exists()){
            file.mkdirs();
        }
        try {
            diskLruCache = DiskLruCache.open(file,getAppVersion(context),1,MAXSIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //3、获取私有目录文件夹
    //获取图片文件
    private File getImageCacheFile(Context context, String imageCachePath) {
        String path;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            path = context.getExternalCacheDir().getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        return new File(path + File.separator + imageCachePath);
    }

    //获取版本号
    private int getAppVersion(Context context) {
        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void put(String key, Value value) {
        DiskLruCache.Editor editor =null;
        OutputStream outputStream = null;
        try {
            editor = diskLruCache.edit(key);
            outputStream = editor.newOutputStream(0);
            Bitmap bitmap = value.getBitmap();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                editor.commit();
                diskLruCache.close();
                if(outputStream!=null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Value get(String key) {
        Value value = new Value();
        InputStream inputStream = null;
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
            inputStream = snapshot.getInputStream(0);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            value.setBitmap(bitmap);
            value.setKey(key);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    @Override
    public void remove(String key) {

    }
}
