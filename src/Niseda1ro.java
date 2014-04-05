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
	Twitter twitter;
	String statusText;
	Niseda1ro() {
		this.twitter = TwitterFactory.getSingleton();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Niseda1ro niseda1ro = new Niseda1ro();
		if (niseda1ro.isTimeToTweet()) {
			niseda1ro.statusText = niseda1ro.selectReadOneLine("txt/statuses.txt");
			niseda1ro.updateStaticText(niseda1ro.statusText);
		}
	}

	public boolean isTimeToTweet() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		if (!(hour>4) && (hour<10)) {
			int minute = calendar.get(Calendar.MINUTE);
			if ((minute==0) || (minute==1) || (minute==30) || (minute==31))
				return true;
		}
		return false;
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
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}

	public void updateStaticText(String text) {
		try {
			this.twitter.updateStatus(text);
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
