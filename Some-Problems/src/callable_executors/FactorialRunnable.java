package callable_executors;

public class FactorialRunnable implements Runnable {

    private long result = 0;
    private long k;

    public FactorialRunnable(long k) {
        this.k = k;
    }

    @Override
    public void run() {
        result = Calculus.factorial(k);
    }

    public long get() {
        return result;
    }

    static void calcule(long k) {
        try {
            FactorialRunnable runnableFactorial = new FactorialRunnable(k);
            Thread thread = new Thread(runnableFactorial);
            thread.start();
            thread.join();
            System.out.println("Factorial of " + k + " is " + runnableFactorial.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        calcule(5);
        calcule(10);
        calcule(20);
    }
}
