package Part1_ThreadSafety;

class Counter  implements Runnable{
    private int c = 0;
    //Lock l = new ReentrantLock();

    public int getValue() {
        return c;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            //synchronized(this) {
            //l.lock();
                c = c + 1;
            //l.unlock();
            //}
        }
    }
}

public class RaceConditions{
    public static void main(String[] args) {
        int numThreads = 100;
        Counter counter = new Counter();
        Thread[] threads = new Thread[numThreads];
        for(int i = 0; i < numThreads; i++){
            threads[i] = new Thread(counter);
            threads[i].start();
        }

        try {
            for(Thread thread : threads)
                thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.getValue());
    }
}