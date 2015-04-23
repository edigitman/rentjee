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

    public User register(User u) {
        u.setConfirmedBl(Boolean.FALSE);
        u.setRegToken(UUID.randomUUID().toString());
        u.setCreateDate(new Date());
        u.setRole("USER");
        u = service.create(u);
        mailService.sendConfirmMail(u);
        return u;
    }

    public User create(User u) {
        return service.create(u);
    }

    public boolean confirm(String registerConfirmToken) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", registerConfirmToken);
        List<User> users = (List) service.findWithNamedQuery(User.FIND_BY_TOKEN, map, 1);
        if (users != null && !users.isEmpty()) {
            User user = users.get(0);
            user.setRegToken(null);
            user.setConfirmedBl(true);
            service.update(user);
            return true;
        }
        return false;
    }

    public void update(User user){
        service.update(user);
    }

    public boolean recover(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<User> users = service.findWithNamedQuery(User.FIND_BY_EMAIL, map);
        User user = users.isEmpty() ? null : users.get(0);

        if (user == null) {
            return false;
        }

        user.setRegToken(UUID.randomUUID().toString());

        service.update(user);
        mailService.sendNewPwd(user);
        return true;
    }

    public boolean recoverConfirm(String pws, String recoverConfirmToken) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", recoverConfirmToken);

        List<User> users = service.findWithNamedQuery(User.FIND_BY_TOKEN, map);
        User user = users.isEmpty() ? null : users.get(0);
        if (user == null) {
            return false;
        }
        user.setPassword(pws);
        user.setRegToken(null);
        user.setConfirmedBl(true);

        service.update(user);
        return true;
    }
}