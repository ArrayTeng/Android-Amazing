package com.example.algorithm.day01;

/**
 * @author tengfei
 */
public class Array<E> {

    private E[] data;

    private int size;

    public Array() {
        this(10);
    }

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 获取数组中元素的个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容器的大小
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 返回数组是否为 empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 往数组中添加元素
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 往数组中第一个位置添加元素
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 往数组中某一个位置添加元素
     */
    public void add(int index, E e) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index 必须 >=0 或者 <=size");
        }

        if (size == getCapacity()) {
            resize(2*data.length);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;

    }

    private void resize(int newCapacity) {
        @SuppressWarnings("unchecked")
        E[] newData = (E[])new Object[newCapacity];
        for (int i=0;i<size;i++){
            newData[i] = data[i];
        }

        data = newData;

    }

    /**
     * 获取 index 索引位置的元素
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index 必须 >=0 或者 <=size");
        }
        return data[index];
    }

    /**
     * 修改 index 索引位置的元素为 e
     */
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index 必须 >=0 或者 <=size");
        }
        data[index] = e;
    }

    /**
     * 查询数组中是否含有某个元素 e
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (e == data[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查询数组中元素 e 所在的位置
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (e == data[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从数组中移除元素并返回移除的元素
     */
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index 必须 >=0 或者 <=size");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return ret;
    }

    /**
     * 删除数组中第一个元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除数组中最后一个元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    public E removeElement(E element) {
        int index = find(element);
        if (index != -1) {
            return remove(index);
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(";");
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
