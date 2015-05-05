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
        mailSender.sendEmail(u.getEmail(), "Confirma emailul", buildConfirm(u.getRegToken()));
    }

    @Override
    public void sendAdvertAdded(Advert advert) {
        mailSender.sendEmail(advert.getUser().getEmail(), "Anunt adaugat", buildAdvertAdded(advert.getId()));
    }

    @Override
    public void sendAdvertStatus(Advert advert) {
        mailSender.sendEmail(advert.getUser().getEmail(), "Anunt modificat", buildAdvertStatus(advert));
    }

    @Override
    public void sendNewPwd(User user) {
        mailSender.sendEmail(user.getEmail(), "Schima parola", buildNewPwd(user.getRegToken()));
    }

    private String buildConfirm(String token) {
        StringBuilder sb = new StringBuilder("Bine ai venit La chirie, <br/><br/>");
        sb.append("<p style='margin-left: 10px;'>Pentru a confirma inregistrarea acceseaza linkul: ");
        sb.append("<a href='http://lachirie.ro/confirm/" + token + "'>http://lachirie.ro/confirm/" + token + "</a>.</p>");
        sb.append("<br/><br/>Multumim pentru inregistrare.");
        return sb.toString();
    }

    private String buildAdvertAdded(Long id) {
        StringBuilder sb = new StringBuilder("Felicitari, <br/><br/>");
        sb.append("<p style='margin-left: 10px;'>Anuntul tau a fost adaugat si este accesibil ");
        sb.append("<a href='http://lachirie.ro/details/" + id + "'>http://lachirie.ro/details/" + id + "</a>.</p>");
        sb.append("<br/><br/>Multumim pentru ca folositi aplicatioa noastra.");
        return sb.toString();
    }

    private String buildNewPwd(String token) {
        StringBuilder sb = new StringBuilder("Salut, <br/><br/>");
        sb.append("<p style='margin-left: 10px;'>Recent ai facut o cerere pentru a-ti recupara parola, pentru a continua te rog acceseaza urmatorul link: <br/><br/>");
        sb.append("<a href='http://lachirie.ro/recoverConfirm/" + token + "'>http://lachirie.ro/recoverConfirm/" + token + "</a>.</p>");
        sb.append("<br/><br/>Multumim pentru ca folosesti aplicatioa noastra.");
        return sb.toString();
    }

    private String buildAdvertStatus(Advert advert) {
        StringBuilder sb = new StringBuilder("Salut, <br/><br/>");
        sb.append("<p style='margin-left: 10px;'>Anuntul tau pentru imobilul din ");
        sb.append(advert.getAddress().getCity().getName()).append(", ");
        sb.append("Str. ");
        sb.append(advert.getAddress().getStreet()).append(", ");
        sb.append("Nr. ");
        sb.append(advert.getAddress().getNr());
        sb.append("<br/>a fost modificat automat.</p>");
        sb.append("<br/><br/><p style='margin-left: 10px;'> Acum are statusul: " + advert.getStatus() + ".</p>");
        sb.append("<br/><br/><p style='margin-left: 10px;'> Mai multe detalii despre status si cum poate fi modificat gasesti ");
        sb.append("<a href='http://lachirie.ro/help'>http://lachirie.ro/help</a>.</p>");

        sb.append("<br/><br/>Multumim pentru ca folosesti aplicatioa noastra.");
        return sb.toString();
    }
}
