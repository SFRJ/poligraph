package gui;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import stats.DayStatistics;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static utils.DateUtils.format;
import static utils.DateUtils.parseFromFile;

@ManagedBean
@RequestScoped
public class ChartBean implements Serializable {

    public ChartBean() {
    }

    public CartesianChartModel linearModel(int numberOfDays) throws IOException {
        return createLinearModel(numberOfDays);
    }

    public CartesianChartModel categoryModel(int numberOfDays) throws IOException {
        return createCategoryModel(numberOfDays);
    }

    private CartesianChartModel createLinearModel(int numberOfDays) throws IOException {
        List<AverageAndDate> averages = getAverages(numberOfDays);
        CartesianChartModel linearModel = new CartesianChartModel();
        LineChartSeries moodSerie = new LineChartSeries();
        moodSerie.setLabel("Mood");

        for (int index = 0; index < averages.size(); index++) {
            moodSerie.set(averages.get(index).getDate(), averages.get(index).getAverage());
        }

        linearModel.addSeries(moodSerie);

        return linearModel;
    }

    private List<AverageAndDate> getAverages(int numberOfDays) throws IOException {
        List<File> files = asList(new File("votespoll").listFiles());
        sort(files);
        List<AverageAndDate> averages = new ArrayList<>();
        for (File file : files.subList(0, numberOfDays)) {
            averages.add(calculateAverage(file));
        }
        return averages;
    }

    private AverageAndDate calculateAverage(File file) throws IOException {
        DayStatistics stats = DayStatistics.fromFile(file);
        double average = (stats.getHappyVotes() * 2 + stats.getNeutralVotes() * 1 + stats.getSadVotes() * 0) / (double) file.length();
        return new AverageAndDate(new Double(average), format(stats.getDay()));
    }

    private static class AverageAndDate {
        private Number average;
        private String date;

        private AverageAndDate(Number average, String date) {
            this.average = average;
            this.date = date;
        }

        public Number getAverage() {
            return average;
        }

        public String getDate() {
            return date;
        }
    }

    private CartesianChartModel createCategoryModel(int numberOfDays) throws IOException {
        CartesianChartModel categoryModel = new CartesianChartModel();

        ChartSeries happyVotes = new ChartSeries("Happy votes");
        ChartSeries neutralVotes = new ChartSeries("Neutral votes");
        ChartSeries sadVotes = new ChartSeries("Sad votes");

        List<File> files = asList(new File("votespoll").listFiles());
        sort(files);
        for (File file : files.subList(0, numberOfDays)) {
            DayStatistics stats = DayStatistics.fromFile(file);
            Date date = parseFromFile(file);
            happyVotes.set(format(date), stats.getHappyVotes());
            neutralVotes.set(format(date), stats.getNeutralVotes());
            sadVotes.set(format(date), stats.getSadVotes());
        }

        categoryModel.addSeries(happyVotes);
        categoryModel.addSeries(neutralVotes);
        categoryModel.addSeries(sadVotes);

        return categoryModel;
    }

}
