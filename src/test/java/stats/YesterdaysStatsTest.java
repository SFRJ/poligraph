package stats;

import org.junit.Test;

public class YesterdaysStatsTest {

    @Test
    public void testFileName() throws Exception {
        YesterdaysStats yesterdaysStats = new YesterdaysStats();
        yesterdaysStats.fileName();
    }

    @Test
    public void testMood() throws Exception {
        YesterdaysStats yesterdaysStats = new YesterdaysStats();
        yesterdaysStats.mood();
    }
}
