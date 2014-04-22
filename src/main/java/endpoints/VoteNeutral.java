package endpoints;

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
            return castVote(email, 'N');
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
