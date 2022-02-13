package activity4.test;

import activity4.core.CircularFifoQueue;

public class TestCircularFifoQueue {
	public static void main(String[] args) {
		CircularFifoQueue<Integer> fifo = new CircularFifoQueue<Integer>(3);
		fifo.put(10);
		fifo.put(20);
		fifo.put(30);
		fifo.put(40);
		
		System.out.println("All " + fifo);
		
		for (int i = 0; i < 4; i++) {
			fifo.get();
			System.out.println("Get " + (i + 1) + " " + fifo);
		}
	}
}
