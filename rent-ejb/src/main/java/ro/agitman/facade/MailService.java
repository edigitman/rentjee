package ro.agitman.facade;

import ro.agitman.model.Advert;
import ro.agitman.model.User;

import javax.ejb.Local;

/**
 * Created by edi on 4/9/2015.
 */
@Local
public interface MailService {

    void sendConfirmMail(User u);

    void sendAdvertAdded(Advert advert);

    void sendNewPwd(User advert);

}
