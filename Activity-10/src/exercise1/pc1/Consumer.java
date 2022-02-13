package exercise1.pc1;

import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable {

    private ArrayBlockingQueue<Integer> product;

    public Consumer(ArrayBlockingQueue<Integer> product) {
        this.product = product;
    }

    @Override
    public void run() {
        String name = Thread.currentThread()
                            .getName();
        while (true) {
            try {
                if (Thread.interrupted())
                    break;
                int value = product.take();
                System.out.println("Consumidor " + name);
                System.out.println("Consumindo " + value);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
