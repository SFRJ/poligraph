package emails;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

import static java.util.Calendar.*;

public final class EmailSendingScheduler extends TimerTask implements Serializable {

    static {
        scheduleEmailSending();
    }

    //1000 miliseconds in 1 second X 60 seconds X 60 minutes X 24 hours
    private final static long ONCE_PER_DAY = 1000 * 60 * 60 * 24;

    private static void scheduleEmailSending() {
        TimerTask fetchMail = new EmailSendingScheduler();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(fetchMail, at5pm(), ONCE_PER_DAY);
    }

    private static Date at5pm() {
        //TODO Make this be compatible with systems that dont use the 24h time format
        //TODO externalize the scheduled time to a config file so it can be changed
        int hour = 17;
        int minute = 00;

        Calendar today = new GregorianCalendar();
        today.add(DATE, 0);
        Calendar result = new GregorianCalendar(
                today.get(YEAR),
                today.get(MONTH),
                today.get(DATE),
                hour,
                minute
        );
        return result.getTime();
    }

    //Just leave this here in case you want to schedule in 60 seconds for testing purposes
    private static Date after60Seconds() {
        long timeIn6Seconds = new Date().getTime() + (1000 * 60);
        return new Date(timeIn6Seconds);
    }

    @Override
    public void run() {
        //TODO Application currently will need a restart if new emails are added to the file, try to improve that.
        if(isWeekend()) return;
        try {
            cleanupvotingblacklist();
            EmailsFileReader emailsFileReader = new EmailsFileReader(new File("emails.txt"));
            EmailSender emailSender = new EmailSender(emailsFileReader);
            emailSender.sendEmails();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isWeekend() {
        Calendar gregorianCalendar = new GregorianCalendar();
        int dayOfTheWeek = gregorianCalendar.get(DAY_OF_WEEK);
        return dayOfTheWeek == SATURDAY || dayOfTheWeek == SUNDAY;
    }

    private void cleanupvotingblacklist() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File("alreadyvoted.txt"));
        printWriter.print("");
        printWriter.close();
    }
}