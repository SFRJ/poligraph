package votes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.DateUtils.currentDateAsString;


public class VotePoll {

    private final File alreadyVotedFile;
    private final VoteValidator voteValidator;
    private final File VOTES_POLL_FILES_DIRECTORY = new File("votespoll");
    private final File todaysVotesFile;

    public VotePoll() {
        alreadyVotedFile = new File("alreadyvoted.txt");
        voteValidator = new FileVoteValidator(alreadyVotedFile);
        todaysVotesFile = todaysVotingFile(new File(VOTES_POLL_FILES_DIRECTORY.getName() + "/" + currentDateAsString() + ".txt"));
    }

    public String castVote(Vote vote) throws Exception {
        if (voteValidator.canVote(vote.getEmail())) {
            if (VOTES_POLL_FILES_DIRECTORY.exists()) {
                writeVotesToFile(todaysVotesFile, vote.getMood());
                addEmailToAlreadyVotedFile(vote.getEmail());
            }
            return "Thanks for your feedback!";
        } else {
            throw new AlreadyVotedException(vote.getEmail() + " already voted today");
        }
    }

    private File todaysVotingFile(File todaysVotingFile) {
        if (!todaysVotingFile.exists()) {
            todaysVotingFile.mkdir();
        }
        return todaysVotingFile;
    }

    private void writeVotesToFile(File votesFile, char mood) throws IOException {
        if(!votesFile.exists()) {
            votesFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(votesFile,true);
        fileWriter.write(mood);
        fileWriter.close();
        System.out.println("voting " + mood);
    }

    private void addEmailToAlreadyVotedFile(String email) throws IOException {
        FileWriter fileWriter = new FileWriter(alreadyVotedFile,true);
        fileWriter.write(email + System.lineSeparator());
        fileWriter.close();
    }
}
