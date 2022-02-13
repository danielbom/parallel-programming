package filosopher_dinner.v1;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		int n = 5;	// Number of Philosophers
		ArrayList<Fork> forks = new ArrayList<Fork>();
		for (int i = 0; i < n; i++)
			forks.add(new Fork(i));

		for (int i = 0; i < n; i++) {
			Fork left = forks.get(i);
			Fork right = forks.get((i + 1) % n);
			Philosopher philosopher = new Philosopher(left, right, 100);
			Thread thread = new Thread(philosopher, String.format("Thread(%2d)", i));
			thread.start();
		}
	}
}
