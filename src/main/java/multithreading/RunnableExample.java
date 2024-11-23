package multithreading;

public class RunnableExample {
    public static void main( String[] args ) {
        Thread t1 = new Thread(() -> {
            System.out.println("Inside a new thread");
            System.out.println(Thread.currentThread().getName());
        });
        t1.setName("Sample Thread");
        t1.start();

        Thread t2 = new Thread(new RunnableImpl());
        Thread t3 = new Thread(new RunnableImpl());

        t2.start();
        t3.start();
    }
}

class RunnableImpl implements Runnable {

    @Override
    public void run() {
        for ( int i = 0; i < 5; i++ ) {
            System.out.println("Inside " + Thread.currentThread().getName() + " with i = " + i);
        }
    }
}
