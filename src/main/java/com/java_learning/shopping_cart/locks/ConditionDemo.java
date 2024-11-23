package com.java_learning.shopping_cart.locks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedResource1 {

    private Lock lock = new ReentrantLock();
    private final int MAX_SIZE = 5;
    private final Queue<Integer> buffer = new LinkedList<>();
    private final Condition bufferNotFullCondition = lock.newCondition();
    private final Condition bufferNotEmptyCondition = lock.newCondition();

    void produce ( int n ) {
        lock.lock();
        try {
            while ( buffer.size() == MAX_SIZE ) {
                bufferNotFullCondition.await();
            }
            System.out.println("Now producing " + n);
            buffer.offer(n);
            bufferNotEmptyCondition.signal();
        } catch ( InterruptedException e ) {

        } finally {
            lock.unlock();
        }
    }

    void consume () {
        lock.lock();
        try {
            while ( buffer.isEmpty() ) {
                bufferNotEmptyCondition.await();
            }
            System.out.println("Now consuming " + buffer.poll());
            bufferNotFullCondition.signal();
        } catch ( InterruptedException e ) {

        } finally {
            lock.unlock();
        }
    }
}

public class ConditionDemo {
    public static void main ( String[] args ) {
        SharedResource1 s = new SharedResource1();
        Thread pt = new Thread(() -> {
            for ( int i = 0; i < 10; i++ ) {
                s.produce(i);
            }
        });

        Thread ct = new Thread(() -> {
            for ( int i = 0; i < 10; i++ ) {
                s.consume();
            }
        });

        pt.start();
        ct.start();
    }
}
