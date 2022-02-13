package producer_consumer.v2;

public class Consumer implements Runnable {

	private Product product;
	private int time;
	
	public Consumer (Product product) {
		this(product, 0);
	}
	
	public Consumer (Product product, int time) {
		this.product = product;
		this.time = time;
	}
	
	public void consume(Integer value) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException " + e.getMessage());
		}
//		System.out.println("Consumidor " + Thread.currentThread().getName());
//		System.out.println("Consumindo " + value);
	}
	
	@Override
	public void run() {
		while (true) {
			Integer value = this.product.take();
			this.consume(value);
		}
	}
	
}
