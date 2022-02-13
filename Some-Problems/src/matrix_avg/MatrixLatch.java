package matrix_avg;

import java.util.concurrent.CountDownLatch;

public class MatrixLatch {
	
	public static void main(String[] args) {
		int n = 100;	// Number of lines in matrix, and threads
		
		CountDownLatch done = new CountDownLatch(n);
		
		double[][] matrix = Utils.createRandomMatrix(n, 500_000);
		RowMatrixSumLatch[] addrs = new RowMatrixSumLatch[n];
		
		for (int i = 0; i < n; i++) {
			addrs[i] = new RowMatrixSumLatch(matrix, i, done);
			new Thread(addrs[i]).start();
		}
		
		try {
			done.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < n; i++) {
			System.out.println("Média da linha [" + i + "]: " + addrs[i].getResult());
		}
	}
}
