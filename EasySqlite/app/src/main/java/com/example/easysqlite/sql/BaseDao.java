package com.example.easysqlite.sql;

import android.database.sqlite.SQLiteDatabase;

import com.example.easysqlite.sql.annotion.DbFiled;
import com.example.easysqlite.sql.annotion.DbTable;

import java.lang.reflect.Field;
import java.util.List;

public class BaseDao<T> implements IBaseDao<T> {

    private SQLiteDatabase sqLiteDatabase;

    private Class<T> entityClass;

    private boolean isInit = false;

    /**
     * 表名
     */
    private String tabName;

    protected BaseDao() {

    }

    protected synchronized boolean init(Class<T> entityClass, SQLiteDatabase database) {
        if (!isInit) {
            this.entityClass = entityClass;
            this.sqLiteDatabase = database;

            //第一步 自动建表
            if (entityClass.getAnnotation(DbTable.class) == null) {
                tabName = entityClass.getClass().getSimpleName();
            } else {
                tabName = entityClass.getAnnotation(DbTable.class).value();
            }

            if (!database.isOpen()) {
                return false;
            }

            isInit = true;

            String sql = createTable();

            try {
                sqLiteDatabase.execSQL(sql);
            } catch (Exception e) {
                isInit = false;
                e.printStackTrace();
            }

        }

        return isInit;
    }

    private String createTable() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table if not exists");
        stringBuffer.append(tabName);
        stringBuffer.append(" (");
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            Class type = field.getType();
            if (type == String.class){
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " TEXT,");
            }else if (type == Integer.class){
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " INTEGER,");
            }else if(type == Double.class){
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " DOUBLE,");
            }else if(type == Long.class){
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " BIGINT,");
            }else if(type == byte[].class){
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " BLOB,");
            }else {
                //不支持的数据类型
                continue;
            }
        }

        if (stringBuffer.charAt(stringBuffer.length()-1) == ','){
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }

        stringBuffer.append(" ) ");

        return stringBuffer.toString();
    }

    @Override
    public Long insert(T entity) {
        return null;
    }

    @Override
    public int update(T entity, T where) {
        return 0;
    }

    @Override
    public int delete(T where) {
        return 0;
    }

    @Override
    public List<T> query(T where) {
        return null;
    }
}
