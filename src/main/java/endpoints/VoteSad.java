package endpoints;

import votes.Vote;
import votes.VotePoll;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
    Sad votes will arrive to this endpoint
 */

@Path("sad")
public class VoteSad {

    @GET
    public String vote(@QueryParam("email") String email) {
        try {
            VotePoll votePoll = new VotePoll();
            votePoll.castVote(new Vote(email, 'S'));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
