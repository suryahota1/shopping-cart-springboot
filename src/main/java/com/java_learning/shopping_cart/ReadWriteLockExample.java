package com.java_learning.shopping_cart;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    private int counter = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment () {
        writeLock.lock();
        try {
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented the value to " + counter);
        } finally {
            writeLock.unlock();
        }
    }

    public void print () {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reading value " + counter);
        } finally {
            readLock.unlock();
        }
    }
}
