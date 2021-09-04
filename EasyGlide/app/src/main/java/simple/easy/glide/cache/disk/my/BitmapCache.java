package simple.easy.glide.cache.disk.my;

import simple.easy.glide.resource.Value;

public interface BitmapCache {

    void put(String key, Value value);

    Value get(String key);

    void remove(String key);

}
