package activity3;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * MergeSort paralelo. A profundidade é igual a raiz quadrada da quantidade de
 * núcleos da máquina. Este valor é usado para saber quantas threads são 
 * suficientes. O tamanho do intervalo é usado para decidir se a recursão é
 * necessária ainda. Se não, é aplicado o ShellSort e o InsertionSort para 
 * ordenar intervalos pequenos.
 * 
 * @author daniel
 */
class MergeSort<T> implements Runnable {
	private T[] array;
	private int end;
	private int begin;
	private int depth;
	private int maxDepth;
	private Comparator<T> comparator;

	public MergeSort(T[] array, int begin, int end, Comparator<T> comparator) {
		this(array, begin, end, comparator, 0);
	}
	
	public MergeSort(T[] array, int begin, int end, Comparator<T> comparator, int depth) {
		this.comparator = comparator;
		this.array = array;
		this.begin = begin;
		this.end = end;
		this.depth = depth;
		this.maxDepth = (int) Math.sqrt(Runtime.getRuntime().availableProcessors());
		System.out.printf(">>> New MergeSort argumentos [%d,%d] Cores (%d)\n", begin, end, maxDepth);
	}
	
	@Override
	public void run() {
		if (this.begin + 2048 > this.end) {
			this.shellSort();
			this.insertionSort();
		} else {
			this.mergeSort();
		}
	}
	
	private void mergeSortThreads(MergeSort<T> left, MergeSort<T> right) {
		System.out.println(">> [THREAD] Invocando threads para cada parte do array.");
		ArrayList<Thread> threads = new ArrayList<Thread>(2);
		threads.add(new Thread(left));
		threads.add(new Thread(right));
		
		for (Thread t : threads)
			t.start();

		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				System.out.println("Uma thread foi interrompida na classe MergeSort");
			}
		}
	}
	
	private void mergeSort() {
		int middle = (begin + end) / 2;
		MergeSort<T> left = new MergeSort<T>(array, begin, middle, comparator, this.depth + 1);
		MergeSort<T> right = new MergeSort<T>(array, middle+1, end, comparator, this.depth + 1);
		if (this.depth < this.maxDepth) {
			this.mergeSortThreads(left, right);
		} else {
			System.out.println(">> [NO THREAD] Fazendo chamada recursiva para o mergesort");
			left.run();
			right.run();
		}
		this.merge();
	}

	private void merge() {
		System.out.printf("> Intervalo Merge [%d,%d] Tamanho (%d)\n", begin, end, end - begin);
		int middle = (begin + end) / 2;
		ArrayList<T> auxiliar = new ArrayList<T>((end - begin) + 1);
		int i = begin;
		int j = middle + 1;

		while (i <= middle && j <= end) {
			if (comparator.compare(array[i], array[j]) <= 0) {
				auxiliar.add(this.array[i++]);
			} else {
				auxiliar.add(this.array[j++]);
			}
		}
		while (i <= middle) {
			auxiliar.add(this.array[i++]);
		}
		while (j <= end) {
			auxiliar.add(this.array[j++]);
		}
		
		int k = 0;
		for (i = begin; i <= end; i++, k++) {
			this.array[i] = auxiliar.get(k);
		}
	}

	private void shellSort() {
		System.out.println("> ShellSort");
		int length = end - begin;
		int h = 1;
		
		while (h < length)
			h = h * 3 + 1;
		
		int j;
		for (h /= 3; h > 16; h /= 2) {
			for (int i = begin + h; i <= end; i++) {
				T elem = array[i];
				
				for (j = i - h; j >= begin && comparator.compare(elem, array[j]) < 0; j -= h)
					array[j + h] = array[j];
				
				array[j + h] = elem;
			}
		}
	}
	
	private void insertionSort() {
		System.out.println("> InsertionSort");
		int i, j;
		for (i = begin + 1; i <= end; i++) {
			T elem = array[i];
			
			for (j = i - 1; j >= begin && comparator.compare(elem, array[j]) < 0; j--)
				array[j + 1] = array[j];

			array[j + 1] = elem;
		}
	}
}