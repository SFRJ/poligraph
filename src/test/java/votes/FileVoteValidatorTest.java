package votes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.deleteIfExists;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FileVoteValidatorTest {

    private static final boolean ALLOWED_TO_VOTE = true;
    private static final boolean NOT_ALLOWED_TO_VOTE = false;
    private File testFile;

    @Before
    public void setupTestFile() throws IOException {
        testFile = createTempFile("", ".txt").toFile();
        PrintStream printStream = new PrintStream(testFile);
        printStream.println("djordje@hotmail.com");
    }

    @After
    public void cleanupTestFile() throws IOException {
        deleteIfExists(testFile.toPath());
    }

    @Test
    public void shouldBeAllowedToVoteWhenTheFileDoesNotContainTheUsersEmail() throws Exception {
        FileVoteValidator fileVoteValidator = new FileVoteValidator(testFile);
        assertThat(fileVoteValidator.canVote("enrico@hotmail.com"), is(ALLOWED_TO_VOTE));
    }

    @Test
    public void shouldNotBeAllowedToVoteWhenTheFileContainTheUsersEmail() throws Exception {
        FileVoteValidator fileVoteValidator = new FileVoteValidator(testFile);
        assertThat(fileVoteValidator.canVote("djordje@hotmail.com"), is(NOT_ALLOWED_TO_VOTE));
    }
}