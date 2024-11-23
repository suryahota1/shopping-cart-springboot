package com.java_learning.shopping_cart.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class ScrapingBot {
    private Semaphore semaphore = new Semaphore(4);

    void scrape () {
        try {
            String threadName = Thread.currentThread().getName();
            semaphore.acquire();
            int delay = ((int)Math.random() * 10000) + 10000;
            System.out.println(threadName + " started scraping");
            Thread.sleep(delay);
            System.out.println(threadName + " finished scraping");
        } catch ( InterruptedException e ) {

        } finally {
            semaphore.release();
        }
    }
}

public class SemaphoreDemo {
    public static void main ( String[] args ) {
        try (ExecutorService es = Executors.newCachedThreadPool()) {
            ScrapingBot bot = new ScrapingBot();
            for ( int i = 0; i < 20; i++ ) {
                es.execute(bot::scrape);
            }
        }
    }
}
