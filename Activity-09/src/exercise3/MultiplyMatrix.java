package exercise3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiplyMatrix implements Runnable {

    private double[][] matrixA;
    private double[][] matrixB;
    private double[][] result;
    private int nThreads;

    public MultiplyMatrix(int nThread, double[][] matrixA, double[][] matrixB) {
        this(nThread, matrixA, matrixB,
                MultiplyMatrix.createResultMatrix(matrixA, matrixB));
    }

    public MultiplyMatrix(int nThreads, double[][] matrixA, double[][] matrixB,
            double[][] result) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
        this.nThreads = nThreads;
    }

    public double[][] getResult() {
        return result;
    }

    @Override
    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);

        // Tasks = m,n of result
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = 0;
                executor.execute(new Multiply(i, j));
            }
        }

        executor.shutdown();
        while (!executor.isTerminated());
    }

    public static double[][] createResultMatrix(double[][] matrixA,
            double[][] matrixB) {
        int m = matrixA.length;
        int n = matrixB[0].length;
        double[][] result = new double[m][];
        for (int i = 0; i < m; i++) {
            result[i] = new double[n];
            for (int j = 0; j < result[i].length; j++)
                result[i][j] = 0;
        }
        return result;
    }

    class Multiply implements Runnable {
        private int k, l;

        public Multiply(int line, int column) {
            this.k = line;
            this.l = column;
        }

        @Override
        public void run() {
            for (int i = 0; i < matrixA[k].length; i++)
                result[k][l] += matrixA[k][i] * matrixB[i][l];
        }
    }
}
