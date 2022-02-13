package ex1;

import java.util.ArrayList;

public class Main {

	public static void test1() {
		Thread helloLambda = new Thread(() -> {
			System.out.println("Hello lambda with threads!");
		});

		helloLambda.start();
	}

	public static void test2() {
		new Pool(3).start();
	}

	public static void test3() throws InterruptedException {
		RaceCondition race = new RaceCondition();
		ArrayList<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new BasicRaceCondition(race));
			threads.add(t);
			t.start();
		}

		for (Thread thread : threads) {
			thread.join();
		}

		System.out.println(race.getNext());
	}

	public static void main(String[] args) {
		try {
			test3();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
