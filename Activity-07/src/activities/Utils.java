package activities;

public class Utils {
	private Utils() {
	}
	
	public static long randomSleep(int maxTime) throws InterruptedException {
		long time = (long) Math.floor(Math.random() * maxTime);
		Thread.sleep(time);
		return time;
	}
}
