package votes;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class VotesSaver {

    private VotingResultsProvider votingResultsProvider;

    public VotesSaver(VotingResultsProvider votingResultsProvider) {
        this.votingResultsProvider = votingResultsProvider;
    }

    public void castVote(String votersEmail, char mood) throws IOException {
        final OutputStream outputStream = votingResultsProvider.provide();
        outputStream.write(mood);
        outputStream.flush();
    }
}