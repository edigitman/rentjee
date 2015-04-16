package ro.agitman.service;

import ro.agitman.facade.MailService;
import ro.agitman.model.User;

import javax.ejb.Stateless;

/**
 * Created by edi on 4/9/2015.
 */
@Stateless
public class MailServiceImpl implements MailService {


    @Override
    public void sendConfirmMail(User u) {
        //TODO implement mail service
    }
}
