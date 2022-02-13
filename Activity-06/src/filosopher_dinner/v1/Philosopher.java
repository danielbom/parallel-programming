package filosopher_dinner.v1;

public class Philosopher implements Runnable {

	private Fork left, right;
	private String name;
	private int counter, fixTime;

	public Philosopher(Fork l, Fork r, int time) {
		left = l;
		right = r;
		fixTime = time;
		counter = 0;
	}
	
	public Philosopher(Fork left, Fork right) {
		this(left, right, -1);
	}
	
	private void think() throws InterruptedException {
		int time = fixTime > 0 ? fixTime : (int) (Math.random() * 10000);
		Thread.sleep(time);
		System.out.println(name + " Counter[" + counter + "] pensando Tempo(" + time + ") " + this);
	}
	
	public void eat() throws InterruptedException {
		int time = fixTime > 0 ? fixTime : (int) (Math.random() * 10000);
		left.use();
		right.use();
		counter++;
		Thread.sleep(time);
		System.out.println(name + " Counter[" + counter + "] comendo  Tempo(" + time + ") " + this);
		if (counter % 5 == 0)
			System.out.println();
		right.discard();
		left.discard();
	}

	@Override
	public void run() {
		this.name = Thread.currentThread().getName();
		while (true) {
			try {
				think();
				eat();
				if (Math.random() > 0.5) {
					think();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public String toString() {
		return "Philosopher[" + left + "|" + right + "]";
	}

}
