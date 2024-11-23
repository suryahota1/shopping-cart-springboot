package executor_framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    private static int factorial ( int n ) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int result = 1;
        for ( int i = 1; i <= n; i++ ) {
            result *= i;
        }
        return result;
    }
    public static void main ( String[] args ) {
//        ExecutorService es = Executors.newFixedThreadPool(9);
//        long startTime = System.currentTimeMillis();
//        for ( int i = 1; i <= 10; i++ ) {
//            int finalI = i;
//            Future<?> f = es.submit(() -> {
//                System.out.println(factorial(finalI));
//            });
//        }
//        es.shutdown();
//        try {
//            while ( !es.awaitTermination(10, TimeUnit.MILLISECONDS) ) {
//                System.out.println("Waiting !!");
//            }
//        } catch ( InterruptedException e ) {
//
//        }
//
//        System.out.println("final time " + (System.currentTimeMillis() - startTime));

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        ses.scheduleAtFixedRate(() -> {
            System.out.println("Executing statement");
        }, 5, 5, TimeUnit.SECONDS);
        ses.schedule(() -> {
            System.out.println("Initiating shutdown");
            ses.shutdown();
        }, 20, TimeUnit.SECONDS);
    }
}
