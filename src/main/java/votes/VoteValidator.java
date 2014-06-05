package votes;

public interface VoteValidator {

    public boolean canVote(String email) throws Exception;
}
