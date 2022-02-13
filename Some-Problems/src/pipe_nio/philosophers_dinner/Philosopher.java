package pipe_nio.philosophers_dinner;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Philosopher implements Runnable {

    Fork left, right;
    
    private int i, times;
    private ConcurrentLinkedQueue<String> result;

    public Philosopher(Fork left, Fork right, int i, ConcurrentLinkedQueue<String> result) {
        this.left = left;
        this.right = right;
        this.i = i;
        this.result = result;
    }
    
    public Philosopher setTimes(int t) {
        times = t;
        return this;
    }

    @Override
    public void run() {
        try {
            for (int j = 0; j < times; ) {
                result.add(i + " THINK");
                Thread.sleep(10L);
                
                if (left.take()) {
                    result.add(i + " TAKE FORK");
                    if (right.take()) {
                        j++;
                        result.add(i + " EAT " + j);
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
