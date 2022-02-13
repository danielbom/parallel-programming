package activity4.exercise4;

import java.util.Random;

/**
 * Classe responsável por representar clientes para serem atendido em uma 
 * barbearia. Os clientes que chegam tentam se sentar. Caso não aja cadeiras
 * para sentar o cliente vai embora.
 * 
 * @author daniel
 */
public class Clients implements Runnable {

	Chairs chairs;
	
	public Clients(Chairs chairs) {
		this.chairs = chairs;
	}
	
	@Override
	public void run() {
		Random random = new Random();
		while (true) {
			try {
				// Tempo para chegar novos clientes
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				// ...
			}
			this.chairs.sitDown(random.nextInt(1500));
		}
	}
	
}
