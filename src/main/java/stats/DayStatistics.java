package stats;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.apache.commons.io.FileUtils.readFileToString;
import static utils.DateUtils.parseFromFile;

public class DayStatistics {
    private Date day;
    private int happyVotes;
    private int neutralVotes;
    private int sadVotes;

    public DayStatistics(Date day, int happyVotes, int neutralVotes, int sadVotes) {
        this.day = day;
        this.happyVotes = happyVotes;
        this.neutralVotes = neutralVotes;
        this.sadVotes = sadVotes;
    }

    public static DayStatistics fromFile(File file) throws IOException {
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
        return new DayStatistics(parseFromFile(file), happyVotes, neutralVotes, sadVotes);
    }

    public Date getDay() {
        return day;
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
