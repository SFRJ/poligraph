package votes;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;

public class FileVoteValidator implements VoteValidator {

    private File emailsFile;

    public FileVoteValidator(File emailsFile) {
        this.emailsFile = emailsFile;
    }

    @Override
    public boolean canVote(String email) throws IOException {
        return !readFileToString(emailsFile).contains(email);
    }
}
