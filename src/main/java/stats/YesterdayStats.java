package stats;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static java.lang.Math.round;
import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static stats.DayStatistics.fromFile;
import static utils.DateUtils.SortOrder.DESC;
import static utils.DateUtils.*;

public class YesterdayStats {

    private static final String NEUTRAL_IMAGE_BIG = "neutralbig.jpg";
    private static final String SAD_IMAGE_BIG = "sadbig.jpg";
    private static final String HAPPY_IMAGE_BIG = "happybig.jpg";

    private final ScoreCalculator scoreCalculator;

    public YesterdayStats(ScoreCalculator scoreCalculator) {
        this.scoreCalculator = scoreCalculator;
    }

    public String fileName() {
        Date mostRecentDate = parseFromFile(getMostRecentVoteFile());
        return format(mostRecentDate);
    }

    public String mood() throws IOException {
        DayStatistics stats = fromFile(getMostRecentVoteFile());
        return resolveMood(stats);
    }

    private String resolveMood(DayStatistics stats) {
        if (!stats.hasVotes()) {
            return NEUTRAL_IMAGE_BIG;
        }
        final Double score = scoreCalculator.calculate(stats);
        return asList(SAD_IMAGE_BIG, NEUTRAL_IMAGE_BIG, HAPPY_IMAGE_BIG).get((int) round(score));
    }

    private File getMostRecentVoteFile() {
        List<File> voteFiles = asList(new File("votespoll").listFiles());
        sort(voteFiles, fileSorter(DESC));
        return voteFiles.get(0);
    }
}
