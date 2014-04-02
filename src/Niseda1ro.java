import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
		niseda1ro.statusText = niseda1ro.selectReadOneLine("txt/statuses.txt");
		niseda1ro.updateStaticText(niseda1ro.statusText);
	}

	@SuppressWarnings("resource")
	public String selectReadOneLine(String file_name) {
		File file = new File(file_name);
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String text = bufferedReader.readLine();
			return text;
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
