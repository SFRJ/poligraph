package stats;

import org.junit.Test;

public class YesterdayStatsTest {

    @Test
    public void testFileName() throws Exception {
        YesterdayStats yesterdayStats = new YesterdayStats();
        yesterdayStats.fileName();
    }

    @Test
    public void testMood() throws Exception {
        YesterdayStats yesterdayStats = new YesterdayStats();
        yesterdayStats.mood();
    }
}
