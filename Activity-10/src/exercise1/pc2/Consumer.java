package exercise1.pc2;

import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable {

    private LinkedBlockingQueue<Integer> product;

    public Consumer(LinkedBlockingQueue<Integer> product) {
	this.product = product;
    }
    
    @Override
    public void run() {
	String name = Thread.currentThread()
			    .getName();
	while (true) {
	    if (Thread.interrupted())
		break;
	    int value = product.peek();
	    System.out.println("Consumidor " + name);
	    System.out.println("Consumindo " + value);
	}
    }
}
