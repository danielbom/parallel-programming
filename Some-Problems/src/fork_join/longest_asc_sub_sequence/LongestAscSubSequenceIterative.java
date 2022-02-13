package fork_join.longest_asc_sub_sequence;

public class LongestAscSubSequenceIterative implements Runnable {
    private double[] array;

    private double[] result;

    private double[] aux;
    private int maxDepth = 0;
    private double maxSum = 0.0;

    public LongestAscSubSequenceIterative(double[] array) {
        this.array = array;
    }

    public double[] getResult() {
        double[] res = new double[maxDepth + 1];
        System.arraycopy(result, 0, res, 0, maxDepth + 1);
        return res;
    }

    private void visit(int index, double sum, int depth) {
        aux[depth] = array[index];

        if (depth > maxDepth || (depth == maxDepth && sum > maxSum)) {
            maxSum = sum;
            maxDepth = depth;
            System.arraycopy(aux, 0, result, 0, depth + 1);
        }

        for (int i = index + 1; i < array.length; i++)
            if (array[index] <= array[i])
                visit(i, sum + array[i], depth + 1);
    }

    @Override
    public void run() {
        this.result = new double[array.length];
        this.aux = new double[array.length];

        for (int i = 0; i < array.length; i++)
            visit(i, array[i], 0);
    }
}
