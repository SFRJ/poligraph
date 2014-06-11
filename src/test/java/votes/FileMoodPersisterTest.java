package votes;

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static java.nio.file.Files.createTempDirectory;
import static java.nio.file.Paths.get;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileMoodPersisterTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd_MM_yyyy");
    private static final String formattedTodayDate = DATE_FORMAT.format(new Date());

    @Test
    public void itShouldSaveIntoANewVoteFileIfItIsTheFirstVoteOfTheDay() throws Exception {
        final Path tempDirectory = createTempDirectory("");
        final FileMoodPersister persister = new FileMoodPersister(tempDirectory.toString());

        persister.save('H');

        final File createdVoteFile = new File(tempDirectory.toUri()).listFiles()[0];
        assertThat(createdVoteFile.getName(), is(formattedTodayDate + ".txt"));
    }

    @Test
    public void itShouldWriteTheMoodIntoTheMoodFile() throws Exception {
        final Path tempDirectory = createTempDirectory("");
        final FileMoodPersister persister = new FileMoodPersister(tempDirectory.toString());
        persister.save('H');
        final File tempVotesFile = tempDirectory.resolve(formattedTodayDate + ".txt").toFile();
        Scanner scanner = new Scanner(tempVotesFile);
        assertThat(scanner.nextLine(), is("H"));
        Files.deleteIfExists(tempVotesFile.toPath());
    }
}