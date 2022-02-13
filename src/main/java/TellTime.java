import java.util.Calendar;

/**
 * @author saich
 *
 */
public class TellTime {
	
	/**
	 * @param a
	 * @throws InterruptedException
	 */
	public static void main(String a[]) throws InterruptedException {
		while (true) {
			Calendar nowTime = Calendar.getInstance();
			int hour = nowTime.get(Calendar.HOUR_OF_DAY);
			int minute = nowTime.get(Calendar.MINUTE);
			boolean isNight = false;
			if (hour > 6 && hour < 23) {
				if (hour > 12) {
					hour = hour - 12;
					isNight = true;
				}
				String timeStr = "Now the time is " + hour + " " + minute + (isNight ? " PM" : " AM");
				TextToSpeech.toSpeak(timeStr);
				Thread.sleep(30 * 60 * 1000);
			}
		}
	}
}
