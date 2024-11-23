package executor_framework;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TaskDemo implements Runnable {
    private final CountDownLatch latch;
    private final CountDownLatch waitLatch;
    TaskDemo ( CountDownLatch latch, CountDownLatch waitLatch ) {
        this.latch = latch;
        this.waitLatch = waitLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " doing something");
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " finished doing");
            latch.countDown();
            waitLatch.await();
            System.out.println(Thread.currentThread().getName() + " doing again");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class CountDownLatchDemo {

    public static void main ( String[] args ) {
        final CountDownLatch latch = new CountDownLatch(3);
        final CountDownLatch waitLatch = new CountDownLatch(1);
        try (ExecutorService es = Executors.newFixedThreadPool(3)) {
            es.execute(new TaskDemo(latch, waitLatch));
            es.execute(new TaskDemo(latch, waitLatch));
            es.execute(new TaskDemo(latch, waitLatch));
            try {
                latch.await();
                System.out.println("Inside main again");
                Thread.sleep(4000);
                waitLatch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
