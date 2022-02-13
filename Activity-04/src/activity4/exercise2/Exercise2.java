package activity4.exercise2;

/**
 * Escreva uma monitor Counter que possibilita um processo
 * dormir até o contador alcançar um valor. A classe Counter
 * permite duas operações: increment() e sleepUntil(int x).
 * 
 * @author daniel
 */
public class Exercise2 {
	public static void main(String[] args) {
		CounterMonitor counter = new CounterMonitor();
		new Thread(new Incrementer(counter, 0)).start();
		new Thread(new Sleeper(counter, 10), "Soneca").start();
		new Thread(new Sleeper(counter, 25), "Descanço").start();
		new Thread(new Sleeper(counter, 50), "Dorminhoco").start();
	}
}
