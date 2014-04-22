package emails;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import static javax.mail.Message.RecipientType.TO;
import static javax.mail.Transport.send;

public class EmailSender {

    private EmailsFileReader emailsFileReader;

    public EmailSender(EmailsFileReader emailsFileReader) {
        this.emailsFileReader = emailsFileReader;
    }

    public void sendEmails() throws Exception {
        for (String emailAddress : emailsFileReader.loadMailList()) {
            Thread.sleep(1000);
            invokeJavaMail(emailAddress);
        }
    }

    private void invokeJavaMail(String to) throws MessagingException {
        send(message(to));
    }

    private Message message(String to) throws MessagingException {
        MimeMessage message = new MimeMessage(session(emailConfig()));
        message.setFrom(new InternetAddress("happinessbarometer@gmail.com"));
        message.setRecipients(TO,
                InternetAddress.parse(to));
        message.setSubject("How was your day?");
        //TODO externalize the server URL
        message.setText(
                        "<h2>How was your day?</h2>" +
                        "<br/>" +
                        "<h3><a href=\"http://10.240.224.248:8080/poligraph/happy?email=" + to + "\"><b>:)</b>  Great, I really enjoyed the day</a></h3>" +
                        "<br/>" +
                        "<h3><a href=\"http://10.240.224.248:8080/poligraph/neutral?email=" + to + "\"><b>:|</b>  Nothing special, Just another day at the office</a></h3>" +
                        "<br/>" +
                        "<h3><a href=\"http://10.240.224.248:8080/poligraph/sad?email=" + to + "\"><b>:(</b>  Not my best day</a></h3>"
                        ,"UTF8","html");
        return message;
    }

    private Properties emailConfig() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

    //TODO externalize credentials to a properties file
    private Session session(Properties props) {
        return Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("happinessbarometer@gmail.com", "happinessbarometer2014");
                    }
                }
        );
    }
}