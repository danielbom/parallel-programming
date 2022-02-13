package reader_writer.v1;

import java.util.concurrent.Semaphore;


public class ReaderWriter {
	int numReaders = 0;
	Semaphore mutex = new Semaphore(1, true);
	Semaphore wlock = new Semaphore(1, true);
	
	public void startRead() throws InterruptedException {
		mutex.acquire();
		if (numReaders == 0) wlock.acquire();
		numReaders++;
		mutex.release();
	}
	public void endRead() throws InterruptedException {
		mutex.acquire();
		numReaders--;
		if (numReaders == 0) wlock.release();
		mutex.release();
	}
	
	public void startWrite() throws InterruptedException {
		wlock.acquire();
	}
	public void endWrite() throws InterruptedException {
		wlock.release();
	}
}
