package simple.easy.glide.cache;

import android.os.Build;
import android.util.LruCache;

import simple.easy.glide.resource.Value;

/**
 * 内存缓存
 */
public class MemoryCache extends LruCache<String, Value> {
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public MemoryCache(int maxSize) {
        super(maxSize);
    }


    /**
     * bitmap的大小
     * @param key
     * @param value
     * @return
     */
    @Override
    protected int sizeOf(String key, Value value) {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >=Build.VERSION_CODES.KITKAT){
            return value.getBitmap().getAllocationByteCount();
        }
        return value.getBitmap().getByteCount();
    }
}
