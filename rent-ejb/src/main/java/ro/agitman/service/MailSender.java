package ro.agitman.service;

import com.sendgrid.SendGrid;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 * Created by AlexandruG on 4/22/2015.
 */
@Singleton
public class MailSender {

    private SendGrid sendgrid;

    @PostConstruct
    public void init() {
        // email: lachirie@gmail.com
        // pwd: stailachirie

        sendgrid = new SendGrid("code");
    }

    public void sendEmail(String to, String subject, String body) {
        try {
            SendGrid.Email email = new SendGrid.Email();
            email.addTo(to);
            email.addToName(to);
            email.setReplyTo("lachirie@gmail.com");
            email.setFrom("lachirie@gmail.com");
            email.setFromName("La Chirie");
            email.setSubject(subject);
            email.setHtml(body);

            sendgrid.send(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
