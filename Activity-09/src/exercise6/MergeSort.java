package exercise6;

import java.util.concurrent.Callable;

import utils.Utils;

public class MergeSort implements Callable<String> {
    private double[] array;

    public MergeSort(double[] array) {
        this.array = array;
    }

    private void mergesort(int begin, int end) {
        if (begin < (end - 1)) {
            int middle = (begin + end) / 2;
            mergesort(begin, middle);
            mergesort(middle, end);
            merge(begin, middle, end);
        }
    }

    private void merge(int begin, int middle, int end) {
        int n = end - begin;
        double[] copy = new double[n];

        int i = begin;
        int j = middle;
        int k = 0;

        while (i < middle && j < end) {
            if (array[i] <= array[j])
                copy[k++] = array[i++];
            else
                copy[k++] = array[j++];
        }

        while (i < middle)
            copy[k++] = array[i++];

        while (j < end)
            copy[k++] = array[j++];

        System.arraycopy(copy, 0, array, begin, n);
    }

    @Override
    public String call() {
        mergesort(0, array.length);
        return "Merge Sort";
    }
}
