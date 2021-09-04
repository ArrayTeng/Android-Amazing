package simple.easy.glide.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import simple.easy.glide.resource.Value;
import simple.easy.glide.resource.ValueCallBack;
import simple.easy.glide.util.Tool;

/**
 * 活动缓存,本质上就是一个HashMap
 */
public class ActiveCache {
    private Map<String, Value> mapList = new HashMap<>();

    private ValueCallBack valueCallBack;

    public ActiveCache(ValueCallBack valueCallBack) {
        this.valueCallBack = valueCallBack;
    }

    /**
     * 添加活动缓存
     */
    public void put(String key,Value value){
        Tool.checkNotEmpty(key);
        value.setValueCallBack(valueCallBack);
        mapList.put(key,value);
    }

    public Value get(String key){
        Value value = mapList.get(key);
        if (null!=value){
            return value;
        }
        return null;
    }

    public void recycleActive(){
        Iterator<Map.Entry<String,Value>> it = mapList.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,Value> entry = it.next();
            it.remove();
        }
    }

}
