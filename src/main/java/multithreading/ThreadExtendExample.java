package multithreading;

public class ThreadExtendExample {
    public static void main ( String[] args ) {
        Thread t1 = new ThreadExm1();
        Thread t2 = new ThreadExm2();

        t1.start();
        t2.start();
    }
}

class ThreadExm1 extends Thread {

    @Override
    public void run () {
        for ( int i = 0; i < 5; i++ ) {
            System.out.println("Inside " + Thread.currentThread().getName() + " with i = " + i);
        }
    }
}

class ThreadExm2 extends Thread {

    @Override
    public void run () {
        for ( int i = 0; i < 5; i++ ) {
            System.out.println("Inside " + Thread.currentThread().getName() + " with i = " + i);
        }
    }
}