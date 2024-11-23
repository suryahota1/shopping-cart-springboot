package executor_framework;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Tourist implements Runnable {

    private final int touristId;
    private final CyclicBarrier barrier;

    Tourist (int touristId, CyclicBarrier barrier) {
        this.touristId = touristId;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        for ( int i = 1; i < 4; i++ ) {
            int timer = (int)(Math.random() * 10000) + 100;
//            System.out.println("Tourist " + touristId + " will take " + timer + " millis");
            try {
                Thread.sleep(timer);
                System.out.println("Tourist " + touristId + " reaching end of stage " + i);
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class CyclicBarrierDemo {
    public static void main ( String[] args ) {
        CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            System.out.println("Tour guide talking now");
        });
        for ( int i = 1; i < 5; i++ ) {
            new Thread(new Tourist(i, barrier)).start();
        }
    }
}
