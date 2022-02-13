package activity4;

import activity4.core.CircularFifoQueue;
import activity4.core.Monitorable;

/**
 * Classe representante de um monitor de uma fila circular. Seu comportamento
 * de monitor é definido pela interface Monitorable.
 * 
 * @author daniel
 */
public class QueueMonitorable<T> extends CircularFifoQueue<T> implements Monitorable<T> {

	public QueueMonitorable(int length) {
		super(length);
	}

	@Override
	public boolean overcrowde() {
		return this.full();
	}

	@Override
	public boolean exhausted() {
		return this.empty();
	}

	@Override
	public T produce(T value) {
		this.put(value);
		return value;
	}

	@Override
	public T consume() {
		return this.get();
	}

}
