package com.example.algorithm.day01;

/**
 * @author tengfei
 */
public class Day01Test {

    public static void main(String[] args) {
        Array<Integer> array = new Array<>();

        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        array.addLast(5);
        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        array.addLast(5);
        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        array.addLast(5);
        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        array.addLast(5);

        array.add(1, 10);

        array.addFirst(0);

        System.out.println(array.toString());

        System.out.println(array.get(1));

        array.set(0, 4);

        System.out.println(array.get(0));

        System.out.println(array.contains(10));

        System.out.println(array.find(5));

        System.out.println(array.remove(0));
        System.out.println(array.toString());

    }
}
