package votes;

import org.junit.Test;

import static votes.VotePoll.castVote;

public class VotePollTest {

        @Test
        public void blah() throws Exception{
            castVote("djordje.popovic@bskyb.com", 'N');
        }
}
