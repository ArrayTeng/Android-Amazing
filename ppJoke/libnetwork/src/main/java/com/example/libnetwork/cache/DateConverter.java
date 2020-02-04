package com.example.libnetwork.cache;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * @author tengfei
 * date 2020-02-04 12:30
 * email arrayadapter.cn@gmail.com
 * description
 */
public class DateConverter {

    @TypeConverter
    public static Long date2Long(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static Date long2Date(Long data) {
        return new Date(data);
    }
}
