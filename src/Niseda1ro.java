import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import twitter4j.*;

public class Niseda1ro {
	public static final int cronTime = 2;
	Twitter twitter;
	String statusText;
	private Calendar calendar;
	static Niseda1ro niseda1ro;

	Niseda1ro() {
		this.twitter = TwitterFactory.getSingleton();
		this.calendar = Calendar.getInstance();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		niseda1ro = new Niseda1ro();
		if (niseda1ro.isTimeToTweet()) {
			niseda1ro.statusText = niseda1ro.selectReadOneLine("src/txt/statuses.txt");
			niseda1ro.updateStaticText(niseda1ro.statusText, 0);
		}
		niseda1ro.reply();
	}

	public boolean isTimeToTweet() {
		int hour = niseda1ro.calendar.get(Calendar.HOUR);
        int ap = niseda1ro.calendar.get(Calendar.AM_PM);
		if (!(ap == 0) ||
            !((hour>4) && (hour<10))) {
			int minute = niseda1ro.calendar.get(Calendar.MINUTE);
			if ((minute==0) || (minute==1) || (minute==30) || (minute==31))
				return true;
		}
		return false;
	}

	public void reply() {
		try {
			ResponseList<Status> replies = niseda1ro.twitter.getMentionsTimeline();
			long time = cronTime * 60 * 1000;
			for (Status status : replies) {
				if ((calendar.getTimeInMillis() - status.getCreatedAt().getTime()) > time)
					continue;
				String replyText = "@" + status.getUser().getScreenName() + " ";
				replyText += niseda1ro.selectReadOneLine("src/txt/replyStatuses.txt");
				niseda1ro.updateStaticText(replyText, status.getId());
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public String selectReadOneLine(String file_name) {
		File file = new File(file_name);
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			ArrayList<String> statuses = new ArrayList<String>();
			String text = null;
			while((text = bufferedReader.readLine()) != null)
				statuses.add(text);
			Random rnd = new Random();
			int selectedLine = rnd.nextInt(statuses.size());
			return statuses.get(selectedLine);
		} catch (FileNotFoundException e) {
            e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateStaticText(String text, long inReplyToId) {
		try {
			this.twitter.updateStatus(new StatusUpdate(text).inReplyToStatusId(inReplyToId));
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
