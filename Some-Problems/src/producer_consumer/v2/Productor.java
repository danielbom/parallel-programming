package producer_consumer.v2;

import java.util.Random;

public class Productor implements Runnable {

	private Product product;
	
	private Random random = new Random();

	private int time;
	
	public Productor(Product product) {
		this(product, 0);
	}
	
	public Productor(Product product, int time) {
		this.product = product;
		this.time = time;
	}
	
	private Integer makeProduct() {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException " + e.getMessage());
		}
		return random.nextInt(100);
	}
	
	@Override
	public void run() {
		while (true) {
			this.product.put(this.makeProduct());
		}
	}
	
}
