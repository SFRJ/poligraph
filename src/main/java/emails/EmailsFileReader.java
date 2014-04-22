package emails;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.lang.System.lineSeparator;
import static java.util.Arrays.asList;
import static org.apache.commons.io.FileUtils.readFileToString;

public class EmailsFileReader {

    private File emailsFile;

    public EmailsFileReader(File emailsFile) {
        this.emailsFile = emailsFile;
    }

    public List<String> loadMailList() throws IOException {
        return asList(readFileToString(emailsFile).split(lineSeparator()));
    }
}
