package stencil1D;

import java.util.Arrays;

public class TestStencil1D {

    static long startTime, endTime;
    static Stencil1D stencil;

    static void startTimer(String name) {
        startTime = System.nanoTime();
        System.out.println(name);
    }

    static void endTimer() {
        endTime = System.nanoTime();
        System.out.println(Arrays.toString(stencil.getResult()));
        long duration = (endTime - startTime);
        System.out.println("Time: " + duration);
        System.out.println();
    }

    public static double[] randomArray(int n, double limit) {
        double[] array = new double[n];
        for (int i = 0; i < n; i++) {
            array[i] = Math.round(Math.random() * limit);
        }
        return array;
    }

    public static void main(String[] args) {
        double[] array = { 1, 3, 4, 4, 3, 1 };
//	double[] array = randomArray(20000000, 50);
        int numberOfThreads = 16;
        int iterations = 5;
        stencil = new Stencil1D(array, numberOfThreads, iterations);

        startTimer("Iterativo");
        stencil.iterative();
        endTimer();

        startTimer("Latch");
        stencil.latch();
        endTimer();

        startTimer("Barrier");
        stencil.barrier();
        endTimer();

        startTimer("Phaser barrier");
        stencil.phasersBarrier();
        endTimer();

//		startTimer("Phaser");
//		stencil.phasers();
//		endTimer();
    }
}
