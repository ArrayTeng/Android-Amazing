package com.example.factory.databse.operator;

import com.example.factory.databse.AbstractDataCacheOperator;

/**
 * @author tengfei
 * 将具体的实现放在子类中去操作
 */
public class LruCacheOperator extends AbstractDataCacheOperator {
    @Override
    public void put(String key, String value) {

    }

    @Override
    public String get(String key) {
        return null;
    }
}
