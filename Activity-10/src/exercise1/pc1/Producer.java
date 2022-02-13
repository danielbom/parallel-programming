package exercise1.pc1;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable {

    private ArrayBlockingQueue<Integer> product;
    private List<Integer> products;

    public Producer(ArrayBlockingQueue<Integer> product, List<Integer> products) {
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
