package executor_framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {

    private final int taskId;

    Task(int taskId) {
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

public class SingleThreadExecutor {

    public static void main ( String[] args ) {
        try (ExecutorService es = Executors.newSingleThreadExecutor()) {
            for (int i = 0; i < 10; i++) {
                es.execute(new Task(i));
            }
        }
    }
}
