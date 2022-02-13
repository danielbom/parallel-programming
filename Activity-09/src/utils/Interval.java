package utils;

public class Interval {
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
