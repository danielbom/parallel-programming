package activity4.exercise2;

/**
 * Classe monitora responsável por representar um temporizador com tempo
 * variável e permitir que classes durmam até um valor específico do contador.
 * 
 * @author daniel
 */
public class CounterMonitor {
	
	Integer count = 0;
	
	public synchronized void sleepUntil(int time) {
		String name = Thread.currentThread().getName();
		System.out.println(name + ": Sleeping until: " + time);
		while (count < time) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// ...
			}
		}
		System.out.println(name + ": Wake up!!! " + time);
	}
	
	public synchronized Integer increment() {
		this.notifyAll();
		return ++this.count;
	}
	
}
