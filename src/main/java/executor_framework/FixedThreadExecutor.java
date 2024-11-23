package executor_framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task1 implements Runnable {

    private final int taskId;

    Task1(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task with id " + taskId + " is being executed by thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch ( InterruptedException e ) {
            System.out.println("Task with id " + taskId + " is interrupted");
        }
    }
}

public class FixedThreadExecutor {
    public static void main ( String[] args ) {
        try (ExecutorService es = Executors.newFixedThreadPool(5)) {
            for ( int i = 0; i < 10; i++ ) {
                es.execute(new Task1(i));
            }
        }
    }
}
