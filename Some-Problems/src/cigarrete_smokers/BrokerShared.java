package cigarrete_smokers;

import java.util.concurrent.Semaphore;

public class BrokerShared {
	public boolean isTobacco = false;
	public boolean isPaper = false;
	public boolean isMatch = false;
	public Semaphore tobaccoSignal = new Semaphore(0, true);
	public Semaphore paperSignal = new Semaphore(0, true);
	public Semaphore matchSignal = new Semaphore(0, true);
	public Semaphore mutex = new Semaphore(1, true);
}
