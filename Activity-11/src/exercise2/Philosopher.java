package exercise2;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Philosopher implements Runnable {

    Fork left, right;
    
    private int i, times;

    public Philosopher(Fork left, Fork right, int i) {
        this.left = left;
        this.right = right;
        this.i = i;
    }
    
    public Philosopher setTimes(int t) {
        times = t;
        return this;
    }

    @Override
    public void run() {
        try {
            for (int j = 0; j < times; ) {
                System.out.println(i + " THINK");
                Thread.sleep(10L);
                
                if (left.take()) {
                    System.out.println(i + " TAKE FORK");
                    if (right.take()) {
                        j++;
                        System.out.println(i + " EAT " + j);
                        Thread.sleep(10L);
                        right.giveBack();
                    }
                    left.giveBack();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return "Philo(" + i + ")[" + left + "," + right + "]";
    }
}
