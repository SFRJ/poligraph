package gui;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@RequestScoped
@ApplicationScoped
public class TabViewChange /*extends TimerTask*/{

//    @PostConstruct
//    public void initScheduler() {
//        TimerTask fetchMail = new TabViewChange();
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(fetchMail,after30Seconds(),5000);
//    }

    private int tabIndex;

//    private static Date after30Seconds(){
//        long timeIn6Seconds = new Date().getTime() + (1000 * 20);
//        return new Date(timeIn6Seconds);
//    }

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

//    @Override
//    public void run() {
//        if(getTabIndex() > 5) {
//            setTabIndex(0);
//        }
//        tabIndex++;
//    }
}
