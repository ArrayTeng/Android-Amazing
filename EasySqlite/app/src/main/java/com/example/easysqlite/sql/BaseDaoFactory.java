package com.example.easysqlite.sql;


import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.easysqlite.enums.PrivateDataBaseEnums;
import com.example.easysqlite.sql.annotion.DbTable;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseDaoFactory {

    //总数据库
    private SQLiteDatabase allUserDatabase;

    //个人数据库
    private SQLiteDatabase personalDatabase;

    // key 表名 value BaseDao
    protected Map<String, BaseDao> map = Collections.synchronizedMap(new HashMap<>());

    private BaseDaoFactory() {
        //总表
        File userFile = new File(Environment.getExternalStorageDirectory(), "EasySqlite/User");

        if (!userFile.exists()) {
            userFile.mkdirs();
        }
        String allUserFilePath = userFile.getAbsolutePath() + "/easySqliteAllUser.db";
        //新建总数据库
        this.allUserDatabase = SQLiteDatabase.openOrCreateDatabase(allUserFilePath, null);


    }

    public static BaseDaoFactory getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final BaseDaoFactory INSTANCE = new BaseDaoFactory();
    }

    //个人数据库dao
    public synchronized <R extends BaseDao<T>, T> R personalDao(Class<R> daoClazz, Class<T> entityClass) {
        BaseDao baseDao = null;

        personalDatabase = SQLiteDatabase.openOrCreateDatabase(PrivateDataBaseEnums.dataBase.getValue(), null);


        try {
            baseDao = daoClazz.newInstance();
            baseDao.init(entityClass, personalDatabase);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return (R) baseDao;
    }


    //总数据库dao
    public synchronized <R extends BaseDao<T>, T> R allUserDao(Class<R> daoClazz, Class<T> entityClass) {
        BaseDao baseDao = null;
        String tabName = entityClass.getAnnotation(DbTable.class).value();
        if (map.get(tabName) != null) {
            return (R) map.get(tabName);
        }
        try {
            baseDao = daoClazz.newInstance();
            baseDao.init(entityClass, allUserDatabase);
            map.put(tabName, baseDao);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return (R) baseDao;
    }


}
