package exercise6;

import java.util.concurrent.Callable;

import utils.Utils;

public class BubbleSort implements Callable<String> {

    private double[] array;

    public BubbleSort(double[] array) {
        this.array = array;
    }

    @Override
    public String call() {
        boolean trade = true;
        int n = array.length - 1;

        sorter:
        while (trade) {
            trade = false;
            for (int i = 0; i < n; i++) {
                if (array[i] > array[i + 1]) {
                    double aux = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = aux;
                    trade = true;
                }
                if (Thread.interrupted())
                    break sorter;
            }
            n--;

            if (Thread.interrupted())
                break;
        }

        return "Bubble Sort";
    }
}
