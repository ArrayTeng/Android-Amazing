package com.example.libnetwork.cache;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * @author tengfei
 * date 2020-02-04 12:22
 * email arrayadapter.cn@gmail.com
 * description
 */

@Entity(tableName = "cache")
public class Cache implements Serializable {

    @PrimaryKey
    @NonNull
    public String key;

    public byte[] data;
}
