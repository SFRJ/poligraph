package votes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileMoodPersister implements MoodPersister {

    private final String saveDirectory;

    public FileMoodPersister(String saveDirectory) {
        this.saveDirectory = saveDirectory;
    }

    @Override
    public void save(char mood) throws Exception {
        File file = getVotesFile();
        writeToFile(mood, file);
    }

    private void writeToFile(char mood, File file) throws FileNotFoundException {
        PrintStream printStream = new PrintStream(file);
        printStream.print(mood);
    }

    private File getVotesFile() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
        String fileName = dateFormat.format(new Date()) + ".txt";
        File file = new File(saveDirectory + "/" + fileName);
        file.createNewFile();
        return file;
    }
}
