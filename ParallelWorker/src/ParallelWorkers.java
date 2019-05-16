import java.util.LinkedList;

/**
 * This class runs numThreads instances of ParallelWorker in parallel to do
 * some amount of simulated computationally intensive work
 */
public class ParallelWorkers {
	
	private ParallelWorker[] workers;

	private ParallelWorkers(int numThreads) {
		workers = new ParallelWorker[numThreads];
	}

    public static void main(String[] args) {
        int numThreads = 4; // number of threads
        int numElements = 12; // number of integers in the list
        ParallelWorkers workers = new ParallelWorkers(numThreads);
        LinkedList<Integer> list = new LinkedList<>();

        // populate the list
		System.out.println("Populating list");
        for (int i=0; i<numElements; i++){
			list.add(1000);
		}

		System.out.println("List populated");
        // run the workers
		System.out.println("Running parallel workers with " + numThreads + " threads");
		final long startTime = System.nanoTime();
        try {
            workers.doWork(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		final long runTime   = System.nanoTime() - startTime;
		System.out.println("Work finished in " + runTime/1000000000.0 + " seconds");

    }
	
	/**
	 * Does a simulated amount of work by using numThreads instances of
	 * ParallelWorker to do some amount of work.
	 */
	private void doWork(LinkedList<Integer> list) throws InterruptedException {
		
		// start numThreads instances of ParallelWorker
		for (int i=0; i<workers.length; i++) {
			workers[i] = new ParallelWorker(list);
			workers[i].start();
		}

		// wait for threads to finish
		for (ParallelWorker worker : workers)
			worker.join();
		
		System.out.println("Workers finished");
	}
	
}
