package com.example.factory.databse.factory;

import com.example.factory.databse.AbstractDataCacheOperator;
import com.example.factory.databse.IDataCacheFactory;
import com.example.factory.databse.operator.DiskLruCacheOperator;
import com.example.factory.databse.operator.LruCacheOperator;

/**
 * @author tengfei
 */
public class DataCacheHandlerFactory implements IDataCacheFactory {

    private AbstractDataCacheOperator diskCacheOperator, lruCacheOperator;

    private DataCacheHandlerFactory() {
    }

    public static DataCacheHandlerFactory newInstance() {
        return Holder.DATA_CACHE_HANDLER_FACTORY;
    }

    private static final class Holder {
        private static final DataCacheHandlerFactory DATA_CACHE_HANDLER_FACTORY = new DataCacheHandlerFactory();
    }

    @Override
    public AbstractDataCacheOperator createDataCache(Class<? extends AbstractDataCacheOperator> dataCacheClass) {
        try {
            return dataCacheClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DiskLruCacheOperator();
    }

    final AbstractDataCacheOperator createDiskOperator() {
        if (diskCacheOperator == null) {
            diskCacheOperator = createDataCache(DiskLruCacheOperator.class);
        }
        return diskCacheOperator;
    }

    final AbstractDataCacheOperator createLruCacheOperator() {
        if (lruCacheOperator == null) {
            lruCacheOperator = createDataCache(LruCacheOperator.class);
        }
        return lruCacheOperator;
    }
}
