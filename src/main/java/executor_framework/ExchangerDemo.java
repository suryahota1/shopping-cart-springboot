package executor_framework;

import java.util.concurrent.Exchanger;

class Thread1 implements Runnable {

    private final Exchanger<Integer> exchanger;

    Thread1 ( Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        System.out.println("Thread 1 now exchanging data " + 10);
        try {
            Integer receivedData = exchanger.exchange(10);
            System.out.println("Thread 1 received data " + receivedData);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Thread2 implements Runnable {

    private final Exchanger<Integer> exchanger;

    Thread2 ( Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(4000);
            System.out.println("Thread 2 now exchanging data " + 20);
            Integer receivedData = exchanger.exchange(20);
            System.out.println("Thread 2 received data " + receivedData);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class ExchangerDemo {
    public static void main ( String[] args ) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        new Thread(new Thread1(exchanger)).start();
        new Thread(new Thread2(exchanger)).start();
    }
}
