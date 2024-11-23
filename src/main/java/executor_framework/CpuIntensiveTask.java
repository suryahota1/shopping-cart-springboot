package executor_framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CpuTask implements Runnable {

    @Override
    public void run() {
        System.out.println("CPU intensive task is being run by " + Thread.currentThread().getName());
    }
}

public class CpuIntensiveTask {
    public static void main ( String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("cores = " + cores);
        try (ExecutorService es = Executors.newFixedThreadPool(cores)) {
            for ( int i = 0; i < 20; i++ ) {
                es.execute(new CpuTask());
            }
        }
    }
}
