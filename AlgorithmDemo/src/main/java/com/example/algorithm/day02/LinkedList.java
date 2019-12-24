package com.example.algorithm.day02;

/**
 * @author tengfei
 */
public class LinkedList<E> {

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
}
