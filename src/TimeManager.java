import java.util.Calendar;

/**
 * Created by mokumoku on 2014/04/28.
 */
public class TimeManager {
    private Calendar calendar;
    private int ap;
    private int hour;
    private int minute;
    private static final int CRON = 2; // MINUTES
    private static final long CRONTIME = CRON * 60 * 1000; // MillSec

    TimeManager() {
        calendar = Calendar.getInstance();
        initialize();
    }

    private void initialize() {
        ap = calendar.get(Calendar.AM_PM);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
    }

    public boolean isTimeToTweet() {
        if (!(ap == 0) || (!(hour > 4) || !(hour < 10))) {
            if (minute % 30 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isRangeOfCronTime(long statusTime) {
        if (calendar.getTimeInMillis() - statusTime < CRONTIME) {
            return true;
        } else {
            return false;
        }
    }
}
