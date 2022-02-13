package ex1;

public class BasicRaceCondition implements Runnable {

	private RaceCondition race;

	BasicRaceCondition(RaceCondition race) {
		this.race = race;
	}

	public void run() {		
		for (long i = 0; i < 3_000_000; i++)
			this.race.getNext();
	}
}
