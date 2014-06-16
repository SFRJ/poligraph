package votes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static utils.DateUtils.currentDateAsString;


public class VotePoll {

    private final File ALREADY_VOTED_FILE;
    private final VoteValidator voteValidator;
    private final File VOTES_POLL_FILES_DIRECTORY = new File("votespoll");
    private final File TODAYS_VOTES_FILE;

    public VotePoll() {
        ALREADY_VOTED_FILE = new File("alreadyvoted.txt");
        voteValidator = new FileVoteValidator(ALREADY_VOTED_FILE);
        TODAYS_VOTES_FILE = new File(VOTES_POLL_FILES_DIRECTORY.getName() + "/" + currentDateAsString() + ".txt");
    }

    public String castVote(Vote vote) throws IOException {
        if (voteValidator.canVote(vote.getEmail()) && VOTES_POLL_FILES_DIRECTORY.exists()) {
                writeVotesToFile(vote.getMood());
                addEmailToAlreadyVotedFile(vote.getEmail());
                return "Thanks for your feedback!";
        } else {
            throw new AlreadyVotedException(vote.getEmail() + " already voted today");
        }
    }

    private void writeVotesToFile(char mood) throws IOException {
        FileWriter fileWriter = new FileWriter(TODAYS_VOTES_FILE,true);
        fileWriter.write(mood);
        fileWriter.close();
    }

    private void addEmailToAlreadyVotedFile(String email) throws IOException {
        FileWriter fileWriter = new FileWriter(ALREADY_VOTED_FILE,true);
        fileWriter.write(email + System.lineSeparator());
        fileWriter.close();
    }
}
