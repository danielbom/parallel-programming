package activity4.core;

/**
 * Interface básica para tornar um objeto monitorável durante o uso com threads.
 * 
 * Usado em conjunto com ObjectMonitor.
 * 
 * @author daniel
 */
public interface Monitorable<T> {
	
	// Método usado para informar que o número de recursos está cheio.
	public boolean overcrowde();
	
	// Método usado para informar que todos os recursos estão esgotados.
	public boolean exhausted();
	
	// Método usado para produzir recurso.
	public T produce(T value);
	
	// Método usado para consumir recurso.
	public T consume();
	
}
