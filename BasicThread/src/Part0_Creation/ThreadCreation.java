package Part0_Creation;

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    public void run(){
        System.out.println("Running MyThread " + Thread.currentThread().getName());
    }
}

class MyRunnable implements Runnable {
    public void run(){
        System.out.println("Running MyRunnable " + Thread.currentThread().getName());
    }
}

public class ThreadCreation {
    public static void main(String[] args) {
        Thread t0 = new Thread("t0"){
            public void run(){
                System.out.println("Running anonymous thread " + Thread.currentThread().getName());
            }
        };

        Thread t1 = new Thread(new Runnable(){
                    public void run(){
                        System.out.println("Running anonymous runnable " + Thread.currentThread().getName());
                    }
                }, "t1");

        Thread t2 = new Thread(new MyRunnable(), "t2");
        Thread t3 = new Thread(new MyRunnable(), "t3");

        Thread t4 = new MyThread("t4");
        Thread t5 = new MyThread("t5");

        Thread[] threads = {t0,t1,t2,t3,t4,t5};

        System.out.println("List of current threads: ");
        for(Thread thread : threads){
            System.out.println(thread.getName());
        }
        System.out.println();

        System.out.println("Starting all threads");
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        //t5.run(); //don't do this
        System.out.println("All threads started");
    }
}
