package matrix_avg;

public class Utils {
	
	public static double[][] createRandomMatrix(int n, int m) {
		double[][] matrix = new double[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = i;
			}
		}
		return matrix;
	}
}
