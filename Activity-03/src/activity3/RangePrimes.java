package activity3;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class RangePrimes {
	
	private long begin;
	private long end;
	private int nthreads;
	private ArrayList<Long> primes;

	public RangePrimes(long begin, long end, int nthreads) {
		this.begin = begin;
		this.end = end;
		this.nthreads = nthreads;
	}
	
	public long countPrimesIterative() {
		primes = new ArrayList<Long>();
		
		for (long i = begin; i < end; i++)
			if (Primes.isPrime(i))
				primes.add(i);

		return primes.size();
	}
	
	public long countPrimesUnsafe() {
		primes = new ArrayList<Long>();
		Shared<Long> it = new Shared<Long>(begin);
		ArrayList<Thread> threads = new ArrayList<Thread>(nthreads);
		
		for (int i = 0; i < nthreads; i++) {
			threads.add(new Thread(() -> {
				while (it.get() < end) {
					if (Primes.isPrime(it.get()))
						primes.add(it.get());
					it.set(it.get() + 1);
				}
			}));
		}
		
		threads.forEach(t -> t.start());
		
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// Ignore...
			}
		}
		
		return primes.size();
	}
	
	public long countPrimesAtomic() {
		primes = new ArrayList<Long>();
		AtomicLong it = new AtomicLong(begin);
		ArrayList<Thread> threads = new ArrayList<Thread>(nthreads);
		
		for (int i = 0; i < nthreads; i++) {
			threads.add(new Thread(() -> {
				long v = it.getAndAdd(1);
				while (v < end) {
					if (Primes.isPrime(v)) {
						synchronized(primes) {
							primes.add(v);							
						}
					}
					v = it.getAndAdd(1);
				}
			}));
		}
		
		threads.forEach(t -> t.start());
		
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// Ignore...
			}
		}
		
		return primes.size();
	}
	
	public ArrayList<Long> getPrimes() {
		return primes;
	}
}

