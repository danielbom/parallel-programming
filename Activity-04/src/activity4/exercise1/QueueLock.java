package activity4.exercise1;

import activity4.core.CircularFifoQueue;

/**
 * Classe representante de um monitor de uma fila.
 * 
 * @author daniel
 */
public class QueueLock {

	CircularFifoQueue<Long> circularBuffer;
	
	public QueueLock(int length) {
		circularBuffer = new CircularFifoQueue<Long>(length);
	}
	
	public synchronized void put(Long value) {
		while (circularBuffer.full()) {
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
		this.circularBuffer.put(value);
		this.notifyAll();
	}
	
	public synchronized Long take() {
		while (circularBuffer.empty()) {
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
		this.notifyAll();
		return this.circularBuffer.get();
	}
	
}
