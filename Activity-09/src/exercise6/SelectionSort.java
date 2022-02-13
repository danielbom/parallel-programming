package exercise6;

import java.util.concurrent.Callable;

import utils.Utils;

public class SelectionSort implements Callable<String> {

    private double[] array;

    public SelectionSort(double[] array) {
        this.array = array;
    }

    @Override
    public String call() {
        sorter:
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j])
                    minIndex = j;
                if (Thread.interrupted())
                    break sorter;
            }

            double aux = array[i];
            array[i] = array[minIndex];
            array[minIndex] = aux;

            if (Thread.interrupted())
                break;
        }

        return "Selection Sort";
    }
}
