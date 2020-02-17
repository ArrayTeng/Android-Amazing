package com.example.tengfei.linkedlist;

/**
 * @author tengfei
 */
public class SinglyLinkedList<T> {

    private Node<T> dummyHeader;

    private int size;

    public SinglyLinkedList() {
        dummyHeader = new Node<>();
        size = 0;
    }

    /**
     * 往链表的指定位置添加一个结点
     */
    public void addIndex(T data, int index) {
        if (index < 0 || index > size) throw new IllegalArgumentException("index 不能小于 0 或者大于 size");

        Node prev = dummyHeader;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node<T> node = new Node<>();
        node.data = data;
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    /**
     * 往链表的头结点添加数据（非虚拟头结点）
     */
    public void addFirst(T data) {
        addIndex(data, 0);
    }

    /**
     * 往链表尾添加数据
     */
    public void addLast(T data) {
        addIndex(data, size);
    }

    /**
     * 删除第index位置的结点
     */
    public void deleteIndex(int index) {
        if (index < 0 || index == size) {
            throw new IllegalArgumentException("index<0||index==size");
        }

        Node prev = dummyHeader;

        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node<T> node = prev.next;
        prev.next = node.next;
        node.next = null;
        size--;
    }

    /**
     * 删除头结点
     */
    public void deleteFirst() {
        deleteIndex(0);
    }

    /**
     * 删除尾结点
     */
    public void deleteLast() {
        deleteIndex(size - 1);
    }

    /**
     * 修改index位置的结点
     */
    public void updateIndex(T date, int index) {
        deleteIndex(index);
        addIndex(date, index);
    }

    /**
     * 获取链表第index位置的结点的数据
     */
    public T getIndex(int index) {
//        Node prev = dummyHeader;
//        for (int i = 0; i < index; i++) {
//            prev = prev.next;
//        }
        @SuppressWarnings("unchecked")
        Node<T> node = getNodeByIndex(index);
        return node.data;
    }

    /**
     * 获取第index位置的结点
     */
    public Node<T> getNodeByIndex(int index) {
        if (index >= size) {
            return null;
        }
        Node prev = dummyHeader;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        return prev.next;
    }

    /**
     * 获取链表数据的长度
     */
    public int size() {
        return size;
    }

    /**
     * 单链表反转
     * https://www.cnblogs.com/mwl523/p/10749144.html
     */
    public void reverse() {
        Node head = getNodeByIndex(0);
        dummyHeader.next = head;
        Node prev = dummyHeader.next;
        Node curr = prev.next;
        while (curr != null) {
            prev.next = curr.next;
            curr.next = dummyHeader.next;
            dummyHeader.next = curr;
            curr = prev.next;
        }
    }

    /**
     * 链表中环的检测
     */
    public boolean checkCircle() {
        Node node = getNodeByIndex(0);
        if (node == null) return false;
        Node slow = node;
        Node fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) return true;
        }

        return false;
    }

    /**
     * 合并两个有序链表
     */
    public static Node<Integer> mergeTwoLists(Node<Integer> l1, Node<Integer> l2) {
        Node dummyNode = new Node<>();

        Node p = dummyNode;

        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        p.next = l1 == null ? l2 : l1;
        return dummyNode.next;
    }

    /**
     * 遍历链表并打印出来
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        Node<T> prev = dummyHeader;
        for (int i = 0; i < size; i++) {
            prev = prev.next;
            T data = prev.data;
            builder.append(data);
            if (i != size - 1) {
                builder.append(",");
            }
        }
        builder.append("}");
        return builder.toString();
    }

    private static class Node<T> {

        public Node next;

        public T data;
    }


    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();

        singlyLinkedList.addFirst(1);
        singlyLinkedList.addFirst(2);
        singlyLinkedList.addFirst(3);
        singlyLinkedList.addFirst(4);
        singlyLinkedList.addFirst(5);
        singlyLinkedList.addFirst(6);
        // singlyLinkedList.updateIndex(90,0);
        //System.out.println(singlyLinkedList.getIndex(1));
        singlyLinkedList.reverse();
        System.out.println(singlyLinkedList.toString());

        System.out.println(singlyLinkedList.getIndex(0));
    }
}
