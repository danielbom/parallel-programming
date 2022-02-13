package activity4.exercise3;

import java.util.Random;

/**
 * Classe responsável por decrementar o contador.
 * 
 * @author daniel
 */
public class Decrementer implements Runnable {

	BoundedCounter counter;
	
	public Decrementer(BoundedCounter counter) {
		this.counter = counter;
	}
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		Random random = new Random();
		while (true) {
			try {
				Thread.sleep(500 + random.nextInt(1500));
			} catch (InterruptedException e) {
				// ...
			}
			System.out.println(name + ": Decrementando: " + this.counter.decrement());
		}
	}
}
