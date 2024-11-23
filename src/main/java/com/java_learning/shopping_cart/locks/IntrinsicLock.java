package com.java_learning.shopping_cart.locks;

class SyncDemo {

    void method1 () {
        for ( int i = 0; i < 10000; i++ ) {
            method3();
        }
    }

    void method2 () {
        for ( int i = 0; i < 10000; i++ ) {
            method3();
        }
    }

    void method3 () {
        synchronized ( SyncDemo.class ) {
            for ( int i = 0; i < 3; i++ ) {
                System.out.println("From " + Thread.currentThread().getName());
            }
        }
    }
}

public class IntrinsicLock {
    public static void main ( String[] args ) {
        SyncDemo obj = new SyncDemo();

        new Thread(obj::method1).start();
        new Thread(obj::method2).start();
    }
}
