package exercise2;

import java.util.concurrent.Callable;

public class SumLineMatrix implements Callable<Double> {
    private int line;
    private double[][] matrix;

    public SumLineMatrix(double[][] matrix, int line) {
        this.matrix = matrix;
        this.line = line;
    }

    @Override
    public Double call() throws Exception {
        double sum = 0;
        for (int i = 0; i < matrix[line].length; i++)
            sum += matrix[line][i];
        return sum;
    }
}
