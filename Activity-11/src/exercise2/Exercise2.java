package exercise2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Implementar o problema do jantar dos filósofos usando Java IO:
 * PipedInputStream e PipedOutputStream.
 * 
 * Referências
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html
 * https://gist.github.com/larryprice/4b129f43424ae74e1941
 * 
 * @author daniel
 *
 */
public class Exercise2 {
    static int NUMBER_FORKS = 10;
    static int DINNER_TIMES = 3;
    static ArrayList<Fork> forks = new ArrayList<Fork>();
    static ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
    static ArrayList<Thread> threads = new ArrayList<Thread>();

    static void addPhilosopher(int i, int j, int id) {
        philosophers.add(new Philosopher(forks.get(i), forks.get(j), id).setTimes(DINNER_TIMES));
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < NUMBER_FORKS; i++)
            forks.add(new Fork(i));
        
        addPhilosopher(1, 0, 1);
        for (int i = 1; i < NUMBER_FORKS - 1; i++)
            addPhilosopher(i, i + 1, i + 1);
        addPhilosopher(NUMBER_FORKS - 1, 0, NUMBER_FORKS);

        for (int i = 0; i < philosophers.size(); i++) {
            Thread thread = new Thread(philosophers.get(i));
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
