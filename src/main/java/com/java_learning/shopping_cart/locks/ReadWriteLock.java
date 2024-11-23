package com.java_learning.shopping_cart.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedResource {
    private int counter = 0;
    private final java.util.concurrent.locks.ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock writeLock = lock.writeLock();
    private final Lock readLock = lock.readLock();

    public void increment () {
        writeLock.lock();
        try {
            counter++;
            System.out.println("Incremented by " + Thread.currentThread().getName() + " " + counter);
        } catch ( RuntimeException e ) {

        } finally {
            writeLock.unlock();
        }
    }

    public void decrement () {
        writeLock.lock();
        try {
            counter--;
            System.out.println("Decremented by " + Thread.currentThread().getName() + " " + counter);
        } catch ( RuntimeException e ) {

        } finally {
            writeLock.unlock();
        }
    }

    public void readValue () {
        readLock.lock();
        try {
            System.out.println("Read by " + Thread.currentThread().getName() + " " + counter);
        } catch ( RuntimeException e ) {

        } finally {
            readLock.unlock();
        }
    }
}

class IncThread implements Runnable {

    private final SharedResource resource;

    IncThread ( SharedResource resource ) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for ( int i = 0; i < 10; i++ ) {
            resource.increment();
        }
    }
}

class DecThread implements Runnable {

    private final SharedResource resource;

    DecThread ( SharedResource resource ) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for ( int i = 0; i < 10; i++ ) {
            resource.decrement();
        }
    }
}

class ReadThread implements Runnable {

    private final SharedResource resource;

    ReadThread ( SharedResource resource ) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for ( int i = 0; i < 10; i++ ) {
            resource.readValue();
        }
    }
}

public class ReadWriteLock {
    public static void main ( String[] args ) {
        SharedResource r = new SharedResource();
        new Thread(new IncThread(r)).start();
        new Thread(new DecThread(r)).start();
        new Thread(new ReadThread(r)).start();
    }
}
