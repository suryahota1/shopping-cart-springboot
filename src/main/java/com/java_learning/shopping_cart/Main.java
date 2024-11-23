package com.java_learning.shopping_cart;

public class Main {

    public static void main ( String[] args ) {
        ReadWriteLockExample example = new ReadWriteLockExample();
        Runnable writeTask = () -> {
            for ( int i = 0; i < 10; i++ ) {
                example.increment();
            }
        };

        Runnable readTask = () -> {
            for ( int i = 0; i < 10; i++ ) {
                example.print();
            }
        };

        Thread t1 = new Thread(writeTask, "WriteThread");
        Thread t2 = new Thread(readTask, "ReadThread 1");
        Thread t3 = new Thread(readTask, "ReadThread 2");

        t2.start();
        t3.start();
        t1.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch ( InterruptedException e ) {

        }
    }
}
