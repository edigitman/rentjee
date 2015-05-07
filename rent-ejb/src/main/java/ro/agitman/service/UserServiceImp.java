package ro.agitman.service;

import org.apache.commons.codec.binary.Base64;
import ro.agitman.dba.DataAccessService;
import ro.agitman.facade.MailService;
import ro.agitman.facade.UserService;
import ro.agitman.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

        String encripted = sha256(u.getPassword());
        if (encripted != null) {
            u.setPassword(encripted);
        }

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

    public boolean update(User user, String confirmPwd, Boolean withPassword) {

        String encripted = sha256(confirmPwd);
        if(user.getPassword().equals(encripted))
            return false;

        if(withPassword){
            user.setPassword(sha256(user.getRegToken()));
            user.setRegToken(null);
        }

        service.update(user);
        return true;
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
        user.setPassword(sha256(pws));
        user.setRegToken(null);
        user.setConfirmedBl(true);

        service.update(user);
        return true;
    }

    private String sha256(String string) {
        MessageDigest digest = null;
        byte[] hash = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] digested = digest.digest(string.getBytes("UTF-8"));

            Base64 base64 = new Base64();
            hash = base64.encode(digested);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return new String(hash);
    }
}