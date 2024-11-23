package multithreading;

import java.util.ArrayList;
import java.util.List;

class Source {

    private List<String> list = new ArrayList<>();
    private final int MAX = 20;

    private final static Object LOCK1 = new Object();
    private final static Object LOCK2 = new Object();

    void produce ( String data ) {
        synchronized ( LOCK1 ) {
            while ( list.size() == MAX ) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            list.add(data);
            notifyAll();
        }
    }

    void consume () {
        synchronized ( LOCK2 ) {
            while ( list.isEmpty() ) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            list.removeFirst();
            notifyAll();
        }
    }
}

public class ProducerConsumer {
}
