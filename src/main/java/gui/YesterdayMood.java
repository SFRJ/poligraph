package gui;

import stats.AverageScoreCalculator;
import stats.YesterdayStats;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;

@ManagedBean
@RequestScoped
public class YesterdayMood {

    private final YesterdayStats stats = new YesterdayStats(new AverageScoreCalculator());

    public String getImageName() {
        try {
            return stats.mood();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDate() {
        return stats.fileName();
    }

    public void setImageName(String imageName) {

    }

    public void setDate(String date) {
    }
}
