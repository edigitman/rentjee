package ro.agitman.service;

import ro.agitman.facade.MailService;
import ro.agitman.model.Advert;
import ro.agitman.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by edi on 4/9/2015.
 */
@Stateless
public class MailServiceImpl implements MailService {

    @EJB
    private MailSender mailSender;

    @Override
    public void sendConfirmMail(User u) {
        mailSender.sendEmail(u.getEmail(), "Confirm your email", u.getRegToken());
    }

    @Override
    public void sendAdvertAdded(Advert advert) {
        mailSender.sendEmail(advert.getUser().getEmail(), "Anunt adaugat", "Anunt adaugat " + advert.getId());
    }

    @Override
    public void sendNewPwd(User user){
        mailSender.sendEmail(user.getEmail(), "Schima parola", user.getRegToken());
    }

    private String buildConfirm(String token){
        StringBuilder sb = new StringBuilder("Bine ai venit La chirie \n\n");
        sb.append("\t Pentru a confirma inregistrarea acceseaza linkul: ");
        sb.append("<a href='http://lachirie.ro/registerConfirm/" + token + "'>link</a>");
        sb.append("\n\nMultumim pentru inregistrare");
        return sb.toString();
    }
}
