package activities.exercise1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {
	Lock locker = new ReentrantLock();
	private int value;
	
	public CounterLock() {
		this(0);
	}
	public CounterLock(int initial) {
		value = initial;
	}

	public int increment() {
		locker.lock();
		value++;
		locker.unlock();
		return value;
	}
	
	public int decrement() {
		locker.lock();
		value--;
		locker.unlock();
		return value;
	}
}
