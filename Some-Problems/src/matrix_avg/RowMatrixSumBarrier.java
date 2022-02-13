package matrix_avg;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RowMatrixSumBarrier implements Runnable {

	private double[][] matrix;
	private int i;
	private CyclicBarrier done;
	private double result;

	public RowMatrixSumBarrier(double[][] matrix, int i, CyclicBarrier done) {
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
		
		try {
			done.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public double getResult() {
		return result;
	}
}
