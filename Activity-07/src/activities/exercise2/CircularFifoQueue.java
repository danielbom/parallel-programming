package activities.exercise2;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Classe representante de uma fila circular de tamanho finito.
 * 
 * @author daniel
 */
public class CircularFifoQueue<T> {

	private ArrayList<T> queue;
	private int begin = 0;
	private int end = 0;
	private int filled = 0;

	public CircularFifoQueue(int length) {
		queue = new ArrayList<T>(length);
		for (int i = 0; i < length; i++)
			queue.add(null);
	}

	public T get() {
		if (!this.empty()) {
			T value = queue.get(begin);
			begin = this.next(begin);
			filled--;
			return value;
		}
		return null;
	}

	public void put(T value) {
		if (!this.full()) {
			queue.set(end, value);
			filled++;
			end = this.next(end);
		}
	}

	public boolean empty() {
		return filled == 0;
	}

	public boolean full() {
		return filled == queue.size();
	}

	private int next(int x) {
		return (x + 1) % queue.size();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int k = begin;

		sb.append('[');
		for (int i = 0; i < filled - 1; i++, k = next(k)) {
			sb.append(queue.get(k));
			sb.append(", ");
		}
		sb.append(next(k));
		sb.append(']');
		
		return sb.toString();
	}
}
