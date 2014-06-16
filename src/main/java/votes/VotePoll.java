package votes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class VotePoll {

    private final File alreadyVotedFile;
    private VoteValidator voteValidator;

    public VotePoll() {
        alreadyVotedFile = new File("alreadyvoted.txt");
        voteValidator = new FileVoteValidator(alreadyVotedFile);
    }

    public String castVote(Vote vote) throws Exception {
        String outputMessage = null;
        final boolean canVote = voteValidator.canVote(vote.getEmail());
        outputMessage = canVote ? performVote(vote.getEmail(), vote.getMood()) : outputMessage;
        return outputMessage;
    }

    private String performVote(String email, char mood) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
        String date = sdf.format(new Date());
        String pathToVotesPoll = "votespoll";
        File votespoll = new File(pathToVotesPoll);
        File todaysVotingFolder = new File(pathToVotesPoll);
        File votes = new File(pathToVotesPoll+ "/" + date + ".txt");

        if(votespoll.exists()) {
            if(!todaysVotingFolder.exists()) {
                todaysVotingFolder.mkdir();
            }
            writeVotesToFile(votes,mood);
            addEmailToAlreadyVotedFile(email);
        }
        return "Thanks for your feedback!";
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
