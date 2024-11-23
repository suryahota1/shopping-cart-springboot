package com.java_learning.shopping_cart;

public class Counter {
    private int counter = 0;

    public void increment () {
        synchronized ( this ) {
            counter++;
        }
    }

    public int getCounter () {
        return counter;
    }
}
