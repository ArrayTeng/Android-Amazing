package com.example.easysqlite.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.example.easysqlite.sql.annotion.DbFiled;
import com.example.easysqlite.sql.annotion.DbTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 2021-7-20
 * 1、将数据库中表的列名和java bean中的成员变量映射到一个map里，完成插入数据的操作
 *
 * 2021-7-21
 *
 *
 * @param <T>
 */
public class BaseDao<T> implements IBaseDao<T> {

    private SQLiteDatabase sqLiteDatabase;

    private Class<T> entityClass;

    private boolean isInit = false;

    private Map<String, Field> fieldCacheMap;

    /**
     * 表名
     */
    private String tabName;

    protected BaseDao() {
        fieldCacheMap = new HashMap<>();
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

            Log.e("tmd", " SQL语句  " + sql);

            try {
                sqLiteDatabase.execSQL(sql);

                initCacheMap();
            } catch (Exception e) {
                isInit = false;
                e.printStackTrace();
            }

        }

        return isInit;
    }

    private void initCacheMap() {
        String sql = "select * from " + tabName + " limit 0 ";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        //获取所有的列名
        String[] columnNames = cursor.getColumnNames();
        //获取所有的Field
        Field[] columnFields = entityClass.getDeclaredFields();
        //将你的列名和Field映射到一起
        for (String columnName : columnNames) {

            Field resultField = null;
            for (Field columnField : columnFields) {
                if (columnName.equals(columnField.getAnnotation(DbFiled.class).value())) {
                    resultField = columnField;
                    break;
                }
            }
            if (resultField != null) {
                fieldCacheMap.put(columnName, resultField);
            }

        }
    }

    private String createTable() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table if not exists ");
        stringBuffer.append(tabName);
        stringBuffer.append(" (");
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            Class type = field.getType();
            if (type == String.class) {
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " TEXT,");
            } else if (type == Integer.class) {
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " INTEGER,");
            } else if (type == Double.class) {
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " DOUBLE,");
            } else if (type == Long.class) {
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " BIGINT,");
            } else if (type == byte[].class) {
                stringBuffer.append(field.getAnnotation(DbFiled.class).value() + " BLOB,");
            } else {
                //不支持的数据类型
                continue;
            }
        }

        if (stringBuffer.charAt(stringBuffer.length() - 1) == ',') {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }

        stringBuffer.append(")");

        return stringBuffer.toString();
    }

    private ContentValues getContentValues(Map<String, String> map) {
        ContentValues contentValues = new ContentValues();
        Set keys = map.keySet();
        Iterator<String> keyIterator = keys.iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            String value = map.get(key);
            if (value != null) {
                contentValues.put(key, value);
            }
        }
        return contentValues;
    }

    private Map<String, String> getValues(T entity) {
        Iterator<Field> fieldIterator = fieldCacheMap.values().iterator();
        Map<String, String> map = new HashMap<>();
        while (fieldIterator.hasNext()) {
            Field field = fieldIterator.next();
            field.setAccessible(true);
            try {
                Object object = field.get(entity);
                if (object == null) {
                    continue;
                }
                String value = object.toString();
                String key = field.getAnnotation(DbFiled.class).value();

                if (!TextUtils.isEmpty(value) && !TextUtils.isEmpty(key)) {
                    map.put(key, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    @Override
    public Long insert(T entity) {

        ContentValues contentValues = getContentValues(getValues(entity));

        return sqLiteDatabase.insert(tabName, null, contentValues);
    }

    /**
     *
     * @param entity 待插入的数据
     * @param where 要修改的对象，如果为null表示所有的都要修改
     * @return
     */
    @Override
    public int update(T entity, T where) {
        Map<String,String> values = getValues(entity);

        ContentValues contentValues = getContentValues(values);

        Map<String,String> whereClauseMap = getValues(where);

        Condition condition = new Condition(whereClauseMap);

        return  sqLiteDatabase.update(tabName,contentValues,condition.whereClause,condition.whereArgs);
    }

    static class Condition{

        String whereClause;
        String[] whereArgs;

        public Condition(Map<String,String> whereClauseMap){
            ArrayList<String> arrayList = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" 1=1");
            Set<String> keys = whereClauseMap.keySet();
            for (String key : keys) {
                String value = whereClauseMap.get(key);
                if (value != null) {
                    stringBuilder.append(" and " + key + " =?");
                    arrayList.add(value);
                }
            }
            this.whereClause = stringBuilder.toString();
            this.whereArgs = arrayList.toArray(new String[arrayList.size()]);
        }

    }

    @Override
    public int delete(T where) {
        Map<String,String> map = getValues(where);
        Condition condition = new Condition(map);
        return sqLiteDatabase.delete(tabName,condition.whereClause,condition.whereArgs);
    }

    @Override
    public List<T> query(T where) {
        return null;
    }
}
