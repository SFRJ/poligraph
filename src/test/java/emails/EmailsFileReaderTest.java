package emails;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class EmailsFileReaderTest {

    public static final File EMAILS_FILE = new File("src/test/resources/sampleFakeEmailsFile.txt");

    @Test
    public void theListShouldContainSomeEmailsWhenLoaded() throws IOException {
            EmailsFileReader emailsFileReader = new EmailsFileReader(EMAILS_FILE);
            List<String> emails = emailsFileReader.loadMailList();
            assertTrue(emails.size() > 0);
    }
}
