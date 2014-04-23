package stats;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AverageScoreCalculatorTest {

    @Test
    public void shouldProduceScoreAsTheWeightedAverage() {
        final AverageScoreCalculator calculator = new AverageScoreCalculator();

        final Double average = calculator.calculate(new DayStatistics(new Date(), 1, 1, 1));

        assertThat(average, is(1.0));
    }

    @Test
    public void shouldProduceCorrectAverageForJustHappyVotes() {
        final AverageScoreCalculator calculator = new AverageScoreCalculator();

        final Double average = calculator.calculate(new DayStatistics(new Date(), 4, 0, 0));

        assertThat(average, is(2.0));
    }

    @Test
    public void shouldProduceCorrectAverageForJustNeutralVotes() {
        final AverageScoreCalculator calculator = new AverageScoreCalculator();

        final Double average = calculator.calculate(new DayStatistics(new Date(), 0, 3, 0));

        assertThat(average, is(1.0));
    }

    @Test
    public void shouldProduceCorrectAverageForJustSadVotes() {
        final AverageScoreCalculator calculator = new AverageScoreCalculator();

        final Double average = calculator.calculate(new DayStatistics(new Date(), 0, 0, 5));

        assertThat(average, is(0.0));
    }

    @Test
    public void averageOfZeroVotesShouldBeZero() {
        final AverageScoreCalculator calculator = new AverageScoreCalculator();

        final Double average = calculator.calculate(new DayStatistics(new Date(), 0, 0, 0));

        assertThat(average, is(0.0));
    }
}