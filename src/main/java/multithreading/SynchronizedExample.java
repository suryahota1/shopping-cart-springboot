package multithreading;

public class SynchronizedExample {

    private static int counter = 0;

    private static synchronized void increment () {
        counter++;
    }

    public static void main ( String[] args ) {

        Thread t1 = new Thread(() -> {
            for ( int i = 0; i < 10000; i++ ) {
                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for ( int i = 0; i < 10000; i++ ) {
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch ( InterruptedException e ) {

        }
        System.out.println("counter = " + counter);
    }
}
