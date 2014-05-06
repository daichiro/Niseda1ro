import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mokumoku on 2014/04/28.
 */
public class Update {
    private Twitter twitter;
    Update(Twitter twitter) {
        this.twitter = twitter;
    }

    public void updateStatus(String status, long inReplyToId) {
        if (inReplyToId == 0) {
            status = selectOneLine(Niseda1ro.updateText);
        } else {
            status += selectOneLine(Niseda1ro.replyText);
        }
        try {
            twitter.updateStatus(new StatusUpdate(status).inReplyToStatusId(inReplyToId));
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    public void updateMention(Status status) {
        String string = "@" + status.getUser().getScreenName() + " ";
        updateStatus(string, status.getId());
    }

    private String selectOneLine(String fileName) {
        File file = new File(fileName);
        ArrayList<String> texts = new ArrayList<String>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                texts.add(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int selectedLine = new Random().nextInt(texts.size());
        return texts.get(selectedLine);
    }
}
