package stencil1D;

import java.util.Iterator;

class Interval {
	public final int begin;
	public final int end;
	
	public Interval(int b, int e) {
		begin = b;
		end = e;
	}
	
	@Override
	public String toString() {
		return "[" + begin + "," + end + ")";
	}
}

public class Partition implements Iterable<Interval> {
	private int length;
	private int interval;
	private int last;
	
	public Partition(int length, int parts) {
		if (length < parts)
			throw new IllegalArgumentException("Length must be 'greater' then 'parts'. length(" + length + ") parts(" + parts + ")");
		this.length = length;
		this.interval = length / parts;
		this.last = parts - 1;
	}

	@Override
	public Iterator<Interval> iterator() {
		return new Iterator<Interval>() {
			int i = 0;

			@Override
			public boolean hasNext() {
				return i <= last;
			}

			@Override
			public Interval next() {
				int k = i++;
				int first = k * interval;
				int last = Math.min(first + interval, length);
				return new Interval(first, last);
			}
		};
	}
}
