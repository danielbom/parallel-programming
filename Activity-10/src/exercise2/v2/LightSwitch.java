package exercise2.v2;

import java.util.concurrent.Semaphore;

public class LightSwitch {
	
	private int counter = 0;
	private final Semaphore mutex = new Semaphore(0);
	
	
	public boolean lock(Semaphore sem) throws InterruptedException {
		mutex.acquire();
		boolean begin = counter == 0;
		if (begin)
			sem.acquire();
		counter++;
		mutex.release();
		return begin;
	}
	
	public boolean unlock(Semaphore sem) throws InterruptedException {
		mutex.acquire();
		counter--;
		boolean end = counter == 0;
		if (end)
			sem.release();
		mutex.release();
		return end;
	}
	
}
