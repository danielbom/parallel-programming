package activity4.exercise1;

/**
 * Implemente uma solução com monitor para o problema do
 * Produtor-Consumidor usando um buffer circular.
 * 
 * @author daniel
 */
public class Exercise1 {
	public static void main(String[] args) {
		QueueLock product = new QueueLock(3);
		new Thread(new Producer(product, 1000), "Produtor").start();
		new Thread(new Consumer(product, 2000), "Consumidor").start();
	}
}
