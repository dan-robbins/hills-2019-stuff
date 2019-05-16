import java.util.LinkedList;

/**
 * Given a <code>LinkedList</code>, this class will find the maximum over a
 * subset of its <code>Integers</code>.
 */
public class ParallelWorker extends Thread {

	private LinkedList<Integer> list;
	
	ParallelWorker(LinkedList<Integer> list) {
		this.list = list;
	}

	public void run() {
		while (true) {
			int number;
			// check if list is not empty and removes the head
			// synchronization needed to avoid atomicity violation
			synchronized(list) {
				if (list.isEmpty())
					return; // list is empty
				number = list.remove();
			}
			
			// Simulate doing some work by sleeping for given duration
			try {
				Thread.sleep(number);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
