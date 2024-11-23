package com.java_learning.shopping_cart;

public class World extends Thread {
    @Override
    public void run() {
        for ( ;;) {
            System.out.println(Thread.currentThread().getName() + " running");
        }

    }
}

class RunnableImpl implements Runnable {

    @Override
    public void run() {
        for ( ;;) {
            System.out.println(Thread.currentThread().getName() + " running");
        }
    }
}
