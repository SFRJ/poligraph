package votes;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;

public class VoteValidator {

    public static boolean validateAlreadyVoted(String email) throws IOException {
        File alreadyVotedFile = new File("alreadyvoted.txt");
        return readFileToString(alreadyVotedFile).contains(email);
    }
}
