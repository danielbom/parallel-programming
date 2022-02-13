package exercise1.pc2;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Runnable {
    private LinkedBlockingQueue<Integer> product;
    private List<Integer> products;

    public Producer(LinkedBlockingQueue<Integer> product, List<Integer> products) {
	this.product = product;
	this.products = products;
    }

    @Override
    public void run() {
	products.forEach(value -> {
	    product.add(value);
	});
    }
}
