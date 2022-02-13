package light_switch;

import java.util.concurrent.Semaphore;

public class LightSwitch {
	
	private int counter = 0;
	private final Semaphore mutex = new Semaphore(0);
	
	
	public void lock(Semaphore sem) throws InterruptedException {
		mutex.acquire();
		if (counter == 0)
			sem.acquire();
		counter++;
		mutex.release();
	}
	
	public void unlock(Semaphore sem) throws InterruptedException {
		mutex.acquire();
		counter--;
		if (counter == 0)
			sem.release();
		mutex.release();
	}
	
}
