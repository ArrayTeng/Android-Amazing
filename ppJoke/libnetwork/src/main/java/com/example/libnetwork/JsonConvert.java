package com.example.libnetwork;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import java.lang.reflect.Type;

/**
 * @author tengfei
 * date 2020-02-03 19:11
 * email arrayadapter.cn@gmail.com
 * description
 */
public class JsonConvert implements Convert {
    //默认的Json转 Java Bean的转换器
    @Override
    public Object convert(String response, Type type) {
        JSONObject jsonObject = JSON.parseObject(response);
        JSONObject data = jsonObject.getJSONObject("data");
        if (data != null) {
            Object data1 = data.get("data");
            return JSON.parseObject(data1.toString(), type);
        }
        return null;
    }
}
