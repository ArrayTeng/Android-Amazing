package com.example.factory.databse;

/**
 * @author tengfei
 */
public abstract class AbstractDataCacheOperator {

    public abstract void put(String key, String value);

    public abstract String get(String key);
}
