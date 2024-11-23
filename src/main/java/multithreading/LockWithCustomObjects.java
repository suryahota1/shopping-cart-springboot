package multithreading;

public class LockWithCustomObjects {

    private static int counter1 = 0;
    private static int counter2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main ( String[] args ) {
        Thread t1 = new Thread(LockWithCustomObjects::increment1);

        Thread t2 = new Thread(LockWithCustomObjects::increment2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch ( InterruptedException e ) {

        }
        System.out.println("Final values = " + counter1 + " " + counter2);
    }

    private static void increment1 () {
        synchronized ( lock1 ) {
            counter1++;
        }
    }

    private static void increment2 () {
        synchronized ( lock2 ) {
            counter2++;
        }
    }
}
