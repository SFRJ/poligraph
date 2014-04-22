package emails;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.mockito.Mockito.*;

public class EmailSenderTest {

    @Ignore
    @Test
    public void emailsFileIsUsedJustOnceWhenEmailsAreSent() throws Exception {
        EmailsFileReader reader = mock(EmailsFileReader.class);
        EmailSender emailSender = new EmailSender(reader);
        emailSender.sendEmails();
        verify(reader,times(1)).loadMailList();
    }

    @Ignore
    @Test
    public void sendEmailsIntegrationTest() throws Exception {
        EmailsFileReader reader = new EmailsFileReader(new File("src/test/resources/sampleFakeEmailsFile.txt"));
        EmailSender emailSender = new EmailSender(reader);
        emailSender.sendEmails();
    }

    @Test
    public void emailSend() throws Exception {
        EmailsFileReader reader = new EmailsFileReader(new File("emails.txt"));
        EmailSender emailSender = new EmailSender(reader);
        emailSender.sendEmails();
    }
}
