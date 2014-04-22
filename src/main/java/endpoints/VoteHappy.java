package endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.IOException;

import static votes.VotePoll.castVote;
/**
    Happy votes will arrive to this endpoint
 */

@Path("happy")
public class VoteHappy {

    @GET
    public String vote(@QueryParam("email") String email) {
        System.out.println(email + " reached the happy endpoint");
        try {
            return castVote(email, 'H');
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
