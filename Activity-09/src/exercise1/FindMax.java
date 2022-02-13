package exercise1;

import java.util.concurrent.Callable;

public class FindMax implements Callable<Integer> {
    private int end;
    private int begin;
    private double[] array;

    public FindMax(double[] array, int begin, int end) {
        this.array = array;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int indexMax = begin;
        for (int i = begin + 1; i < end; i++) {
            if (array[indexMax] < array[i]) {
                indexMax = i;
            }
        }
        return indexMax;
    }
}
