package producer_consumer.v1;

public class Product { // ResourceMonitor
	
	private Integer value = null;
	
	public synchronized void put(Integer value) {
		while (this.value != null) {
			try {
				System.out.println("LOG: Lock!     " + Thread.currentThread().getName());
				this.wait();
			} catch (InterruptedException e) {
				System.out.println("LOG: Fail lock! " + Thread.currentThread().getName());
				// Ignore ...
			}
		}
		System.out.println("LOG: Unlocked! " + Thread.currentThread().getName());
		// I need work!!!
		this.value = value;
		this.notifyAll();
	}
	
	public synchronized Integer take() {
		while (this.value == null) {
			try {
				System.out.println("LOG: Lock!     " + Thread.currentThread().getName());
				this.wait();
			} catch (InterruptedException e) {
				System.out.println("LOG: Fail lock! " + Thread.currentThread().getName());
				// Ignore ...
			}
		}
		System.out.println("LOG: Unlocked! " + Thread.currentThread().getName());
		Integer taked = this.value;
		this.value = null;
		// I need work!!!
		this.notifyAll();
		return taked;
	}


}
