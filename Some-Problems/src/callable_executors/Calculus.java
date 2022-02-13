package callable_executors;

public class Calculus {

    public static long factorial(long k) {
        long prod = k;
        while (--k != 0) prod *= k;
        return prod;
    }

}
