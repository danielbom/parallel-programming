package matrix_avg;

import java.util.concurrent.CountDownLatch;

public class RowMatrixSumLatch implements Runnable {
	
	private int i;
	private double[][] matrix;
	private CountDownLatch done;
	private double result;

	public RowMatrixSumLatch(double[][] matrix, int i, CountDownLatch done) {
		this.matrix = matrix;
		this.i = i;
		this.done = done;
	}
	
	@Override
	public void run() {
		double[] row = matrix[i];
		
		double sum = 0;
		for (int k = 0; k < row.length; k++) {
			sum += row[k];
		}
		result = sum / row.length;
		
		done.countDown();
	}

	public double getResult() {
		return result;
	}
}
