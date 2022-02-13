package activity4.core;

/**
 * Classe representante de um monitor para uso com Threads.
 * 
 * Usada em conjunto com Monitorable.
 * 
 * @author daniel
 */
public class ObjectMonitor<T> {
	
	Monitorable<T> monitorable;
	
	public ObjectMonitor(Monitorable<T> m) {
		monitorable = m;
	}
	
	public synchronized T produce(T value) {
		// Espere enquanto os recursos estiver cheio e sobrecarregado.
		while (monitorable.overcrowde()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// ...
			}
		}
		this.notifyAll();
		return monitorable.produce(value);
	}
	
	public synchronized T consume() {
		// Espere enquanto os recursos estiverem esgotados.
		while (monitorable.exhausted()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// ...
			}
		}
		this.notifyAll();
		return monitorable.consume();
	}
	
}
