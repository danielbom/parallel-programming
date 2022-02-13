package activities.exercise3;

import java.util.Arrays;

/**
 * Faça uma classe ArrayListThreadSafe usando ReadWriteLock. Teste usando
 * threads que realizam leitura e escrita para essa estrutura.
 * 
 * @author daniel
 */
public class Exercise3 {
	public static void main(String[] args) {
		ArrayListThreadSafe<Integer> numbers = new ArrayListThreadSafe<Integer>();
		ArrayListThreadSafe<Integer> numbers2 = new ArrayListThreadSafe<Integer>();
		
		
		for (int i = 0; i < 10; i++) {
			numbers.add(i);
			numbers2.add(i + 5);
		}
		
		Integer[] array = new Integer[15];
		array = (Integer[]) numbers.toArray(array);
		System.out.println(Arrays.toString(array));
		
		System.out.println(numbers);
		System.out.println(numbers2);
		numbers.retainAll(numbers2); // Difference set
		System.out.println(numbers);
	}
}
