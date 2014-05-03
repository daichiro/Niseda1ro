import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Created by mokumoku on 2014/05/04.
 */
public class Timeline {
    private Twitter twitter;

    public Timeline(Twitter twitter) {
        this.twitter = twitter;
    }

    public ResponseList<Status> getTimeline() {
        ResponseList<Status> statuses;
        try {
            statuses = twitter.getHomeTimeline();
            return statuses;
        } catch (TwitterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResponseList<Status> getReplyTimeline() {
        ResponseList<Status> statuses;
        try {
            statuses = twitter.getMentionsTimeline();
            return statuses;
        } catch (TwitterException e) {
            e.printStackTrace();
            return null;
        }
    }
}
