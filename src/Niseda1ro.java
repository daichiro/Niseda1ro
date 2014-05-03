import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * Created by mokumoku on 2014/04/28.
 */
public class Niseda1ro {
    public static final String updateText = "src/txt/statuses.txt";
    public static final String replyText = "src/txt/replyStatuses.txt";
    TimeManager timeManager;
    Update update;
    Timeline timeline;
    Twitter twitter;

    Niseda1ro() {
        twitter = TwitterFactory.getSingleton();
        timeManager = new TimeManager();
        update = new Update(twitter);
        timeline = new Timeline(twitter);
    }

    public static void main(String[] args) {
        Niseda1ro niseda1ro = new Niseda1ro();
        niseda1ro.tweet();
        niseda1ro.reply();

    }

    private void tweet() {
        if (timeManager.isTimeToTweet()) {
            update.updateStatus(null, 0);
        }
    }

    private void reply() {
        for (Status status : timeline.getReplyTimeline()) {
            if (timeManager.isRangeOfCronTime(status.getCreatedAt().getTime())) {
                update.updateMention(status);
            }
        }
    }

}
