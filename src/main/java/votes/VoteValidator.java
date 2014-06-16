package votes;

import java.io.IOException;

public interface VoteValidator {

    public boolean canVote(String email) throws IOException;
}
