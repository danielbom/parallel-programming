package activity4.exercise4;

import activity4.core.CircularFifoQueue;

/**
 * Classe responsável por representar cadeiras na sala do barbeiro.
 * 
 * @author daniel
 */
public class Chairs {

	CircularFifoQueue<Integer> chairs;
	
	public Chairs(int length) {
		chairs = new CircularFifoQueue<Integer>(length);
	}

	// Sentar
	public synchronized Integer sitDown(Integer time) {
		while (this.chairs.full()) {
			try {
				System.out.println("Todas as cadeiras estão ocupadas, cliente foi embora.");
				this.wait();
			} catch (InterruptedException e) {
				// ...
			}
		}
		System.out.println("Cliente sentado: " + time);
		this.chairs.put(time);
		this.notifyAll();
		return time;
	}
	
	// Levantar
	public synchronized Integer getUp() {
		while (this.chairs.empty()) {
			try {
				System.out.println("Sem clientes para atender, hora de descançar.");
				this.wait();
			} catch (InterruptedException e) {
				// ...
			}
		}
		Integer time = this.chairs.get();
		System.out.println("Atendendo um cliente: " + time);
		this.notifyAll();
		return time;
	}
	
}
