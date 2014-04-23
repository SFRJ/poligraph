package stats;

public class AverageScoreCalculator implements ScoreCalculator {

    public static final Double HAPPY_VOTE_WEIGHT = 2.0;
    public static final Double NEUTRAL_VOTE_WEIGHT = 1.0;
    public static final Double SAD_VOTE_WEIGHT = 0.0;

    @Override
    public Double calculate(DayStatistics stats) {
        final int happyVotes = stats.getHappyVotes();
        final int neutralVotes = stats.getNeutralVotes();
        final int sadVotes = stats.getSadVotes();
        final int totalVotes = happyVotes + neutralVotes + sadVotes;

        if (totalVotes == 0) {
            return 0.0;
        }

        final double weightedSum = happyVotes * HAPPY_VOTE_WEIGHT + neutralVotes * NEUTRAL_VOTE_WEIGHT + sadVotes * SAD_VOTE_WEIGHT;
        return weightedSum / totalVotes;
    }
}
