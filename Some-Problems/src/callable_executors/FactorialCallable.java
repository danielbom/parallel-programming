package callable_executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FactorialCallable implements Callable<Long> {
    private final long k;

    public FactorialCallable(long k) {
        this.k = k;
    }

    @Override
    public Long call() throws Exception {
        return Calculus.factorial(k);
    }

    static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void calcule(long k) {
        try {
            Future<Long> futureFactorial = executor.submit(new FactorialCallable(k));
            System.out.println("Factorial of " + k + " is " + futureFactorial.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        calcule(5);
        calcule(10);
        calcule(20);
        executor.shutdown();
    }

}
