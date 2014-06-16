package endpoints;

import votes.FileVoteValidator;
import votes.Vote;
import votes.VotePoll;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.File;

/**
    Sad votes will arrive to this endpoint
 */

@Path("sad")
public class VoteSad {

    @GET
    public String vote(@QueryParam("email") String email) {
        try {
            VotePoll votePoll = new VotePoll(new FileVoteValidator(new File("alreadyvoted.txt")));
            votePoll.castVote(new Vote(email, 'S'));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
