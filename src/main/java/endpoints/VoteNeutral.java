package endpoints;

import votes.Vote;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.IOException;

import static votes.VotePoll.castVote;

/**
    Neutral votes will arrive to this endpoint
 */

@Path("neutral")
public class VoteNeutral {

    @GET
    public String vote(@QueryParam("email") String email) {
        try {
            return castVote(new Vote(email, 'N'));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
