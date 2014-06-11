package votes;

public interface AlreadyVotedEmailPersister {
    void save(String votersEmail);
}
