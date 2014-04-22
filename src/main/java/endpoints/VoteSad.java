package endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.IOException;

import static votes.VotePoll.castVote;

/**
    Sad votes will arrive to this endpoint
 */

@Path("sad")
public class VoteSad {

    @GET
    public String vote(@QueryParam("email") String email) {
        try {
            castVote(email,'S');
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
