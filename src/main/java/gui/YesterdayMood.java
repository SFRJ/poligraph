package gui;

import stats.YesterdaysStats;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;

@ManagedBean
@RequestScoped
public class YesterdayMood {


    public String getImageName() {
        YesterdaysStats stats = new YesterdaysStats();
        try {
            return stats.mood();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDate() {
        YesterdaysStats stats = new YesterdaysStats();
        return stats.fileName();
    }

    public void setImageName(String imageName) {

    }

    public void setDate(String date) {
    }
}
