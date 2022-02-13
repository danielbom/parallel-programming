package exercise1;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implemente duas versões do problema do produtor/consumidor com M produtores e
 * N consumidores usando ArrayBlockingQueue e LinkedBlockingQueue. Compare o
 * desempenho das duas implementações.
 * 
 * @author daniel
 */
public class Exercise1 {

    private static String testName = "";
    private static List<Integer> products;
    private static long limit = 100_000_000;
    private static int calls = 0;

    private static void testBase(Runnable produtor, Runnable consumer) {
        long startTime = System.nanoTime();

        Thread t1 = new Thread(consumer);
        Thread t2 = new Thread(produtor);

        try {
            t2.join();
            t1.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(testName + " Time: " + duration);
    }

    private static void test(int n) {
        System.out.format("[%d] Test with %d products and %d queue length\n", calls, limit, n);
        testName = "ArrayBlockingQueue";
        ArrayBlockingQueue<Integer> arr1 = new ArrayBlockingQueue<Integer>(n);
        testBase(new exercise1.pc1.Producer(arr1, products), new exercise1.pc1.Consumer(arr1));

        testName = "LinkedBlockingQueue";
        LinkedBlockingQueue<Integer> arr2 = new LinkedBlockingQueue<Integer>(n);
        testBase(new exercise1.pc2.Producer(arr2, products), new exercise1.pc2.Consumer(arr2));
        calls++;
        System.out.println();
    }

    public static void main(String[] args) {
        products = Stream.generate(() -> (int) Math.round(Math.random() * 1000))
                         .limit(limit)
                         .collect(Collectors.toList());
        test(10);
        test(100);
        test(1000);
        test(10000);
        test(100000);
    }
}
