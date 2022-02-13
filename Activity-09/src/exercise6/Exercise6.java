package exercise6;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utils.Utils;

/**
 * Faça um programa que execute três algoritmos de ordenação para um conjunto de
 * valores e exiba o resultado apenas do algoritmo que finalizar primeiro (use
 * invokeAny ).
 * 
 * Referencias:
 * https://stackoverflow.com/questions/15900387/how-to-stop-all-runnable-thread-in-java-executor-class
 * https://pt.stackoverflow.com/questions/192755/equivalente-a-goto-em-java
 *
 * @author daniel
 */
public class Exercise6 {
    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();

        double[] array = Utils.randomArray(1_000_000, 50);
        ArrayList<Callable<String>> pool = new ArrayList<Callable<String>>();

        pool.add(new BubbleSort(Utils.cloneArray(array)));
        pool.add(new InsertionSort(Utils.cloneArray(array)));
        pool.add(new SelectionSort(Utils.cloneArray(array)));
        pool.add(new MergeSort(Utils.cloneArray(array)));
        pool.add(new QuickSort(Utils.cloneArray(array)));
        String champion = executor.invokeAny(pool);
        executor.shutdownNow();

        System.out.println(champion);
    }
}
