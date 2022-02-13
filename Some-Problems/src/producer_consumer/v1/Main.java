package producer_consumer.v1;

public class Main {
	
	public static void main(String[] args) {
		Product product = new Product();
		new Thread(new Productor(product, 500), "Produtor 1").start();
		new Thread(new Productor(product, 250), "Produtor 2").start();
		new Thread(new Consumer(product), "Consumidor 1").start();
//		new Thread(new Consumer(product), "Consumidor 2").start();
	}
	
}
