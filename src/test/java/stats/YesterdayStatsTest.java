package stats;

import org.junit.Test;

public class YesterdayStatsTest {

    @Test
    public void testFileName() throws Exception {
        YesterdayStats yesterdayStats = new YesterdayStats(new AverageScoreCalculator());
        yesterdayStats.fileName();
    }

    @Test
    public void testMood() throws Exception {
        YesterdayStats yesterdayStats = new YesterdayStats(new AverageScoreCalculator());
        yesterdayStats.mood();
    }
}
