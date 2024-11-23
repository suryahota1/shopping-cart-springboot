package executor_framework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class ProbeTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Probing into the task ...");
    }
}

public class ScheduledThreadExecutor {
    public static void main ( String[] args ) {
        try (ScheduledExecutorService es = Executors.newScheduledThreadPool(2)) {
            es.scheduleAtFixedRate(new ProbeTask(), 1000, 3000, TimeUnit.MILLISECONDS);
            try {
                es.awaitTermination(10, TimeUnit.SECONDS);
            } catch ( InterruptedException e ) {

            } finally {
                es.shutdownNow();
            }
        }
    }
}
