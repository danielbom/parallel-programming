package fork_join.longest_asc_sub_sequence;

import java.util.Arrays;

import fork_join.Timer;
import fork_join.Utils;

public class TestLongest {
    static double[] array = Utils.randomArray(100, 50);
    
    static void testBasicLongestAscSubSequence() {
        double[] array = {5, 2, 7, 1, 4, 11, 6, 9};
        testLongestAscSubSequence(array);
    }
    
    static void testLongestAscSubSequence(double[] array) {
        Timer.time("time");
        LongestAscSubSequenceIterative c = new LongestAscSubSequenceIterative(array);
        c.run();
        double[] r1 = c.getResult();
        System.out.println(Arrays.toString(r1));
        System.out.println("Timer: " + Timer.endTime("time"));

        Timer.time("time");
        LongestAscSubSequenceFork f = new LongestAscSubSequenceFork(array);
        f.run();
        double[] r2 = f.getResult();
        System.out.println(Arrays.toString(r2));
        System.out.println("Timer: " + Timer.endTime("time"));
        
        System.out.println("Arrays is equals: " + Utils.equalsArray(r1, r2));
    }

    public static void main(String[] args) {
        testLongestAscSubSequence(array);
    }
}
