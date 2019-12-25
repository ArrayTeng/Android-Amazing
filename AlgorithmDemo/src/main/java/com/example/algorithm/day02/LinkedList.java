package com.example.algorithm.day02;

/**
 * @author tengfei
 */
public class LinkedList<E> {

    private Node dummyHeader;

    private int size;

    private class Node {
        public E data;
        public Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(E data) {
            this(data, null);
        }

        public Node() {
            this(null, null);
        }
    }

    public LinkedList() {
        this.dummyHeader = new Node(null, null);
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }


    public void add(E element, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index must index<0||index>size");
        }
        if (index == 0) {
            addFirst(element);
        } else {
            Node prev = dummyHeader;
            for (int i = 0; i < index; i++) {
                prev = dummyHeader.next;
            }
            Node node = new Node(element);
            node.next = prev.next;
            prev.next = node;
            size++;
        }
    }

    public void addFirst(E element) {
        add(element, 0);
    }

    public void addLast(E element) {
        add(element, size);
    }

}
