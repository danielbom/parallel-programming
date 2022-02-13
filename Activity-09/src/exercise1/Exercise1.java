package exercise1;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import utils.Interval;
import utils.Partition;
import utils.Utils;

/**
 * Faça um programa que localize o maior valor em um vetor. Divida o programa em
 * tarefas que localizam o maior valor em um segmento do vetor. O programa deve
 * possibilitar especificar o número de tarefas e o número de threads para
 * resolver o problema.
 * 
 * @author daniel
 */
public class Exercise1 {

    public static int findMax(double[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[max] < array[i]) {
                max = i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int nThreads = 8;
        int parts = 100; // Tasks
        int length = 100_000;
        double[] array = Utils.randomArray(length, 3000);

        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        Partition p = new Partition(length, parts);

        ArrayList<Future<Integer>> result = new ArrayList<Future<Integer>>();

        for (Interval i : p)
            result.add(executor.submit(new FindMax(array, i.begin, i.end)));

        executor.shutdown();

        int max = 0;
        for (int i = 0; i < result.size(); i++) {
            try {
                int index = result.get(i).get();
                if (array[index] > array[max]) {
                    max = index;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        int maxi = findMax(array);

        System.out.println("[Threads] Max value '" + array[max] + "' in index '"
                + max + "'");
        System.out.println("[Iterative] Max value '" + array[maxi]
                + "' in index '" + maxi + "'");
    }
}
