package ex1;

/**
 * Basic class to learn how to work with threads.
 * 
 * This class receive a number of thread to create.
 * 
 * Each thread receive a number that represents how iteration it's be created,
 * and your method run prints a simple hello message with this id, and your
 * properly id of a thread.
 * 
 * @author daniel
 * @since 2019/08/19 - 15:21
 *
 */
public class Pool {

	private int numThreads;

	public Pool(int numThreads) {
		this.numThreads = numThreads;
	}

	public void start() {
		for (int i = 0; i < this.numThreads; i++) {
			new Thread(new SimpleRunner(i)).start();
			new SimpleThread(i).start();
		}
	}

	private class SimpleThread extends Thread {

		private int id;

		public SimpleThread(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			System.out.printf("(%d) Hello by runner (ID: %d)\n", this.id, Thread.currentThread().getId());
		}
	};

	private class SimpleRunner implements Runnable {
		private int id;

		public SimpleRunner(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			System.out.printf("(%d) Hello by runner (ID: %d)\n", this.id, Thread.currentThread().getId());
		}
	}
}
