package com.example.factory.databse;


/**
 * @author tengfei
 */
public interface IDataCacheFactory {

    AbstractDataCacheOperator createDataCache(Class<? extends AbstractDataCacheOperator> dataCacheClass);

}
