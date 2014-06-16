package endpoints;

import votes.Vote;
import votes.VotePoll;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
/**
    Happy votes will arrive to this endpoint
 */

@Path("happy")
public class VoteHappy {

    @GET
    public String vote(@QueryParam("email") String email) {
        System.out.println(email + " reached the happy endpoint");
        try {
            VotePoll votePoll = new VotePoll();
            return votePoll.castVote(new Vote(email, 'H'));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
