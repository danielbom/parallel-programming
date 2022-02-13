package exercise6;

import java.util.concurrent.Callable;

import utils.Utils;

public class InsertionSort implements Callable<String> {
    private double[] array;

    public InsertionSort(double[] array) {
        this.array = array;
    }

    @Override
    public String call() {
        int i, j;

        for (i = 0; i < array.length - 1; i++) {
            double elem = array[i + 1];
            for (j = i; j > 0 && array[j] < elem; j--)
                array[j + 1] = array[j];
            array[j + 1] = array[j];
            if (Thread.interrupted())
                break;
        }

        return "Insertion Sort";
    }
}
