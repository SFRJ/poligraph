package votes;

public class VotesSaver {

    private MoodPersister moodPersister;
    private AlreadyVotedEmailPersister alreadyVotedEmailPersister;

    /*
    From OO Design:
    Good practice to pass interfaces to methods and constructors, and delegate the implementation detal to specific implementations of those.
    - Composition over inheritance
    - Program to the interface and not to the realization
    */
    public VotesSaver(MoodPersister moodPersister, AlreadyVotedEmailPersister alreadyVotedEmailPersister) {
        this.moodPersister = moodPersister;
        this.alreadyVotedEmailPersister = alreadyVotedEmailPersister;
    }

    public void castVote(String votersEmail, char mood) throws Exception {
        moodPersister.save(mood);
        alreadyVotedEmailPersister.save(votersEmail);
    }
}