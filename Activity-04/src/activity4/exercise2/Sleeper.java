package activity4.exercise2;

/**
 * Classe responsável por dormir por um tempo especificado.
 *  
 * @author daniel
 */
public class Sleeper implements Runnable {
	
	CounterMonitor counter;
	int sleep;
	
	public Sleeper(CounterMonitor counter, int sleep) {
		this.counter = counter;
		this.sleep = sleep;
	}

	@Override
	public void run() {
		counter.sleepUntil(sleep);
	}
	
}
