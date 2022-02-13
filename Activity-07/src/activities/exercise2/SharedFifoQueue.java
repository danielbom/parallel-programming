package activities.exercise2;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedFifoQueue {

	CircularFifoQueue<Long> queue;
	Lock locker = new ReentrantLock();
	Condition isEmpty = locker.newCondition();
	Condition isFull = locker.newCondition();
	
	Random random = new Random();
	
	public SharedFifoQueue(int length) {
		queue = new CircularFifoQueue<Long>(length);
	}
	
	public void put(Long value) throws InterruptedException {
		locker.lock();
		while (queue.full())
			isFull.await();
		
		queue.put(value);
		
		isEmpty.signal();
		locker.unlock();
	}
	
	public Long take() throws InterruptedException {
		locker.lock();
		while (queue.empty())
			isEmpty.await();
		
		Long value = queue.get();
		
		isFull.signal();
		locker.unlock();
		return value;
	}
}
