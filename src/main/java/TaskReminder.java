import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TaskReminder {

	public static void main(String a[]) {
		Scanner taskListFileScanner = null;
		File logFile = new File("/home/pi/Documents/workspace/java-labs/text-2-speech/logs/TaskReminder_log_"
				+ Calendar.getInstance().getTimeInMillis());
		try {
			if (logFile.createNewFile()) {
				writeLogs(logFile, "New File Created..");
			}

			logFile.createNewFile();
			File taskListFile = new File("/home/pi/Documents/workspace/java-labs/text-2-speech/test_data");

			Map<Integer, List<Task>> taskReminderByStartHour = new HashMap<Integer, List<Task>>();

			if (taskListFile.exists()) {
				taskListFileScanner = new Scanner(taskListFile);
				while (taskListFileScanner.hasNextLine()) {
					String taskRecord = taskListFileScanner.nextLine();
					String taskDetails[] = taskRecord.split(",");
					writeLogs(logFile, "New Task: " + taskRecord);
					Integer startHour = Integer.valueOf(taskDetails[0]);
					Task task = new Task(startHour, Integer.valueOf(taskDetails[1]), taskDetails[2], taskDetails[3]);
					if (taskReminderByStartHour.containsKey(startHour)) {
						taskReminderByStartHour.get(startHour).add(task);
					} else {
						List<Task> tasks = new ArrayList<Task>();
						tasks.add(task);
						taskReminderByStartHour.put(startHour, tasks);
					}
				}
			} else {
			}

			while (true) {
				Calendar nowTime = Calendar.getInstance();
				int hour = nowTime.get(Calendar.HOUR_OF_DAY);
				int minute = nowTime.get(Calendar.MINUTE);

				if (taskReminderByStartHour.containsKey(hour)) {
					writeLogs(logFile, "Task found for the hour: " + hour);
					List<Task> tasks = taskReminderByStartHour.get(hour);
					for (Task task : tasks) {
						if ((task.getDismiss() == null || !task.getDismiss()) && hour == task.getStartTimeHour()) {
							if (minute == task.getStartTimeMinute()) {
								writeLogs(logFile, "Task found for the minute: " + task.getReminderDescription());
								task.setDismiss(true);
								TextToSpeech
										.toSpeak("Hi " + task.getPersonName() + " " + task.getReminderDescription());
							}
						}
					}
					writeLogs(logFile, "Task processing over for the minute: " + minute);
					int second = nowTime.get(Calendar.SECOND);
					Thread.sleep(((61 - second) * 1000));
				} else {
					writeLogs(logFile, "Task not found for the hour: " + hour);
					Thread.sleep((60 - minute) * 60 * 1000);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (taskListFileScanner != null) {
				taskListFileScanner.close();
			}
		}
	}

	/**
	 * @param logFile
	 * @param content
	 * @throws IOException
	 */
	private static void writeLogs(final File logFile, final String content) throws IOException {
		Calendar nowTime = Calendar.getInstance();

		int hour = nowTime.get(Calendar.HOUR_OF_DAY);
		int minute = nowTime.get(Calendar.MINUTE);

		BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
		writer.append(hour + ":" + minute + ":: " + content);
		writer.close();
	}
}
