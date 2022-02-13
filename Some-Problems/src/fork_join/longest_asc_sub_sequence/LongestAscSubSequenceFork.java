package fork_join.longest_asc_sub_sequence;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.Semaphore;

public class LongestAscSubSequenceFork implements Runnable {

    private double[] array;

    private double[] result;
    private double maxSum = 0;
    private int maxDepth = 0;

    Semaphore mutex = new Semaphore(1);

    LongestAscSubSequenceFork(double[] array) {
        this.array = array;
        result = new double[array.length];
    }

    public double[] getResult() {
        double[] res = new double[maxDepth + 1];
        System.arraycopy(result, 0, res, 0, maxDepth + 1);
        return res;
    }

    @Override
    public void run() {
        ArrayList<Visit> visits = new ArrayList<Visit>(array.length);

        for (int i = 0; i < array.length; i++) {
            Visit visit = new Visit(i, array[i], 0, new LinkedList<Double>());
            visits.add(visit);
            visit.fork();
        }

        for (Visit visit : visits)
            visit.join();
    }

    class Visit extends RecursiveAction {

        private int index, depth;
        private double sum;
        private LinkedList<Double> partial;

        public Visit(int index, double sum, int depth, LinkedList<Double> partial) {
            this.index = index;
            this.depth = depth;
            this.sum = sum;
            this.partial = partial;
        }

        @Override
        protected void compute() {
            try {
                int i = 0;
                
                mutex.acquire();
                partial.add(array[index]);
                if (depth > maxDepth || (depth == maxDepth && sum > maxSum)) {
                    maxSum = sum;
                    maxDepth = depth;
                    for (Double p : partial)
                        result[i++] = p;
                }
                mutex.release();

                LinkedList<Visit> visits = new LinkedList<Visit>();
                for (i = index + 1; i < array.length; i++) {
                    if (array[index] <= array[i]) {
                        LinkedList<Double> clone = (LinkedList<Double>) partial.clone();
                        visits.add(new Visit(i, sum + array[i], depth + 1, clone));
                    }
                }

                invokeAll(visits);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
