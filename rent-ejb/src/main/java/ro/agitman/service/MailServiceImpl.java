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
        mailSender.sendEmail(u.getEmail(), "Confirm your email", buildConfirm(u.getRegToken()));
    }

    @Override
    public void sendAdvertAdded(Advert advert) {
        mailSender.sendEmail(advert.getUser().getEmail(), "Anunt adaugat", buildAdvertAdded(advert.getId()));
    }

    @Override
    public void sendNewPwd(User user) {
        mailSender.sendEmail(user.getEmail(), "Schima parola", buildNewPwd(user.getRegToken()));
    }

    private String buildConfirm(String token) {
        StringBuilder sb = new StringBuilder("Bine ai venit La chirie \n\n");
        sb.append("\t Pentru a confirma inregistrarea acceseaza linkul: ");
        sb.append("<a href='http://lachirie.ro/registerConfirm/" + token + "'>link</a>.");
        sb.append("\n\nMultumim pentru inregistrare");
        return sb.toString();
    }

    private String buildAdvertAdded(Long id) {
        StringBuilder sb = new StringBuilder("Felicitari, \n\n");
        sb.append("\t Anuntul tau a fost adaugat si este accesibil ");
        sb.append("<a href='http://lachirie.ro/details/" + id + "'>aici</a>.");
        sb.append("\n\nMultumim pentru ca folositi aplicatioa noastra");
        return sb.toString();
    }

    private String buildNewPwd(String token) {
        StringBuilder sb = new StringBuilder("Salut, \n\n");
        sb.append("\t Recent ai facut o cerere pentru a-ti recupara parola, pentru a continua te rog acceseaza urmatorul link: \n\n");
        sb.append("<a href='http://lachirie.ro/recoverConfirm/" + token + "'>http://lachirie.ro/recoverConfirm/" + token + "</a>.");
        sb.append("\n\nMultumim pentru ca folosesti aplicatioa noastra");
        return sb.toString();
    }
}
