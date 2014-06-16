package votes;

import org.junit.Test;

import static votes.VotePoll.castVote;

public class VotePollTest {

        @Test
        public void blah() throws Exception{
            castVote(new Vote("djordje.popovic@bskyb.com", 'N'));
        }
}
