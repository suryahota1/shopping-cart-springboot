package executor_framework;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CallableTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "Hello";
    }
}

public class CallableDemo {
    public static void main ( String[] args ) {
        try (ExecutorService es = Executors.newFixedThreadPool(1)) {
            Future<String> result = es.submit(new CallableTask());
            try {
                System.out.println(result.get());
            } catch (InterruptedException | ExecutionException e ) {

            }
        }
    }
}
