package personnal_tests;

public final class Test {

	public static void main(String[] args) {
		(new Thread() {
			public void run() {
				System.out.printf("Hello thread (%d) !!!\n", Thread.currentThread().getId());
			}
		}).start();

		System.out.printf("Hello main (%d) !\n", Thread.currentThread().getId());
	}

}
