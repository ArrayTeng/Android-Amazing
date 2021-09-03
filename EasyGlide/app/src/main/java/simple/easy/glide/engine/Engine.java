package simple.easy.glide.engine;

import android.content.Context;
import android.widget.ImageView;

import simple.easy.glide.cache.ActiveCache;
import simple.easy.glide.cache.MemoryCache;
import simple.easy.glide.cache.disk.DiskLruCache;
import simple.easy.glide.cache.disk.my.DiskBitmapCache;
import simple.easy.glide.resource.Key;
import simple.easy.glide.resource.Value;
import simple.easy.glide.resource.ValueCallBack;
import simple.easy.glide.util.Tool;

/**
 * 1、读缓存（1级、2级、3级）
 * 2、分发给EngineJob
 */
public class Engine implements ValueCallBack ,ResponseListener{
    private Context glideContext;
    private String path;
    private String key;

    //活动缓存 - 只针对当前页面有效
    private ActiveCache activeCache;
    //内存缓存 - 在整个APP运行期间有效
    private MemoryCache memoryCache;
    //磁盘缓存
    private DiskBitmapCache diskLruCache;

    //内存缓存最大的缓存容量
    private final int MEMORY_MAX_SIZE = 1024 * 1024 * 120;

    private static volatile Engine instance;

    public static Engine getInstance() {
        if (instance == null) {
            synchronized (Engine.class) {
                if (instance == null) {
                    instance = new Engine();
                }
            }
        }
        return instance;
    }

    private Engine() {
        if (activeCache == null) {
            activeCache = new ActiveCache(this);
        }
        if (memoryCache == null) {
            memoryCache = new MemoryCache(MEMORY_MAX_SIZE);
        }
    }

    //初始化磁盘缓存
    public void load(String path, Context context) {
        this.path = path;
        this.glideContext = context;
        this.key = new Key(path).getKey();
        diskLruCache = DiskBitmapCache.getInstance(glideContext);
    }

    //读缓存
    public void into(ImageView imageView) {
        Tool.assertBackgroundThread();
        //本地有缓存直接渲染
        Value value = cacheAction();

        if (value != null) {
            imageView.setImageBitmap(value.getBitmap());
        }
    }

    private Value cacheAction() {
        //判断读取逻辑，活动缓存有就从活动缓存中然后是内存缓存，磁盘缓存
        Value value = activeCache.get(key);
        if (value!=null){
            memoryCache.put(key,value);
//            activeCache.recycleActive();
            return value;
        }
        value = memoryCache.get(key);
        if (value!=null){
            activeCache.put(key,value);
            memoryCache.remove(key);
            return value;
        }
        value = diskLruCache.get(key);
        if (value!=null){
            return value;
        }
        //从服务器中去找，通过EngineJob加载资源
        return null;
    }


    //写缓存


    @Override
    public void valueNonUseListener(String key, Value value) {

    }

    @Override
    public void responseSuccess(String key, Value value) {
        value.setKey(key);
        if (diskLruCache!=null){
            activeCache.put(key,value);
            diskLruCache.put(key,value);
        }
    }

    @Override
    public void responseException(Exception e) {

    }
}
