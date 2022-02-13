package filosopher_dinner.v1;

import java.util.concurrent.Semaphore;

public class Fork {
	private Semaphore mutexFork = new Semaphore(1, true);
	private int i;

	public Fork(int position) {
		i = position;
	}
	
	public void use() throws InterruptedException {
		mutexFork.acquire();
	}
	
	public void discard() {
		mutexFork.release();
	}

	public int getPosition() {
		return i;
	}
	
	public String toString() {
		return String.valueOf(i);
	}
}
