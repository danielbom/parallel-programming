package fork_join;

public class TestSorts {
    static double[] array = Utils.randomArray(100, 50);
    
    
    static void testMergeForks(double[] array) {
        Timer.time("time");
        MergeSortWithForks m = new MergeSortWithForks(array);
        m.run();
        String sorted = !Utils.isSorted(m.getArray()) ? " NOT " : " ";
        System.out.println("Is" + sorted + "sorted timer: " + Timer.endTime("time"));
    }
    
    static void testQuickForks(double[] array) {
        Timer.time("time");
        QuickSortWithFort q = new QuickSortWithFort(array);
        q.run();
        String sorted = !Utils.isSorted(q.getArray()) ? " NOT " : " ";
        System.out.println("Is" + sorted + "sorted timer: " + Timer.endTime("time"));
    }
    
    static void testSorts(double[] array) {
        // ForkJoin is good close or greater to 10_000_000
        testMergeForks(array);
        testQuickForks(array);
    }

    public static void main(String[] args) {
        testSorts(array);
    }
}
