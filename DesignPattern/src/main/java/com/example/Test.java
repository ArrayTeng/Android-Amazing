package com.example;

/**
 * @author tengfei
 */
public class Test {

    public static int count = 0;

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(test::run, "thread 01").start();

        new Thread(test::add, "thread 02").start();
    }


    public void run() {
        synchronized (Test.class) {
            for (int i =0;i<10;i++){
                System.out.println(Thread.currentThread().getName() + " --- " + count++);

            }
        }
    }

    public void add(){
        for (int i =0;i<10;i++){
            System.out.println("lalalalla");
        }


    }
}
