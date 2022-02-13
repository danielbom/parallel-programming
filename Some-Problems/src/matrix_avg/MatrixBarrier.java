package matrix_avg;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MatrixBarrier {
	public static void main(String[] args) {
		int n = 100;	// Number of lines in matrix, and threads
		
		CyclicBarrier done = new CyclicBarrier(n + 1);
		

		double[][] matrix = Utils.createRandomMatrix(n, 500_000);
		RowMatrixSumBarrier[] addrs = new RowMatrixSumBarrier[n];
		
		for (int i = 0; i < n; i++) {
			addrs[i] = new RowMatrixSumBarrier(matrix, i, done);
			new Thread(addrs[i]).start();
		}
		
		try {
			done.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < n; i++) {
			System.out.println("Média da linha [" + i + "]: " + addrs[i].getResult());
		}
	}
}
