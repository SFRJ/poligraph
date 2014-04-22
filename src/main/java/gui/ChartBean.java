package gui;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.apache.commons.io.FileUtils.readFileToString;

@ManagedBean
@RequestScoped
public class ChartBean implements Serializable {


    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

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

        Stats stats = Stats.create(file);
        double average = (stats.getHappyVotes() * 2 + stats.getNeutralVotes() * 1 + stats.getSadVotes() * 0) / (double) file.length();
        Date date = customDate(file);
        return new AverageAndDate(new Double(average), DATE_FORMAT.format(date));
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

        ChartSeries happyVotes = new ChartSeries();
        happyVotes.setLabel("happy votes");
        ChartSeries neutralVotes = new ChartSeries();
        neutralVotes.setLabel("neutral votes");
        ChartSeries sadVotes = new ChartSeries();
        sadVotes.setLabel("sad votes");

        List<File> files = asList(new File("votespoll").listFiles());
        sort(files);
        for (File file : files.subList(0, numberOfDays)) {
            Stats stats = Stats.create(file);
            Date date = customDate(file);
            happyVotes.set(DATE_FORMAT.format(date), stats.getHappyVotes());
            neutralVotes.set(DATE_FORMAT.format(date), stats.getNeutralVotes());
            sadVotes.set(DATE_FORMAT.format(date), stats.getSadVotes());
        }

        categoryModel.addSeries(happyVotes);
        categoryModel.addSeries(neutralVotes);
        categoryModel.addSeries(sadVotes);

        return categoryModel;
    }

    private static class Stats {
        private int happyVotes;
        private int neutralVotes;
        private int sadVotes;

        private Stats(int happyVotes, int neutralVotes, int sadVotes) {
            this.happyVotes = happyVotes;
            this.neutralVotes = neutralVotes;
            this.sadVotes = sadVotes;
        }

        public static Stats create(File file) throws IOException {
            String fileContent = readFileToString(file);
            int happyVotes = 0;
            int neutralVotes = 0;
            int sadVotes = 0;
            for (int currentCharacter = 0; currentCharacter < fileContent.length(); currentCharacter++) {
                if(fileContent.charAt(currentCharacter) == 'H')
                    happyVotes++;
                if(fileContent.charAt(currentCharacter) == 'N')
                    neutralVotes++;
                if(fileContent.charAt(currentCharacter) == 'S')
                    sadVotes++;
            }
            return new Stats(happyVotes, neutralVotes, sadVotes);
        }

        public int getHappyVotes() {
            return happyVotes;
        }

        public int getNeutralVotes() {
            return neutralVotes;
        }

        public int getSadVotes() {
            return sadVotes;
        }
    }

    private Date customDate(File currentFile) {
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy");
        return format.parse(currentFile.getName().replace(".txt",""), new ParsePosition(0));
    }
}
