package ro.agitman.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.agitman.dba.DataAccessService;
import ro.agitman.facade.MailService;
import ro.agitman.facade.UserService;
import ro.agitman.model.User;

import java.util.*;

@Stateless
public class UserServiceImp implements UserService {

    @EJB
    private DataAccessService service;
    @EJB
    private MailService mailService;

    public User findUserByEmail(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<User> users = service.findWithNamedQuery(User.FIND_BY_EMAIL, map);
        return users.isEmpty() ? null : users.get(0);
    }

    public User register(User u){
        u.setConfirmedBl(Boolean.FALSE);
        u.setRegToken(UUID.randomUUID().toString());
        u.setCreateDate(new Date());
        u.setRole("USER");
        u = service.create(u);
        mailService.sendConfirmMail(u);
        return u;
    }

    public User create(User u){
        return service.create(u);
    }
}