package producer_consumer.v2;

import java.util.concurrent.Semaphore;

public class Product {
	
	/**
	 * As variáveis 'canProduce' e 'canConsume' controlam a quantidade de
	 * de produtos produzidos e consumidos. O mutex faz o controle da
	 * exclusão multua durante a região critica.
	 */
	Semaphore mutex, canConsume, canProduce;
	CircularFifoQueue<Integer> queue;
	
	public Product(int length) {
		queue = new CircularFifoQueue<Integer>(length);
		canProduce = new Semaphore(length);
		canConsume = new Semaphore(0);
		mutex = new Semaphore(1);
	}
	
	
	public void put(Integer value) {
		String name = Thread.currentThread().getName();
		try {
			canProduce.acquire();
			mutex.acquire();
			System.out.println(name + ": Lock!");
			queue.put(value);
			System.out.println(name + " - Produziu: " + value + " " + canConsume.availablePermits());
			mutex.release();
			System.out.println(name + ": Unlock! " + name);
			canConsume.release();
			System.out.println();
		} catch (InterruptedException e) {
			// ...
		}
	}
	
	public synchronized Integer take() {
		String name = Thread.currentThread().getName();
		Integer value = null;
		try {
			canConsume.acquire();
			mutex.acquire();
			System.out.println(name + ": Lock!     ");
			value = this.queue.get();
			System.out.println(name + " - Consumiu: " + value + " " + canProduce.availablePermits());
			mutex.release();
			canProduce.release();
			System.out.println(name + ": Unlock! ");
			System.out.println();
		} catch (InterruptedException e) {
			// ...
		}
		return value;
	}


}
