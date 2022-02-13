package activity4.exercise3;

/**
 * Escreva um monitor BoundedCounter que possui um valor
 * mı́nimo e máximo. A classe possui dois métodos: increment()
 * e decrement(). Ao alcançar os limites mı́nimo ou máximo, a
 * thread que alcançou deve ser bloqueada.
 * 
 * @author daniel
 */
public class Exercise3 {
	public static void main(String[] args) {
		BoundedCounter counter = new BoundedCounter(0, 10);
		new Thread(new Incrementer(counter), "Inc").start();
		new Thread(new Incrementer(counter), "Inc").start();
		new Thread(new Incrementer(counter), "Inc").start();
		new Thread(new Incrementer(counter), "Inc").start();
		
		new Thread(new Decrementer(counter), "Dec").start();
		new Thread(new Decrementer(counter), "Dec").start();
		new Thread(new Decrementer(counter), "Dec").start();
		new Thread(new Decrementer(counter), "Dec").start();
	}
}
