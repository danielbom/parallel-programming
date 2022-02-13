package activity3;

// NoThreadSafe
public class Shared<T> {
	private volatile T value;

	public Shared(T v) {
		value = v;
	}
	
	public T get() {
		return value;
	}

	public void set(T v) {
		value = v;
	}
}
