package stats;

import java.io.File;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Math.round;
import static java.util.Arrays.asList;
import static org.apache.commons.io.FileUtils.readFileToString;

public class YesterdaysStats {

    public String fileName() {
        File[] votespolls = new File("votespoll").listFiles();
        return latestDate(sortFiles(asList(votespolls)));
    }

    public String mood() throws IOException {
        File[] votespolls = new File("votespoll").listFiles();
        String fileContent = readFileToString(sortFiles(asList(votespolls)).get(0));
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
        return resolveMood(happyVotes, neutralVotes, sadVotes);
    }

    private List<File> sortFiles(List<File> files) {
        Collections.sort(files, new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                Date dateFile1 = customDate(file1);
                Date dateFile2 = customDate(file2);
                return dateFile2.compareTo(dateFile1);
            }
        });
        return files;
    }

    private String latestDate(List<File> files) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(customDate(files.get(0)));
    }

    private String resolveMood(int happyVotes, int neutralVotes, int sadVotes) {
        int totalVotes = happyVotes + neutralVotes + sadVotes;
        if (totalVotes == 0) return "neutralbig.jpg";
        double average = (happyVotes * 2 + neutralVotes * 1 + sadVotes * 0) / (double) totalVotes;
        return asList("sadbig.jpg", "neutralbig.jpg", "happybig.jpg").get((int) round(average));
    }

    private Date customDate(File currentFile) {
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy");
        return format.parse(currentFile.getName().replace(".txt",""), new ParsePosition(0));
    }
}
