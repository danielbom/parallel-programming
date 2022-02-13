package producer_consumer.v2;

public class Main {
	
	public static void main(String[] args) {
		Product product = new Product(5);
		new Thread(new Productor(product, 1500), "Produtor 1").start();
		new Thread(new Productor(product, 2250), "Produtor 2").start();
		new Thread(new Consumer(product, 2000), "Consumidor 1").start();
//		new Thread(new Consumer(product), "Consumidor 2").start();
	}
	
}
