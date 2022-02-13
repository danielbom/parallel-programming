package stencil1D;

public class TestPartition {
    static void basic() {
	Partition p = new Partition(1000, 6);

	for (int i = 0; i < 6; i++) {
	    System.out.println(p.getFirstIndex(i) + " " + p.getLastIndex(i));
	}

	for (Interval i : p) {
	    System.out.println(i.begin + " " + i.end);
	}
    }

    public static void main(String[] args) {
	basic();
    }
}
