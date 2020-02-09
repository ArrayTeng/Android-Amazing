package com.example.libnetwork.cache;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.libcommon.utils.AppGlobal;

/**
 * @author tengfei
 * date 2020-02-04 12:21
 * email arrayadapter.cn@gmail.com
 * description
 */

@Database(entities = {Cache.class},version = 1)
public abstract class CacheDatabase extends RoomDatabase {

    private static final CacheDatabase CACHE_DATABASE;
    static {
        //noinspection ConstantConditions
        CACHE_DATABASE = Room.databaseBuilder(AppGlobal.getApplication(),CacheDatabase.class,"ppjoke_cache")
                .allowMainThreadQueries()
                .build();

    }

    public abstract CacheDao getCache();

    public static CacheDatabase get() {
        return CACHE_DATABASE;
    }

}
