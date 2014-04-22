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

    public String fileName() {
        Date mostRecentDate = parseFromFile(getMostRecentVoteFile());
        return format(mostRecentDate);
    }

    public String mood() throws IOException {
        DayStatistics stats = fromFile(getMostRecentVoteFile());
        return resolveMood(stats.getHappyVotes(), stats.getNeutralVotes(), stats.getSadVotes());
    }

    private File getMostRecentVoteFile() {
        List<File> voteFiles = asList(new File("votespoll").listFiles());
        sort(voteFiles, fileSorter(DESC));
        return voteFiles.get(0);
    }

    private String resolveMood(int happyVotes, int neutralVotes, int sadVotes) {
        int totalVotes = happyVotes + neutralVotes + sadVotes;
        if (totalVotes == 0) return "neutralbig.jpg";
        double average = (happyVotes * 2 + neutralVotes * 1 + sadVotes * 0) / (double) totalVotes;
        return asList("sadbig.jpg", "neutralbig.jpg", "happybig.jpg").get((int) round(average));
    }
}
