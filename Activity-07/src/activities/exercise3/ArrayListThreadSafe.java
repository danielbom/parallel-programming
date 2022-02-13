package activities.exercise3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ArrayListThreadSafe<T> implements Collection<T> {
	private ReadWriteLock locker = new ReentrantReadWriteLock();
	private Lock read = locker.readLock();
	private Lock write = locker.writeLock();

	private ArrayList<T> array;

	public ArrayListThreadSafe() {
		array = new ArrayList<T>();
	}

	public ArrayListThreadSafe(int length) {
		array = new ArrayList<T>(length);
	}

	@Override
	public boolean add(T value) {
		write.lock();
		try {
			return array.add(value);
		} finally {
			write.unlock();
		}
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		write.lock();
		try {
			return array.addAll(c);
		} finally {
			write.unlock();
		}
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		write.lock();
		try {
			return array.addAll(index, c);
		} finally {
			write.unlock();
		}
	}

	@Override
	public void clear() {
		write.lock();
		array.clear();
		write.unlock();
	}

	public Object clone() {
		read.lock();
		try {
			return array.clone();
		} finally {
			read.unlock();
		}
	}

	@Override
	public boolean contains(Object o) {
		read.lock();
		try {
			return array.contains(o);
		} finally {
			read.unlock();
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		read.lock();
		try {
			return array.containsAll(c);
		} finally {
			read.unlock();
		}
	}

	public void ensureCapacity(int minCapacity) {
		write.lock();
		array.ensureCapacity(minCapacity);
		write.unlock();
	}

	public boolean equals(Object o) {
		read.lock();
		try {
			return array.equals(o);
		} finally {
			read.unlock();
		}
	}

	public void forEach(Consumer<? super T> c) {
		array.forEach(e -> {
			read.lock();
			c.accept(e);
			read.unlock();
		});
	}

	public T get(int index) {
		read.lock();
		try {
			return array.get(index);
		} finally {
			read.unlock();
		}
	}

	public int indexOf(Object o) {
		read.lock();
		try {
			return array.indexOf(o);
		} finally {
			read.unlock();
		}
	}

	@Override
	public boolean isEmpty() {
		read.lock();
		try {
			return array.isEmpty();
		} finally {
			read.unlock();
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				read.lock();
				try {
					return index < array.size();
				} finally {
					read.unlock();
				}
			}

			@Override
			public T next() {
				read.lock();
				try {
					return array.get(index);
				} finally {
					read.unlock();
				}
			}
		};
	}

	public int lasIndexOf(Object o) {
		read.lock();
		try {
			return array.lastIndexOf(o);
		} finally {
			read.unlock();
		}
	}

	@Override
	public boolean remove(Object o) {
		write.lock();
		try {
			return array.remove(o);
		} finally {
			write.unlock();
		}
	}

	public T remove(int index) {
		write.lock();
		try {
			return array.remove(index);
		} finally {
			write.unlock();
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		write.lock();
		try {
			return array.removeAll(c);
		} finally {
			write.unlock();
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) { // Difference set
		write.lock();
		try {
			return array.retainAll(c);
		} finally {
			write.unlock();
		}
	}

	public boolean removeIf(Predicate<? super T> filter) {
		write.lock();
		try {
			return array.removeIf(filter);
		} finally {
			write.unlock();
		}
	}

	public void replaceAll(UnaryOperator<T> operator) {
		write.lock();
		try {
			array.replaceAll(operator);
		} finally {
			write.unlock();
		}
	}

	public T set(int index, T value) {
		write.lock();
		try {
			return array.set(index, value);
		} finally {
			write.unlock();
		}
	}

	@Override
	public int size() {
		read.lock();
		try {
			return array.size();
		} finally {
			read.unlock();
		}
	}

	public void sort(Comparator<? super T> c) {
		read.lock();
		array.sort(c);
		read.unlock();
	}

	public List<T> subList(int fromIndex, int toIndex) {
		read.lock();
		try {
			return array.subList(fromIndex, toIndex);
		} finally {
			read.unlock();
		}
	}

	@Override
	public Object[] toArray() {
		read.lock();
		try {
			return array.toArray();
		} finally {
			read.unlock();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray(Object[] a) {
		read.lock();
		try {
			return array.toArray(a);
		} finally {
			read.unlock();
		}
	}

	@Override
	public String toString() {
		read.lock();
		try {
			return array.toString();
		} finally {
			read.unlock();
		}
	}

	public void trimToSize() {
		write.lock();
		try {
			array.trimToSize();
		} finally {
			write.unlock();
		}
	}

}
