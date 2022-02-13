package activity4.exercise3;

/**
 * Classe monitora que representa um contador dentro do intervalo especificado.
 * 
 * @author daniel
 */
public class BoundedCounter {
	
	Integer i, min, max;
	
	public BoundedCounter(Integer min, Integer max) {
		this.min = min < max ? min : max;
		this.max = max > min ? max : min;
		this.i = (int) ((Math.random() * (max - min)) + min); 
	}
	
	public synchronized Integer increment() {
		while (i >= max) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// ...
			}
		}
		this.notifyAll();
		return ++i;
	}
	
	public synchronized Integer decrement() {
		while (i <= min) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// ...
			}
		}
		this.notifyAll();
		return --i;
	}
	
}
