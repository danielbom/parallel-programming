package fork_join;

import java.util.HashMap;

public class Timer {
    private static HashMap<String, Long> timers = new HashMap<String, Long>();

    public static void time(String key) {
        timers.put(key, System.nanoTime());
    }
    
    public static Long endTime(String key) {
        return System.nanoTime() - timers.get(key);
    }
}
