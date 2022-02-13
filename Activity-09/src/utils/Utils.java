package utils;

public class Utils {
    public static double[] randomArray(int n, double limit) {
        double[] array = new double[n];
        for (int i = 0; i < n; i++) {
            array[i] = Math.round(Math.random() * limit);
        }
        return array;
    }

    public static double[][] randomMatrix(int n, int m, double limit) {
        double[][] matrix = new double[m][];
        for (int i = 0; i < m; i++)
            matrix[i] = randomArray(n, limit);
        return matrix;
    }

    public static boolean isSorted(double[] array) {
        for (int i = 1; i < array.length; i++)
            if (array[i - 1] > array[i])
                return false;
        return true;
    }

    public static double[] cloneArray(double[] array) {
        double[] newArray = new double[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }
}
