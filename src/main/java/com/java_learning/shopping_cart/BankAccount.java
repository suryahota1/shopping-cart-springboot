package com.java_learning.shopping_cart;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;
    private final Lock lock = new ReentrantLock();

    public void deduct (int amount) {
        System.out.println(Thread.currentThread().getName() + " to deduct money");
        try {
            if ( lock.tryLock(1000, TimeUnit.MILLISECONDS) ) {
                if ( balance >= amount ) {
                    try {
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " deducted money");
                    } catch ( Exception e ) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " insufficient balance");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire lock, will  try later");
            }
        } catch ( Exception e ) {
            Thread.currentThread().interrupt();
        }
        if ( Thread.currentThread().isInterrupted() ) {
//            Perform cleanup activity in case of interruption
        }
    }

    public int getBalance () {
        return balance;
    }
}
