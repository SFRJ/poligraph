package endpoints;

import votes.Vote;
import votes.VotePoll;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
    Neutral votes will arrive to this endpoint
 */

@Path("neutral")
public class VoteNeutral {

    @GET
    public String vote(@QueryParam("email") String email) {
        try {
            VotePoll votePoll = new VotePoll();
            return votePoll.castVote(new Vote(email, 'N'));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
