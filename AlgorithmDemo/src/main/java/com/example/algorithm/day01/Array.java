package com.example.algorithm.day01;

/**
 * @author tengfei
 */
public class Array {

    private int[] data;

    private int size;

    public Array(){
        this(10);
    }

    public Array(int capacity){
        data = new int[capacity];
        size = 0;
    }

    /**
     * 获取数组中元素的个数
     */
    public int getSize(){
        return size;
    }

    /**
     * 获取数组容器的大小
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 返回数组是否为 empty
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 往数组中添加元素
     */



    /**
     * 从数组中移除元素
     */


}
