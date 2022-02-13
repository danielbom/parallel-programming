package activities.exercise2;

/**
 * Classe responsável por consumir recursos.
 * 
 * @author daniel
 */
public class Consumer implements Runnable {

	private SharedFifoQueue product;
	private int time;

	public Consumer(SharedFifoQueue product) {
		this(product, 0);
	}

	public Consumer(SharedFifoQueue product, int time) {
		this.product = product;
		this.time = time;
	}

	public void consume(Long value) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException " + e.getMessage());
		}
		System.out.println("Consumindo " + value);
	}

	@Override
	public void run() {
		Long value;
		try {
			while (true) {
				value = this.product.take();
				this.consume(value);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
