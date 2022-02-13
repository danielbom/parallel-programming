package producer_consumer.v1;

public class Product2 {

	private final Object lock = new Object();
	private Integer value = null;

	public void put(Integer value) {
		synchronized (lock) {
			while (this.value != null) {
				try {
					System.out.println("LOG: Lock!     " + Thread.currentThread().getName());
					lock.wait();
				} catch (InterruptedException e) {
					System.out.println("LOG: Fail lock! " + Thread.currentThread().getName());
					// Ignore ...
				}
			}
			System.out.println("LOG: Unlocked! " + Thread.currentThread().getName());
			// I need work!!!
			this.value = value;
			lock.notifyAll();
		}
	}
	
	public Integer take() {
		Integer taked;
		synchronized (lock) {
			while (this.value == null) {
				try {
					System.out.println("LOG: Lock!     " + Thread.currentThread().getName());
					lock.wait();
				} catch (InterruptedException e) {
					System.out.println("LOG: Fail lock! " + Thread.currentThread().getName());
					// Ignore ...
				}
			}
			System.out.println("LOG: Unlocked! " + Thread.currentThread().getName());
			taked = this.value;
			this.value = null;
			// I need work!!!
			lock.notifyAll();
		}
		return taked;
	}
	
}
