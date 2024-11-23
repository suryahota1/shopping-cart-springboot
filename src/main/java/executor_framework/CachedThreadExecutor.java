package executor_framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task2 implements Runnable {

    private final int taskId;

    Task2(int taskId) {
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

public class CachedThreadExecutor {
    public static void main ( String[] args ) {
        try (ExecutorService es = Executors.newCachedThreadPool()) {
            for ( int i = 0; i < 20; i++ ) {
                es.execute(new Task2(i));
            }
        }
    }
}
