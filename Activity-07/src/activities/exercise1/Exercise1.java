package activities.exercise1;

import activities.Utils;

/**
 * Faça um programa usando Lock para simular a atualização de um contador que é
 * acessado por múltiplas threads. O contador pode diminuir e aumentar.
 * 
 * @author daniel
 */
public class Exercise1 {
	public static void main(String[] args) {
		CounterLock counter = new CounterLock();

		Runnable incrementer = () -> {
			String name = Thread.currentThread().getName();
			while (true) {
				try {
					long time = Utils.randomSleep(1000);
					int value = counter.increment();
					System.out.println(name + " " + time + " Incrementando " + value);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		Runnable decrementer = () -> {
			String name = Thread.currentThread().getName();
			while (true) {
				try {
					long time = Utils.randomSleep(1000);
					int value = counter.decrement();
					System.out.println(name + " " + time + " Decrementando " + value);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		new Thread(incrementer).start();
		new Thread(decrementer).start();
		new Thread(incrementer).start();
		new Thread(decrementer).start();
		new Thread(incrementer).start();
		new Thread(decrementer).start();
	}
}
