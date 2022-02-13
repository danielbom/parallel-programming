package activity4;

import java.util.Random;

import activity4.core.ObjectMonitor;

/**
 * Implemente uma solução com monitor para o problema do
 * Produtor-Consumidor usando um buffer circular.
 * 
 * @author daniel
 */
public class Exercise1 {
	public static void main(String[] args) {
		QueueMonitorable<Integer> queue = new QueueMonitorable<Integer>(10);
		ObjectMonitor<Integer> monitor = new ObjectMonitor<Integer>(queue);

		int timeConsumer = 2000;
		Runnable consumer = () -> {
			while (true) {
				Integer value = monitor.consume();
				try {
					Thread.sleep(timeConsumer);
				} catch (InterruptedException e) {
					// ...
				}
				System.out.println(Thread.currentThread().getName() + ": Consumindo " + value);
			}
		};
		
		int timeProducer = 1000;
		Runnable producer = () -> {
			Random random = new Random(); // Not thread safe
			while (true) {
				try {
					Thread.sleep(timeProducer);
				} catch (InterruptedException e) {
					// ...
				}
				Integer value = random.nextInt(1000);
				System.out.println(Thread.currentThread().getName() + ": Produzindo " + value);
				monitor.produce(value);
			}
		};

		new Thread(consumer, "Consumidor").start();
		new Thread(producer, "Produtor 1").start();
		new Thread(producer, "Produtor 2").start();
	}
}
