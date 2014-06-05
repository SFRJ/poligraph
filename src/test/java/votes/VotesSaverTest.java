package votes;

import org.junit.Before;
import org.junit.Test;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VotesSaverTest {

    private VotingResultsProvider provider;
    private PipedInputStream inputStream;

    @Before
    public void setupVotingResultsProvider() throws Exception {
        provider = mock(VotingResultsProvider.class);
        final PipedOutputStream outputStream = new PipedOutputStream();
        when(provider.provide()).thenReturn(outputStream);
        inputStream = new PipedInputStream(outputStream);
    }

    @Test
    public void shouldAddAVoteToTheSavedVotingFileWhenAVoteIsCasted() throws Exception {
        VotesSaver votesSaver = new VotesSaver(provider);
        votesSaver.castVote("enrico@gmail.com", 'H');
        final char savedMood = (char)inputStream.read();
        assertThat(savedMood, is('H'));
    }
}