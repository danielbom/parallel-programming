package exercise2;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import utils.Utils;

/**
 * Faça um programa que calcule a soma dos elementos de uma matriz MxN. Divida o
 * programa em tarefas que somam as linhas. O programa deve possibilitar
 * especificar o número de threads para resolver o problema.
 * 
 * @author daniel
 */
public class Exercise2 {

    public static double totalSumMatrix(double[][] matrix) {
        double sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int nThreads = 8;
        int m = 20; // Tasks
        int n = 10;
        double matrix[][] = Utils.randomMatrix(n, m, 2000);

        ExecutorService executor = Executors.newFixedThreadPool(nThreads);

        ArrayList<Future<Double>> results = new ArrayList<Future<Double>>();

        for (int i = 0; i < m; i++)
            results.add(executor.submit(new SumLineMatrix(matrix, i)));

        executor.shutdown();

        double totalSum = 0.0;
        for (int i = 0; i < results.size(); i++) {
            try {
                totalSum += results.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        double totalSumi = totalSumMatrix(matrix);
        System.out.println("[Thread] Total sum '" + totalSum + "'");
        System.out.println("[Iterative] Total sum '" + totalSumi + "'");
    }
}
