package activities.exercise2;

import java.util.Random;

/**
 * Classe responsável por produzir recursos.
 * 
 * @author daniel
 */
public class Producer implements Runnable {

	private SharedFifoQueue product;
	
	private Random random = new Random();

	private int time;
	
	public Producer(SharedFifoQueue product) {
		this(product, 0);
	}
	
	public Producer(SharedFifoQueue product, int time) {
		this.product = product;
		this.time = time;
	}
	
	private Long makeProduct() {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException " + e.getMessage());
		}
		Long value = random.nextLong();
		System.out.println("Produzindo " + value);
		return value;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				this.product.put(this.makeProduct());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
