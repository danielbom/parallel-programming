package activity4.exercise2;

import java.util.Random;

/**
 * Classe responsável por incrementar o contador.
 * 
 * @author daniel
 */
public class Incrementer implements Runnable {

	CounterMonitor counter;
	int time;
	
	public Incrementer(CounterMonitor counterMonitor, int time) {
		this.time = time;
		this.counter = counterMonitor;
	}
	
	@Override
	public void run() {
		Random random = new Random();
		while (true) {
			try {
				if (time > 500) {
					Thread.sleep(time);
				} else {
					Thread.sleep(random.nextInt(2000));
				}
			} catch (InterruptedException e) {
				// ...
			}
			System.out.println("Increment: " + counter.increment());
		}
	}
}
