package exercise3;

import utils.Utils;

/**
 * Faça um programa concorrente para multiplicar duas matrizes.
 * 
 * @author daniel
 */
public class Exercise3 {

    public static void multiplyMatrixes(double[][] matrixA, double[][] matrixB,
            double[][] result) {
        for (int k = 0; k < result.length; k++)
            for (int l = 0; l < result[k].length; l++)
                for (int i = 0; i < matrixA[k].length; i++)
                    result[k][l] += matrixA[k][i] * matrixB[i][l];
    }

    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean matrixEquals(double[][] matrixA, double[][] matrixB) {
        int m = matrixA.length;
        int n = matrixA[0].length;
        if (m != matrixB.length)
            return false;
        if (n != matrixB[0].length)
            return false;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (matrixA[i][j] != matrixB[i][j])
                    return false;
        return true;
    }

    public static void test(double[][] matrixA, double[][] matrixB) {
        double result[][] = MultiplyMatrix.createResultMatrix(matrixA, matrixB);
        multiplyMatrixes(matrixA, matrixB, result);
        printMatrix(result);
    }

    public static void testMultiplyMatrixes() {
        double matrixA[][] = { { 2, 3 }, { 0, 1 }, { -1, 4 } };
        double matrixB[][] = { { 1, 2, 3 }, { -2, 0, 4 } };
        double result[][] = MultiplyMatrix.createResultMatrix(matrixA, matrixB);
        multiplyMatrixes(matrixA, matrixB, result);
        printMatrix(result);
    }

    public static void test(int nThreads, double[][] matrixA,
            double[][] matrixB) {
        final MultiplyMatrix mult = new MultiplyMatrix(nThreads, matrixA,
                matrixB);
        mult.run();
        double resultThreads[][] = mult.getResult();
        double resultIterative[][] = MultiplyMatrix.createResultMatrix(matrixA,
                matrixB);
        multiplyMatrixes(matrixA, matrixB, resultIterative);

        printMatrix(resultIterative);
        System.out.println();
        printMatrix(resultThreads);

        System.out.println("Results is equals: "
                + matrixEquals(resultThreads, resultIterative));
    }

    public static void main(String[] args) {
        int nThreads = 8;
        int ma = 2, na = 5;
        double matrixA[][] = Utils.randomMatrix(na, ma, 2000);
        int mb = 5, nb = 2;
        double matrixB[][] = Utils.randomMatrix(nb, mb, 2000);
        test(nThreads, matrixA, matrixB);
    }
}
