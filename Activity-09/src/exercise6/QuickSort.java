package exercise6;

import java.util.concurrent.Callable;

import utils.Utils;

public class QuickSort implements Callable<String> {

    private double[] array;

    public QuickSort(double[] array) {
        this.array = array;
    }

    private void quicksort(int begin, int end) {
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

        if (i < end)
            quicksort(i, end);
        if (j > begin)
            quicksort(begin, j);
    }

    @Override
    public String call() {
        quicksort(0, array.length - 1);
        return "Quick Sort";
    }
}
