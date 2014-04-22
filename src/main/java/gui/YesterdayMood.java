package gui;

import stats.YesterdayStats;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;

@ManagedBean
@RequestScoped
public class YesterdayMood {


    public String getImageName() {
        YesterdayStats stats = new YesterdayStats();
        try {
            return stats.mood();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDate() {
        YesterdayStats stats = new YesterdayStats();
        return stats.fileName();
    }

    public void setImageName(String imageName) {

    }

    public void setDate(String date) {
    }
}
