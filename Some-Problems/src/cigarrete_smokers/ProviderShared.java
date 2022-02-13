package cigarrete_smokers;

import java.util.concurrent.Semaphore;

public class ProviderShared {
	public Semaphore agent = new Semaphore(1, true);
	
	public Semaphore paper = new Semaphore(0, true);
	public Semaphore match = new Semaphore(0, true);
	public Semaphore tobacco = new Semaphore(0, true);
}
