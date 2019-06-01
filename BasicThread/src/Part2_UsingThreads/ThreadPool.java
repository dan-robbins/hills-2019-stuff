package Part2_UsingThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerThread implements Runnable {
    private String message;

    public WorkerThread(String s){
        this.message=s;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+" (Start) message = "+message);
        processMessage();
        System.out.println(Thread.currentThread().getName()+" (End)");
    }
    private void processMessage() {
        try {  Thread.sleep(2000);  } catch (InterruptedException e) { e.printStackTrace(); }
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        //int numThreads = Runtime.getRuntime().availableProcessors() - 1;
        int numThreads = 7;
        int numTasks = 100;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numTasks; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
            //do nothing
        }

        System.out.println("Finished all threads");
    }
}
