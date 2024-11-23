package com.java_learning.shopping_cart;

import java.util.LinkedList;
import java.util.Queue;

class SharedResource {
    private final Queue<Integer> list;
    private int capacity;

    SharedResource ( int s ) {
        list = new LinkedList<>();
        capacity = s;
    }

    synchronized void produce ( int s ) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " came to produce");
        try {
            while ( list.size() == capacity ) {
                System.out.println(threadName + " waiting");
                wait();
            }
            System.out.println(threadName + " now producing data");
            list.add(s);
            System.out.println("list = " + list);
            notifyAll();
        } catch ( Exception e ) {

        }
    }

    synchronized void consume () {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " came to consume");

        try {
            while ( list.isEmpty() ) {
                System.out.println(threadName + " waiting");
                wait();
            }
            int data = list.poll();
            System.out.println(threadName + " now consuming data " + data);
            System.out.println("list = " + list);
            notifyAll();
        } catch ( Exception e ) {

        }
    }
}

class Producer extends Thread {

    private final SharedResource resource;

    Producer ( SharedResource r, String name ) {
        super(name);
        resource = r;
    }

    @Override
    public void run () {
        for ( int i = 0; i < 10; i++ ) {
            try {
                resource.produce((int)(Math.random() * 10) + 5 );
                Thread.sleep(500);
            } catch ( Exception e ) {

            }
        }
    }
}

class Consumer extends Thread {

    private final SharedResource resource;

    Consumer ( SharedResource r, String name ) {
        super(name);
        resource = r;
    }

    @Override
    public void run () {
        try {
            while ( true ) {
                resource.consume();
                Thread.sleep(1000);
            }
        } catch ( Exception e ) {

        }
    }
}


public class ThreadCommunication {

    public static void main ( String[] args ) {
        SharedResource res = new SharedResource(5);

        Thread t1 = new Producer(res, "Producer 1");
        Thread t2 = new Producer(res, "Producer 2");
        Thread t3 = new Consumer(res, "Consumer 1");
        Thread t4 = new Consumer(res, "Consumer 2");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
