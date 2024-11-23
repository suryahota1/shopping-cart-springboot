package multithreading;


public class JoinExample {
    public static void main ( String[] args ) {
        Thread t1 = new Thread(() -> {
            for ( int i = 0; i < 5; i++ ) {
                System.out.println(Thread.currentThread().getName() + " running");
                try {
                    Thread.sleep(1000);
                } catch ( InterruptedException e ) {

                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch ( InterruptedException e ) {

            }

            for ( int i = 0; i < 5; i++ ) {
                System.out.println(Thread.currentThread().getName() + " running");
                try {
                    Thread.sleep(1000);
                } catch ( InterruptedException e ) {

                }
            }
        });

        t1.start();
        t2.start();
        try {
            t2.join();
        } catch ( InterruptedException e ) {

        }
        System.out.println("Now exit main code");
    }
}
