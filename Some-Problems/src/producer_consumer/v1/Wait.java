package producer_consumer.v1;

public class Wait {

	private final Object lock = new Object();
	private boolean signed = false;

	public void doWait() {
		synchronized (lock) {
			while (!signed) {
				try {
					System.out.println("LOG: Lock! " + Thread.currentThread().getName());
					lock.wait();
				} catch (InterruptedException e) {
					System.out.println("LOG: Fail lock! " + Thread.currentThread().getName());
					// Ignore ...
				}
			}
			System.out.println("LOG: Unlocked " + Thread.currentThread().getName());
			// I need work!!!
			signed = false;
		}
	}

	public void doNotify() {
		synchronized (lock) {
			System.out.println("LOG: NotifyAll! " + Thread.currentThread().getName());

			signed = true;
			lock.notifyAll();
		}
	}

}
