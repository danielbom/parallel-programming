package exercise5;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecuteInDate implements Runnable {

    private Runnable task;
    private Date date;

    public ExecuteInDate(Runnable task, Date date) {
        this.task = task;
        this.date = date;
    }

    @Override
    public void run() {
        ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();
        Date now = new Date();
        System.out.println(now);
        System.out.println(date);

        // https://www.baeldung.com/java-date-difference

        long diffInMillies = this.date.getTime() - now.getTime();
        long diff = TimeUnit.SECONDS.convert(diffInMillies,
                TimeUnit.MILLISECONDS);

        System.out.println(diff);
        schedule.schedule(task, diff, TimeUnit.SECONDS);
        schedule.shutdown();
    }
}
