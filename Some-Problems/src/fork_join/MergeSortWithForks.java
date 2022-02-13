package fork_join;

import java.util.concurrent.RecursiveAction;

public class MergeSortWithForks implements Runnable {
    private double[] array;

    public MergeSortWithForks(double[] array) {
        this.array = array;
    }

    public double[] getArray() {
        return array;
    }

    @Override
    public void run() {
        new Merging(0, array.length).compute();;
    }
    
    private class Merging extends RecursiveAction {
        private int begin, end, middle;

        public Merging(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (begin < (end - 1)) {
                middle = (begin + end) / 2;
                
                Merging left = new Merging(begin, middle);
                Merging right = new Merging(middle, end);

                invokeAll(left, right);

                Utils.merge(array, begin, middle, end);
            }
        }
    }

}
