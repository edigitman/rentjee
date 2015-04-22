package ro.agitman.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by AlexandruG on 4/22/2015.
 */
@Singleton
public class MailSender {

    private final String username = "lachirie@gmail.com";
    private Properties props;
    private MyAuthenticator authenticator;

    @PostConstruct
    public void init() {
        authenticator = new MyAuthenticator();
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public void sendEmail(String to, String subject, String body) {
        Session session = Session.getInstance(props, authenticator);

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            message.setSubject(subject);
            message.setContent(body, "text/html");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    class MyAuthenticator extends Authenticator {
        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, "stailachirie");
        }
    }
}
