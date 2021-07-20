package com.example.easysqlite.sql;


import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class BaseDaoFactory {

    private SQLiteDatabase database;

    private String sqliteDataBasePath;

    private BaseDaoFactory(){
        sqliteDataBasePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/easySqlite.db";
        this.database = SQLiteDatabase.openOrCreateDatabase(sqliteDataBasePath,null);

    }

    public static BaseDaoFactory getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder{
        private static final BaseDaoFactory INSTANCE = new BaseDaoFactory();
    }

    public synchronized <R extends BaseDao<T>,T> R createBaseDao(Class<R> clazz,Class<T> entityClass){
        BaseDao baseDao = null;
        try {
            baseDao = clazz.newInstance();
            baseDao.init(entityClass,database);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return (R) baseDao;
    }
}
