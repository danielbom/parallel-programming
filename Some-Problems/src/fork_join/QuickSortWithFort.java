package fork_join;

import java.util.concurrent.RecursiveAction;

public class QuickSortWithFort implements Runnable {
    private double[] array;

    public QuickSortWithFort(double[] array) {
        this.array = array;
    }

    public double[] getArray() {
        return array;
    }

    @Override
    public void run() {
        new Partition(0, array.length - 1).compute();
    }

    private class Partition extends RecursiveAction {
        private int begin, end;

        public Partition(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        protected void compute() {
            Partition left = null;
            Partition right = null;

            int i = begin, j = end;
            double elem = array[(begin + end) / 2];

            while (i <= j) {
                while (elem > array[i])
                    i++;
                while (elem < array[j])
                    j--;
                if (i <= j) {
                    double aux = array[i];
                    array[i++] = array[j];
                    array[j--] = aux;
                }
            }

            if (i < end) {
                left = new Partition(i, end);
                left.fork();
            }
            if (j > begin) {
                right = new Partition(begin, j);
                right.fork();
            }

            if (i < end)
                left.join();
            if (j > begin)
                right.join();
        }
    }
}
