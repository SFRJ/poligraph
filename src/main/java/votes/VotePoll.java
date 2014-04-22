package votes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static votes.VoteValidator.validateAlreadyVoted;

public class VotePoll {

    //TODO don't allow an email to vote more than once in the same day.
    public static String castVote(String email, char mood) throws IOException {
        String outputMessage = null;
        outputMessage = checkIfThePersonAlreadyVoted(email);
        outputMessage = outputMessage == null ? performVote(email, mood) : outputMessage;
        return outputMessage;
    }

    private static String checkIfThePersonAlreadyVoted(String email) throws IOException {
        if(validateAlreadyVoted(email)) {
            return  "you already voted today.";
        }
        return null;
    }

    private static String performVote(String email, char mood) throws IOException {
        //TODO Make folder with the name in the format day_month_year
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

    private static void writeVotesToFile(File votesFile, char mood) throws IOException {
        if(!votesFile.exists()) {
            votesFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(votesFile,true);
        fileWriter.write(mood);
        fileWriter.close();
        System.out.println("voting " + mood);
    }

    private static void addEmailToAlreadyVotedFile(String email) throws IOException {
        FileWriter fileWriter = new FileWriter(new File("alreadyvoted.txt"),true);
        fileWriter.write(email + System.lineSeparator());
        fileWriter.close();
    }
}
