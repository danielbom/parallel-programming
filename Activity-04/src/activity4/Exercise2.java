package activity4;

import java.util.Random;

import activity4.core.ObjectMonitor;

/**
 * Escreva uma monitor Counter que possibilita um processo
 * dormir até o contador alcançar um valor. A classe Counter
 * permite duas operações: increment() e sleepUntil(int x).
 * 
 * @author daniel
 */
public class Exercise2 {
	public static void main(String[] args) {
		Counter counter = new Counter(0);
		ObjectMonitor<Integer> monitor = new ObjectMonitor<Integer>(counter);
		
		Runnable incrementer = () -> {
			String name = Thread.currentThread().getName();
			Random random = new Random();
			while (true) {
				try {
					Thread.sleep(500 + random.nextInt(1000));
				} catch(InterruptedException e) {
					// ...
				}
				Integer value = monitor.produce(null);
				System.out.println(name + ": produzindo " + value);
			}
		};
		
		Runnable sleeper = () -> {
			String name = Thread.currentThread().getName();
			Random random = new Random();
			int sleep = 0;
			while (true) {
				Integer value = random.nextInt(10);
				sleep += value;
				System.out.println(name + ": Dormindo até " + sleep);
				counter.sleepUntil(value);
				monitor.consume();
				System.out.println(name + ": Acordando");
			}
		};
		
		new Thread(incrementer, "Incrementador").start();
		new Thread(sleeper, "Dorminhoco").start();
		
	}
}
