package activity4.exercise4;

/**
 * Implemente uma solução para o problema do Barbeiro
 * Dorminhoco usando monitores.
 * 
 * @author daniel
 */
public class Exercise4 {
	public static void main(String[] args) {
		Chairs chairs = new Chairs(5);
		new Thread(new Clients(chairs), "Clientes").start();
		new Thread(new Barber(chairs), "Barbeiro").start();
	}
}
