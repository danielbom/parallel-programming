package personnal_tests;

import java.util.Arrays;
import java.util.List;

public class StreamTest {
	public static void main(String[] args) {
		List<String> birds = Arrays.asList("Robin", "Blujay", "Penguin", "Ostrich", "Canary");

		for (String bird : birds)
			System.out.println(bird);
		System.out.println();
		
		birds.stream().forEach(System.out::println);
		System.out.println();
		
		birds.parallelStream().forEach(System.out::println);
		System.out.println();
	}
}
