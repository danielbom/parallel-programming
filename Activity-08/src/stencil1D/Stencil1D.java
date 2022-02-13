package stencil1D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;
import java.util.stream.Stream;

public class Stencil1D {

    private final double[] original;
    private final int ITERATIONS;
    private final int NUMBER_WORKERS;
    private final Partition partition;
    private double[] array;
    private double[] copy;

    public Stencil1D(double[] array, int numberOfThreads, int iterations) {
        int n = array.length;
        this.array = new double[n];
        this.copy = new double[n];
        this.original = new double[n];
        System.arraycopy(array, 0, original, 0, n);

        ITERATIONS = iterations;
        NUMBER_WORKERS = n - 2 < numberOfThreads ? n - 2 : numberOfThreads;
        partition = new Partition(n - 2, NUMBER_WORKERS);
    }

    // Utils
    private void initialize() {
        System.arraycopy(original, 0, array, 0, original.length);
        System.arraycopy(original, 0, copy, 0, original.length);
    }

    private void swapArrays() {
        double[] aux = array;
        array = copy;
        copy = aux;
    }

    public double[] getResult() {
        return array;
    }

    private void compute(int begin, int end) {
        for (int k = begin; k < end; k++) {
            copy[k] = (array[k - 1] + array[k + 1]) / 2;
        }
    }

    // Methods
    public void iterative() {
        initialize();
        for (int i = 0; i < ITERATIONS; i++) {
            compute(1, array.length - 1);
            swapArrays();
//			System.out.println((i + 1) + " " + Arrays.toString(array));
        }
    }

    class LatchWorker implements Runnable {
        private CountDownLatch latch;
        private int begin;
        private int end;

        public LatchWorker(CountDownLatch latch, int begin, int end) {
            this.latch = latch;
            this.begin = begin;
            this.end = end;
        }

        public void setLatch(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            compute(begin, end);
            latch.countDown();
        }
    }

    public void latch() {
        initialize();

        CountDownLatch latch;
        for (int t = 0; t < ITERATIONS; t++) {
            latch = new CountDownLatch(NUMBER_WORKERS);

            for (Interval i : partition)
                new Thread(new LatchWorker(latch, i.begin + 1, i.end + 1)).start();

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//			System.out.println((t + 1) + " " + Arrays.toString(array));
            swapArrays();
        }
    }

    class BarrierWorker implements Runnable {
        private CyclicBarrier barrier;
        private int begin;
        private int end;

        public BarrierWorker(CyclicBarrier barrier, int begin, int end) {
            this.barrier = barrier;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < ITERATIONS; i++) {
                    compute(begin, end);
                    barrier.await();
                    if (begin == 1) {
//						System.out.println((i + 1) + " " + Arrays.toString(array));
                        swapArrays();
                    }
                    barrier.await();
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void barrier() {
        initialize();

        ArrayList<Thread> worker = new ArrayList<Thread>();
        CyclicBarrier barrier = new CyclicBarrier(NUMBER_WORKERS);
        for (Interval i : partition) {
            Thread t = new Thread(new BarrierWorker(barrier, i.begin + 1, i.end + 1));
            t.start();
            worker.add(t);
        }
        worker.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    class PhaserBarrierWorker implements Runnable {
        private Phaser phaser;
        private int begin;
        private int end;
        private Phaser swap;

        public PhaserBarrierWorker(Phaser phaser, Phaser swap, int begin, int end) {
            phaser.register();
            swap.register();
            this.phaser = phaser;
            this.swap = swap;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
                compute(begin, end);
                if (begin == 1) {
                    swap.arriveAndAwaitAdvance();
//					System.out.println(i + " " + Arrays.toString(array));
                    swapArrays();
                } else {
                    swap.arrive();
                }
                phaser.arriveAndAwaitAdvance();
            }
        }
    }

    public void phasersBarrier() {
        initialize();

        ArrayList<Thread> worker = new ArrayList<Thread>();
        Phaser phaser = new Phaser(0);
        Phaser swap = new Phaser(0);
        for (Interval i : partition) {
            Thread t = new Thread(new PhaserBarrierWorker(phaser, swap, i.begin + 1, i.end + 1));
            t.start();
            worker.add(t);
        }
        worker.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    class PhaserWorker implements Runnable {
        public PhaserWorker(Phaser[] phasers, int j, int begin, int end, double[] array, double[] copy) {
        }

        @Override
        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
            }
        }
    }

    public void phasers() {
        initialize();
    }
}
