package com.example.algorithm.day03;

/**
 * @author tengfei
 */
public class ArrayStack {

    private String[] data;
    /**
     * 栈中元素的个数
     */
    private int count;
    /**
     * 给数组申请的空间
     */
    private int n;

    public ArrayStack(int n) {
        data = new String[n];
        this.n = n;
    }

    public boolean push(String element){
        if (count == n) return false;

        this.data[count] = element;
        count++;
        return true;
    }

    public String pop(){
        if (count == 0) return null;

        count--;
        return this.data[count];
    }
}
