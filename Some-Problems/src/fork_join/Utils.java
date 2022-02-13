package fork_join;

public class Utils {
    public static void merge(double[] array, int begin, int middle, int end) {
        int n = end - begin;
        double[] copy = new double[n];

        int i = begin, j = middle, k = 0;

        while (i < middle && j < end)
            copy[k++] = array[array[i] <= array[j] ? i++ : j++];

        while (i < middle)
            copy[k++] = array[i++];

        while (j < end)
            copy[k++] = array[j++];

        System.arraycopy(copy, 0, array, begin, n);
    }

    public static double[] randomArray(int n, double limit) {
        double[] array = new double[n];
        for (int i = 0; i < n; i++)
            array[i] = Math.round(Math.random() * limit);
        return array;
    }

    public static boolean isSorted(double[] array) {
        for (int i = 1; i < array.length; i++)
            if (array[i - 1] > array[i])
                return false;
        return true;
    }

    public static double[] cloneArray(double[] array) {
        double[] newArray = new double[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }
    
    public static boolean equalsArray(double[] a1, double[] a2) {
        if (a1.length != a2.length || a1.length == 0 || a2.length == 0)
            return false;

        for (int i = 0; i < a1.length; i++)
            if (a1[i] != a2[i]) 
                return false;
        return true;
    }
}
