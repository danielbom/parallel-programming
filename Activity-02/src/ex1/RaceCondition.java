package ex1;

public class RaceCondition {

	private double next = 0;
	
	double getNext() {
		this.next = this.next + 1;
		return this.next;
	}
}
