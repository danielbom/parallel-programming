package activity4.exercise4;

import java.util.Random;

/**
 * Classe representante do barbeiro. Ela é responsável por chamar um cliente
 * para ser atendido.
 * 
 * @author daniel
 */
public class Barber implements Runnable {

	Chairs chairs;
	
	public Barber(Chairs chairs) {
		this.chairs = chairs;
	}
	
	@Override
	public void run() {
		Random random = new Random();
		while (true) {
			int time = this.chairs.getUp();
			try {
				// Atendendo um cliente
				Thread.sleep(time + random.nextInt(500));
			} catch (InterruptedException e) {
				// ...
			}
		}
	}
	
}
